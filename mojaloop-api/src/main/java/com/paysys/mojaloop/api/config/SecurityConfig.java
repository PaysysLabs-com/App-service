package com.paysys.mojaloop.api.config;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.paysys.mojaloop.api.authentication.MojaloopServiceAuthenticator;
import com.paysys.mojaloop.api.authentication.UsernamePasswordAuthenticationProvider;
import com.paysys.mojaloop.api.controller.APIController;
import com.paysys.mojaloop.api.controller.error.CustomErrorController;
import com.paysys.mojaloop.api.filter.ExceptionHandlerFilter;
import com.paysys.mojaloop.api.filter.LocalRequestTrackingFilter;
import com.paysys.mojaloop.api.filter.MojaloopAuthenticationFilter;
import com.paysyslabs.bootstrap.infrastructure.security.ExternalServiceAuthenticator;
import com.paysyslabs.bootstrap.infrastructure.security.TokenAuthenticationProvider;
import com.paysyslabs.bootstrap.service.TokenService;
import com.paysyslabs.mojaloop.dao.repo.CustomerRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Value("${springfox.documentation.swagger.v2.path:#{null}}")
	private String swaggerApiDocsPath;

	@Value("${swagger.enabled:false}")
	private Boolean swaggerEnabled;

	@Autowired
	@Qualifier("tokenService")
	private TokenService tokenService;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TokenAuthenticationProvider tokenAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers(anonymousEndpoints()).permitAll().antMatchers("/**").authenticated()
				.and().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());

		http.addFilterBefore(new LocalRequestTrackingFilter(), BasicAuthenticationFilter.class);
		http.addFilterBefore(new ExceptionHandlerFilter(), BasicAuthenticationFilter.class);

		http.addFilterBefore(new MojaloopAuthenticationFilter(authenticationManager(), customerRepository),
				BasicAuthenticationFilter.class);
	}
	
	private String[] anonymousEndpoints() {
		String endpoints[] = { CustomErrorController.PATH, APIController.WEBPAGES_ENDPOINT + "/**",
				APIController.REGISTRATION_ENDPOINT + "/**", "/.well-known/**" };

		if (swaggerEnabled) {
			String swaggerEndpoints[] = { "/swagger-ui.html", swaggerApiDocsPath, "/webjars/**",
					"/swagger-resources/**" };
			endpoints = ArrayUtils.addAll(endpoints, swaggerEndpoints);
		}

		return endpoints;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(domainUsernamePasswordAuthenticationProvider())
				.authenticationProvider(tokenAuthenticationProvider);
	}

	@Bean
	public ExternalServiceAuthenticator someExternalServiceAuthenticator() {
		return new MojaloopServiceAuthenticator();
	}

	@Bean
	public AuthenticationProvider domainUsernamePasswordAuthenticationProvider() {
		return new UsernamePasswordAuthenticationProvider(tokenService, someExternalServiceAuthenticator());
	}

	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}

}

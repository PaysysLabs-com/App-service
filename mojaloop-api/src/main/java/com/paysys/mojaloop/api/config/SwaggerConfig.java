package com.paysys.mojaloop.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.paysys.mojaloop.api.domain.DomainUser;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true", matchIfMissing = false)
@EnableSwagger2
public class SwaggerConfig {

	@Value("${api.title}")
	private String title;

	@Value("${api.version}")
	private String version;

	@Value("${api.description}")
	private String description;

	@Value("${api.contact.name}")
	private String name;

	@Value("${api.contact.link}")
	private String link;

	@Value("${api.contact.email}")
	private String email;

	@Bean
	public Docket api() {

		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.name("X-Auth-Token").modelRef(new ModelRef("string")).parameterType("header").required(false)
				.build();

		List<Parameter> aParameters = new ArrayList<Parameter>();
		aParameters.add(aParameterBuilder.build());

		return new Docket(DocumentationType.SWAGGER_2).ignoredParameterTypes(DomainUser.class).select().apis(RequestHandlerSelectors.any()).paths(paths())
				.build().globalOperationParameters(aParameters).apiInfo(metadata());
	}

	private Predicate<String> paths() {
		return Predicates.not(PathSelectors.regex("/error.*|/flowable.*|/metrics.*|/heapdump.*|/env.*|/autoconfig.*"
				+ "|/beans.*|/dump.*|/autoconfig.*|/info.*|/trace.*|/auditevents.*|/configprops.*|/mappings.*"));
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder().title(title).description(description).version(version)
				.contact(new Contact(name, link, email)).build();
	}

}

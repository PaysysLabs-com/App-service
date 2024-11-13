package com.paysys.mojaloop.api.authentication;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

import com.paysys.mojaloop.api.domain.DomainUser;
import com.paysys.mojaloop.api.exception.MojaloopAuthenticationException;
import com.paysys.mojaloop.api.utils.UtilMethods;
import com.paysyslabs.bootstrap.infrastructure.AuthenticatedExternalWebService;
import com.paysyslabs.bootstrap.infrastructure.security.ExternalServiceAuthenticator;
import com.paysyslabs.mojaloop.commons.ResponseCodes;
import com.paysyslabs.mojaloop.commons.enums.Channel;
import com.paysyslabs.mojaloop.commons.enums.DeviceType;
import com.paysyslabs.mojaloop.dao.entity.Customer;
import com.paysyslabs.mojaloop.dao.repo.CustomerRepository;

public class MojaloopServiceAuthenticator implements ExternalServiceAuthenticator{

	private static final Logger LOG = LoggerFactory.getLogger(MojaloopServiceAuthenticator.class);

    @Autowired
    private CustomerRepository customerRepository;
    
	@Override
	public AuthenticatedExternalWebService authenticate(String username, String password, HttpServletRequest request)
			throws AuthenticationException {
		UtilMethods utilMethods = new UtilMethods();

		String passwordHash = utilMethods.getHash(password);

		LOG.info("authenticating: '{}' - '{}'", username, passwordHash);

		String deviceToken = request.getHeader("X-Device-Token");
		DeviceType deviceType = DeviceType.valueOf(request.getHeader("X-Device-Type"));
		String deviceID = request.getHeader("X-Device-ID");
		String deviceVersion = request.getHeader("X-App-Version");
		Channel channel = Channel.valueOf(request.getHeader("X-Channel"));

        String latidude = "";
        String longitude = "";

        if (request.getHeader("X-Latitude") != null)
            latidude = request.getHeader("X-Latitude");

        if (request.getHeader("X-Longitude") != null)
            longitude = request.getHeader("X-Longitude");

        LOG.info(
                "Device Token: {} - Device Type: {} - Device ID: {} - Channel: {} - App Version: {} - Latitude: {} - Longitude: {}",
                deviceToken, deviceType, deviceID, channel, deviceVersion, latidude, longitude);
        long start = System.currentTimeMillis();


		Customer customer = customerRepository.findByUsername(username);
		
		
		LOG.info("findByUsername took {} ms", System.currentTimeMillis() - start);
		
        if (customer == null) {
    
            throw new MojaloopAuthenticationException(ResponseCodes.INVALID_LOGIN);
        }
        
        DomainUser user = new DomainUser(username);

        user.setLoggedinUserDetails(customer);

        AuthenticatedExternalWebService authenticatedExternalWebService = new AuthenticatedExternalWebService(user,
                null, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DOMAIN_USER"));

        authenticatedExternalWebService.setToken(customer.getToken());

        return authenticatedExternalWebService;

	}
}

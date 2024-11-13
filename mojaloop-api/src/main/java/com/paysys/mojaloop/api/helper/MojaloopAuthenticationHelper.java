package com.paysys.mojaloop.api.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.paysys.mojaloop.api.domain.DomainUser;
import com.paysys.mojaloop.api.exception.MojaloopAuthenticationException;
import com.paysyslabs.bootstrap.infrastructure.security.AuthenticationHelper;
import com.paysyslabs.mojaloop.commons.ResponseCodes;
import com.paysyslabs.mojaloop.dao.entity.Customer;
import com.paysyslabs.mojaloop.dao.repo.CustomerRepository;

@Component
public class MojaloopAuthenticationHelper implements AuthenticationHelper {

	private static final Logger LOG = LoggerFactory.getLogger(MojaloopAuthenticationHelper.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public AuthenticationException createSessionInvalidException() {
		return new MojaloopAuthenticationException(ResponseCodes.SESSION_INVALID);
	}

	@Override
	public AuthenticationException createSessionExpiredException() {
		return new MojaloopAuthenticationException(ResponseCodes.SESSION_EXPIRED);

	}

	@Override
	public void updateTokenForPrincipal(String token, Object principal) {
		
		DomainUser user = (DomainUser) principal;

		Customer customer = user.getLoggedinUserDetails();

		LOG.info("id = {} token = {}", customer.getId(), token);

		customerRepository.updateLatestToken(customer.getId(), token);

		LOG.info("Update authentication token for principal");

	}

}

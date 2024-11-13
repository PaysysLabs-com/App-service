package com.paysys.mojaloop.api.authentication;

import com.paysyslabs.bootstrap.infrastructure.security.DomainUsernamePasswordAuthenticationProvider;
import com.paysyslabs.bootstrap.infrastructure.security.ExternalServiceAuthenticator;
import com.paysyslabs.bootstrap.service.TokenService;
import com.paysyslabs.mojaloop.commons.ResponseCodes;

public class UsernamePasswordAuthenticationProvider extends DomainUsernamePasswordAuthenticationProvider{

	public UsernamePasswordAuthenticationProvider(TokenService tokenService,
			ExternalServiceAuthenticator externalServiceAuthenticator) {
		super(tokenService, externalServiceAuthenticator);
		
	}

	@Override
	protected String getInvalidLoginResponseCode() {
		// TODO Auto-generated method stub
		return ResponseCodes.INVALID_INFO;
	}

}

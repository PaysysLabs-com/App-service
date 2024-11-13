package com.paysys.mojaloop.api.exception;

import org.springframework.security.core.AuthenticationException;

import com.paysyslabs.mojaloop.commons.ResponseCodes;

public class MojaloopAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 2775740869229677567L;

	private final String code;

	public MojaloopAuthenticationException(String code) {
		super(ResponseCodes.RESPONSE_MAP.get(code) != null ? ResponseCodes.RESPONSE_MAP.get(code) : "Internal Authentication Exception");
        this.code = code;
	}
	
    public String getCode() {
        return code;
    }

}

package com.paysyslabs.mojaloop.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paysyslabs.mojaloop.model.LoginDetails;;


@JsonInclude(Include.NON_NULL)
public class TokenResponse {

	@JsonProperty
	private String token;
	
    @JsonProperty
    private LoginDetails details;

	@JsonProperty
	private String expiry;

	public TokenResponse() {
	}

	public TokenResponse(String token, String expiry) {
		super();
		this.token = token;
		this.expiry = expiry;
	}

	
	
	public TokenResponse(String token, LoginDetails details) {
		super();
		this.token = token;
		this.details = details;
	}

	@Override
	public String toString() {
		return new com.google.gson.Gson().toJson(this);
	}
}

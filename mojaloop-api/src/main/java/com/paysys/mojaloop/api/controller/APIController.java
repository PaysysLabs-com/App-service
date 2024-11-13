package com.paysys.mojaloop.api.controller;

public abstract class APIController {

    private static final String API_ENDPOINT = "/api/v1";

	public static final String AUTHENTICATE_ENDPOINT 				= API_ENDPOINT + "/authenticate";
    public static final String REGISTRATION_ENDPOINT                = API_ENDPOINT + "register";
    public static final String WEBPAGES_ENDPOINT                	= API_ENDPOINT + "/webpages";
    public static final String MY_ENDPOINT                     		= API_ENDPOINT + "/my";
}

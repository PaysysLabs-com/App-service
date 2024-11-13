package com.paysyslabs.mojaloop.commons;

import java.util.HashMap;

public class ResponseCodes {
	public static final String OK = "00";
	public static final String INVALID_PARAMS = "01";
	public static final String INVALID_INFO = "02";
	public static final String SESSION_INVALID = "13";
	public static final String SESSION_EXPIRED = "14";
	public static final String INVALID_LOGIN = "15";
	public static final String MULTI_LOGIN = "16";
	public static final String INACTIVE_USER = "17";

	public static final String UNABLE_TO_VALIDATE_LINKED_ACCOUNT = "18";
	public static final String UNABLE_TO_GET_MINI_STATEMENT = "19";

	public static final String INVALID_ACCOUNT_NUMBER = "20";
	public static final String INVALID_CNIC = "21";

	public static final String UNABLE_TO_PROCESS_CODE = "99";
	public static final String UNABLE_TO_PROCESS_MESSAGE = "Unable to process at this time. Please, try again later";

	public static final HashMap<String, String> RESPONSE_MAP = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4396790457518423520L;

		{
			put(OK, "Processed OK");
			put(INVALID_PARAMS, "Required parameters not provided");
			put(INVALID_INFO, "Invalid information provided");
			put(SESSION_INVALID, "Invalid session provided.");
			put(SESSION_EXPIRED, "Session expired.");
			put(INVALID_LOGIN, "Invalid login attempt.");
			put(INACTIVE_USER, "User inactive.");
			put(SESSION_INVALID, "Invalid session provided.");
			put(UNABLE_TO_VALIDATE_LINKED_ACCOUNT, "Unable to validate linked account");
			put(UNABLE_TO_GET_MINI_STATEMENT, "Unable to get mini statement");
			put(INVALID_ACCOUNT_NUMBER, "Account Number must not be empty");
			put(INVALID_CNIC, "CNIC must not be empty");

		}
	};
}

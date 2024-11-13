package com.paysys.mojaloop.api.exception;

public class MojaloopGeneralException extends RuntimeException {

	private static final long serialVersionUID = 8160175874085333377L;

	private final String code;
	
	public MojaloopGeneralException(String code) {
		super();
		this.code = code;
	}

	public MojaloopGeneralException(String code,String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}

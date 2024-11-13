package com.paysyslabs.mojaloop.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paysyslabs.mojaloop.commons.ResponseCodes;


@JsonInclude(Include.NON_NULL)
public class GenericResponse<T> {

	@JsonProperty
	protected String responseCode;

	@JsonProperty
	protected String responseDescription;

	@JsonProperty
	protected T data;

	public GenericResponse() {
		super();
		this.responseCode = "00";
		this.responseDescription = "Processed OK";
	}

	public GenericResponse(String responseCode, String responseDescription, T data) {
		super();
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
		this.data = data;
	}

	public GenericResponse(String responseCode) {
		super();
		this.responseCode = responseCode;
		this.responseDescription = ResponseCodes.RESPONSE_MAP.get(responseCode);
	}

	public GenericResponse(String responseCode, T data) {
		super();
		this.responseCode = responseCode;
		this.responseDescription = ResponseCodes.RESPONSE_MAP.get(responseCode);
		this.data = data;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GenericResponse [responseCode=" + responseCode + ", responseDescription=" + responseDescription
				+ ", data=" + data + "]";
	}

}

package com.paysys.mojaloop.api.controller.error;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;

@JsonInclude(Include.NON_NULL)
public class ErrorJson {

	public Integer status;
	public String error;
	public String message;
	public String timeStamp;
	public String trace;

	public ErrorJson(int status, Map<String, Object> errorAttributes) {
		this.status = status;
		this.error = (String) errorAttributes.get("error");
		this.message = (String) errorAttributes.get("message");
		this.timeStamp = errorAttributes.get("timestamp").toString();
		this.trace = (String) errorAttributes.get("trace");
	}
}

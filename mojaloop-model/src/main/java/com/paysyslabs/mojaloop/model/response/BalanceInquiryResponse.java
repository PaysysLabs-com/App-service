package com.paysyslabs.mojaloop.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;;

@JsonInclude(Include.NON_NULL)
public class BalanceInquiryResponse {

	@JsonProperty
    private String availableBalance;

	@JsonProperty
    private String currentBalance;

	@JsonProperty
    private String institutionCode;
	
	public BalanceInquiryResponse(String availableBalance, String currentBalance, String institutionCode) {
		super();
		this.availableBalance = availableBalance;
		this.currentBalance = currentBalance;
		this.institutionCode = institutionCode;
	}

	public String getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	
}

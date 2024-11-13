package com.paysyslabs.mojaloop.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BalanceInquiryRequest implements Serializable{

	private static final long serialVersionUID = 2319149726573538558L;

    @NotBlank(message = "Saving Account Number cannot be null")
    @Size(min=5, max=30, message = "Saving Account Number cannot be < 5 and > 30")
    private String savingAccountNumber;
        
    private String institutionCode;

	public String getSavingAccountNumber() {
		return savingAccountNumber;
	}

	public void setSavingAccountNumber(String savingAccountNumber) {
		this.savingAccountNumber = savingAccountNumber;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}
}

package com.paysyslabs.mojaloop.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;

@JsonInclude(Include.NON_NULL)
public class LoginDetails {

	private static final long serialVersionUID = 6380187136609368889L;

	private String mobileNumber;
    private String accountTitle;
    private String lastLoginDate;
    private String isFirstLogin;
    private boolean showTnc;
    private String account;
    private String email;
    
    public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAccountTitle() {
		return accountTitle;
	}
	public void setAccountTitle(String accountTitle) {
		this.accountTitle = accountTitle;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getIsFirstLogin() {
		return isFirstLogin;
	}
	public void setIsFirstLogin(String isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	public boolean isShowTnc() {
		return showTnc;
	}
	public void setShowTnc(boolean showTnc) {
		this.showTnc = showTnc;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}

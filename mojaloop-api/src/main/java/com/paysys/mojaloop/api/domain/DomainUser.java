package com.paysys.mojaloop.api.domain;

import java.io.Serializable;

import com.paysyslabs.mojaloop.dao.entity.Customer;

public class DomainUser implements Serializable{

	private static final long serialVersionUID = -7875094328361756982L;

    private String username;
    private Customer loggedinUserDetails;

    public DomainUser(String username, Customer loggedinUserDetails) {
		super();
		this.username = username;
		this.loggedinUserDetails = loggedinUserDetails;
	}
	
	public DomainUser(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Customer getLoggedinUserDetails() {
		return loggedinUserDetails;
	}

	public void setLoggedinUserDetails(Customer loggedinUserDetails) {
		this.loggedinUserDetails = loggedinUserDetails;
	}

}

package com.paysyslabs.mojaloop.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.paysyslabs.mojaloop.commons.enums.Channel;

@Entity
@Table(name = "login_audit_log")
public class LoginAuditLog implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 8591624523459613226L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn(name="customer")
    private Customer customer;

    @Column(name = "stamp_date")
    private Date stampDate;
    
    @Column(name = "username", length = 20)
    private String username;
    
    @Column(name = "password", length = 200)
    private String password;
    
    @Column(name = "ip_address", length = 200)
    private String ipAddress;
    
    @Column(name = "response_code", length = 20)
    private String responseCode;
    
    @Column(name = "device_id", length = 100)
    private String deviceId;
    
    @Column(name = "device_type", length = 50)
    private String deviceType;
    
    @Column(name = "device_token", length = 200)
    private String deviceToken;
    
    @Column(name = "response_desc", columnDefinition = "text")
    private String responseDesc;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", length = 100, columnDefinition = "varchar(100) default 'MOBILE_APP'", nullable = false)
    private Channel channel = Channel.MOBILE_APP;
    
    @Column(name = "device_version", length = 50)
    private String deviceVersion;
    
    @Column(name = "latitude", length = 50)
    private String latitude;
    
    @Column(name = "longitude", length = 50)
    private String longitude;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getStampDate() {
		return stampDate;
	}

	public void setStampDate(Date stampDate) {
		this.stampDate = stampDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

    
}

package com.paysyslabs.mojaloop.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paysyslabs.mojaloop.commons.enums.Channel;
import com.paysyslabs.mojaloop.commons.enums.DeactivationTypes;
import com.paysyslabs.mojaloop.commons.enums.DeviceType;

@Entity
@Table(name="customer",indexes = { @Index(columnList = "username", unique = true)})
public class Customer {

	private static final long serialVersionUID = -3189113291136826379L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;
    
    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    
    @Column(name = "email", length = 200)
    private String email;

    @Column(name = "mobile_number", nullable = false, unique = true, length = 50)
    private String mobileNumber;
    
    @Column(name = "dob", nullable = false, length = 50)
    private String dob;
    
    @Column(name = "is_verified", nullable = false)
    private boolean isVerified = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", length = 100, nullable = false)
    private Channel channel;

    @JsonIgnore
    @Column(name = "device_id", unique = false, length = 50)
    private String deviceID;

    @Column(name = "device_token", length = 200)
    private String deviceToken;

    @Column(name = "app_version", length = 50)
    private String appVersion;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type", length = 50)
    private DeviceType deviceType;

    @JsonIgnore
    @Column(name = "is_first_login", nullable = false)
    private boolean isFirstLogin = true;

    @JsonIgnore
    @Column(name = "locked", nullable = false)
    private boolean locked = false;

    @JsonIgnore
    @Column(name = "registration_date", nullable = false, unique = true)
    private Date registrationDate;

    @JsonIgnore
    @Column(name = "password", nullable = false, unique = false, length = 100)
    private String password;
    
    @JsonIgnore
    @Column(name = "secret_question")
    private String secretQuestion;

    @JsonIgnore
    @Column(name = "secret_answer")
    private String secretAnswer;
    
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "login_date", nullable = true, unique = true)
    private Date loginDate;

    @Column(name = "show_tnc", nullable = false)
    private boolean showTnc = false;

    @JsonIgnore
    @Column(name = "token", length = 100)
    private String token;

    @JsonIgnore
    @Column(name = "wrong_password_count", nullable = false)
    private int wrongPasswordCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_status", nullable = false)
    private DeactivationTypes customerStatus = DeactivationTypes.Active;

    @Column(name = "deactivation_reason", length = 200)
    private String deactivationReason;
    
    @Column(name = "latitude", length = 50)
    private String latitude;

    @Column(name = "longitude", length = 50)
    private String longitude;

    
	public Customer() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public boolean isShowTnc() {
		return showTnc;
	}

	public void setShowTnc(boolean showTnc) {
		this.showTnc = showTnc;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getWrongPasswordCount() {
		return wrongPasswordCount;
	}

	public void setWrongPasswordCount(int wrongPasswordCount) {
		this.wrongPasswordCount = wrongPasswordCount;
	}

	public DeactivationTypes getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(DeactivationTypes customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getDeactivationReason() {
		return deactivationReason;
	}

	public void setDeactivationReason(String deactivationReason) {
		this.deactivationReason = deactivationReason;
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

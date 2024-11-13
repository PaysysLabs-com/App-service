package com.paysys.mojaloop.api.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.paysys.mojaloop.api.controller.APIController;
import com.paysys.mojaloop.api.controller.error.CustomErrorController;
import com.paysys.mojaloop.api.domain.DomainUser;
import com.paysys.mojaloop.api.exception.MojaloopGeneralException;
import com.paysyslabs.bootstrap.infrastructure.security.AuthenticationFilter;
import com.paysyslabs.mojaloop.commons.ResponseCodes;
import com.paysyslabs.mojaloop.commons.enums.Channel;
import com.paysyslabs.mojaloop.commons.enums.DeviceType;
import com.paysyslabs.mojaloop.dao.entity.Customer;
import com.paysyslabs.mojaloop.dao.repo.CustomerRepository;
import com.paysyslabs.mojaloop.model.LoginDetails;
import com.paysyslabs.mojaloop.model.response.GenericResponse;
import com.paysyslabs.mojaloop.model.response.TokenResponse;

public class MojaloopAuthenticationFilter extends AuthenticationFilter {

	private static final Logger LOG = LoggerFactory.getLogger(MojaloopAuthenticationFilter.class);

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

	public static final String RRN_SESSION_KEY = "rrn";

	private CustomerRepository customerRepository;

	public MojaloopAuthenticationFilter(AuthenticationManager authenticationManager,
			CustomerRepository customerRepository) {
		super(authenticationManager);
		this.customerRepository = customerRepository;
	}

	@Override
	protected String getErrorEndpoint() {
		return CustomErrorController.PATH;
	}

	@Override
	protected String getAuthenticationEndpoint() {

		return APIController.AUTHENTICATE_ENDPOINT;
	}

	@Override
	protected void respondWithLoginDetails(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			String token, Authentication authentication, Optional<String> username) throws RuntimeException {

		try {

			DomainUser domainUser = (DomainUser) authentication.getPrincipal();

			Customer customer = domainUser.getLoggedinUserDetails();

			String deviceToken = httpRequest.getHeader("X-Device-Token");
			DeviceType deviceType = DeviceType.valueOf(httpRequest.getHeader("X-Device-Type"));
			String deviceID = httpRequest.getHeader("X-Device-ID");
			String appVersion = httpRequest.getHeader("X-App-Version");
			Channel channel = Channel.valueOf(httpRequest.getHeader("X-Channel"));

			String latitude = "";
			String longitude = "";

			if (httpRequest.getHeader("X-Latitude") != null)
				latitude = httpRequest.getHeader("X-Latitude");

			if (httpRequest.getHeader("X-Longitude") != null)
				longitude = httpRequest.getHeader("X-Longitude");

			LoginDetails loginDetails = new LoginDetails();
			loginDetails.setLastLoginDate(FORMAT.format(new Date().getTime()));
			loginDetails.setMobileNumber(customer.getMobileNumber());
			loginDetails.setIsFirstLogin(customer.isFirstLogin() ? "1" : "0");
			loginDetails.setShowTnc(customer.isShowTnc());
			loginDetails.setEmail(customer.getEmail());

			
			
			if (customer != null) {
				customer.setAppVersion(appVersion);
				customer.setChannel(channel);
				customer.setDeviceID(deviceID);
				customer.setDeviceToken(deviceToken);
				customer.setDeviceType(deviceType);
				customer.setLatitude(latitude);
				customer.setLongitude(longitude);
				customer.setToken(token);
				customer.setLoginDate(new Date());

				customerRepository.save(customer);
			}

			
			TokenResponse tokenResponse = new TokenResponse(token, loginDetails);
			GenericResponse<TokenResponse> response = new GenericResponse<TokenResponse>("00", tokenResponse);
			String tokenJsonResponse = new ObjectMapper().writeValueAsString(response);
			httpResponse.setStatus(HttpServletResponse.SC_OK);
			httpResponse.addHeader("Content-Type", "application/json");
			httpResponse.getWriter().print(tokenJsonResponse);

		} catch (Exception e) {
			LOG.error("Failure responding", e);
			throw new MojaloopGeneralException(ResponseCodes.UNABLE_TO_PROCESS_CODE, e.getMessage());
		}
	}

}

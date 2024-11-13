package com.paysys.mojaloop.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paysys.mojaloop.api.domain.CurrentlyLoggedUser;
import com.paysys.mojaloop.api.domain.DomainUser;
import com.paysys.mojaloop.api.service.AccountInfoService;
import com.paysys.mojaloop.api.utils.UtilMethods;
import com.paysyslabs.mojaloop.commons.enums.Channel;
import com.paysyslabs.mojaloop.model.request.BalanceInquiryRequest;
import com.paysyslabs.mojaloop.model.response.BalanceInquiryResponse;
import com.paysyslabs.mojaloop.model.response.GenericResponse;

import io.swagger.annotations.Api;

@Api(tags = "Account Info Controller")
@RestController
@RequestMapping(path = APIController.MY_ENDPOINT)
public class AccountInfoController {
	
	@Autowired
	private AccountInfoService accountInfoService;
	
	@Autowired
	private UtilMethods utilMethods;

	@RequestMapping(value = "/balanceInquiry", method = RequestMethod.POST)
	public GenericResponse<BalanceInquiryResponse> BalanceInquiry(HttpServletRequest httpRequestData,
			@Valid @RequestBody BalanceInquiryRequest balanceInquiryRequest, @CurrentlyLoggedUser DomainUser domainUser,
			@RequestParam(value = "channel", required = false) Channel channel) throws Exception {

     	return accountInfoService.balanceInquiry(domainUser,balanceInquiryRequest, utilMethods.getClientIp(httpRequestData), utilMethods);
    	
	}
}

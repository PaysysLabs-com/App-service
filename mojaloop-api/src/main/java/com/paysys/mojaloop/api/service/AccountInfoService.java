package com.paysys.mojaloop.api.service;

import com.paysys.mojaloop.api.domain.DomainUser;
import com.paysys.mojaloop.api.utils.UtilMethods;
import com.paysyslabs.mojaloop.model.request.BalanceInquiryRequest;
import com.paysyslabs.mojaloop.model.response.BalanceInquiryResponse;
import com.paysyslabs.mojaloop.model.response.GenericResponse;

public interface AccountInfoService {

	public GenericResponse<BalanceInquiryResponse> balanceInquiry (DomainUser domainUser, BalanceInquiryRequest balanceInquiryRequest, String ipAddress, UtilMethods UtilsMethods);
}

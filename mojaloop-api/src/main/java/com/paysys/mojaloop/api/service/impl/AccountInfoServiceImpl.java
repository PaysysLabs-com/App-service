package com.paysys.mojaloop.api.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.paysys.mojaloop.api.domain.DomainUser;
import com.paysys.mojaloop.api.service.AccountInfoService;
import com.paysys.mojaloop.api.utils.UtilMethods;
import com.paysyslabs.mojaloop.commons.ResponseCodes;
import com.paysyslabs.mojaloop.model.request.BalanceInquiryRequest;
import com.paysyslabs.mojaloop.model.response.BalanceInquiryResponse;
import com.paysyslabs.mojaloop.model.response.GenericResponse;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {

	private static final Logger LOG = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

	@Override
	public GenericResponse<BalanceInquiryResponse> balanceInquiry(DomainUser domainUser,
			BalanceInquiryRequest balanceInquiryRequest, String ipAddress, UtilMethods UtilsMethods) {
		
		Date requestDateTime = new Date();

		GenericResponse<BalanceInquiryResponse> response = new GenericResponse<BalanceInquiryResponse>();
		
		BalanceInquiryResponse balanceResponse = null;
		try {
			
			LOG.info("\n\n[\nRequest({}) from Mobile App: '{}' \n]", "BalanceInquiry", balanceInquiryRequest.toString());

			balanceResponse.setAvailableBalance("1000.00");
			balanceResponse.setCurrentBalance("1001.00");

			return response = new GenericResponse<BalanceInquiryResponse>(ResponseCodes.OK, balanceResponse);
		} catch (Exception e) {
			// TODO: handle exception
			LOG.error("{}", e);
			response = new GenericResponse<>(ResponseCodes.UNABLE_TO_PROCESS_CODE, null);
		} finally {
			LOG.info("OUT - {}", "BalanceInquiry");
		}
		
		return response;

	}
}

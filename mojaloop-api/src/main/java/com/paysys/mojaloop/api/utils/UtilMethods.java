package com.paysys.mojaloop.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import com.paysyslabs.mojaloop.model.response.GenericResponse;

@Component
public class UtilMethods {

	private static final Logger LOG = LoggerFactory.getLogger(UtilMethods.class);

	public static final int WRONG_PASSWORD_LIMIT = 3;
	private static int counter = (new Random()).nextInt(100);
	private static String sRRNformat = "ddHHmms";

	final static int RETRIEVAL_REFERENCE_LENGTH = 12;
	final static int STAN_LENGTH = 6;

	public void logFailed(GenericResponse<?> response, Logger LOG) {
		LOG.error("FAILED -> ResponseCode: " + response.getResponseCode() + ", ResponseDesc:"
				+ response.getResponseDescription());

	}

	public void logException(String where, Exception e, Logger LOG) {
		LOG.error("EXCEPTION in " + where + " -> Message: " + e.getMessage() + "\n" + e.getStackTrace());

	}

	public void logSuccess(GenericResponse<?> response, Logger LOG) {
		LOG.info("SUCCESS -> ResponseCode: " + response.getResponseCode() + ", ResponseDesc:"
				+ response.getResponseDescription());

	}

	public void generalLog(String text, Logger LOG) {
		LOG.info(text);
	}

	public synchronized String getRRN() {
		if (counter < 9999) {
			counter++;
		} else {
			counter = 0;
		}
		return StringUtils.leftPad((getDateTime() + String.valueOf(counter)), RETRIEVAL_REFERENCE_LENGTH, '0');
	}

	public synchronized String getStan() {
		if (counter < 9999) {
			counter++;
		} else {
			counter = 0;
		}
		return getRRN().substring(6);
	}

	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat(sRRNformat);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public long getEpoochDateTime(Date date) {
		return date.getTime();
	}

	public String getHash(String text) {
		return DigestUtils.sha256Hex(text);
	}

	public static String getHash(String text, boolean isStatic) {
		return DigestUtils.sha256Hex(text);
	}

	public Date getFormattedDueDate(String dueDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		try {
			date = format.parse("20" + dueDate);
		} catch (ParseException e) {
			LOG.error("{}", e);
		}
		return date;
	}

	public String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	public String getClientIp(HttpServletRequest request) {

		String remoteAddr = "";
		// X-FORWARDED-FOR
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		LOG.info("IPADDRESS(before): '{}'", remoteAddr);

		if (remoteAddr.contains(":") && remoteAddr.contains(",")) {
			remoteAddr = new StringTokenizer(remoteAddr, ",").nextToken().trim();
			remoteAddr = remoteAddr.substring(0, remoteAddr.indexOf(":"));
		}

		LOG.info("IPADDRESS(after): '{}'", remoteAddr);

		return remoteAddr;
	}

}

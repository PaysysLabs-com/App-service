package com.paysys.mojaloop.api.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paysys.mojaloop.api.controller.error.CustomErrorController;
import com.paysys.mojaloop.api.controller.error.ErrorJson;
import com.paysys.mojaloop.api.exception.MojaloopAuthenticationException;
import com.paysys.mojaloop.api.exception.MojaloopGeneralException;
import com.paysyslabs.mojaloop.commons.ResponseCodes;
import com.paysyslabs.mojaloop.model.response.GenericResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String resourcePath = new UrlPathHelper().getPathWithinApplication(request);

			if (resourcePath.equals(CustomErrorController.PATH)) {
				filterChain.doFilter(request, response);
				return;
			}

			filterChain.doFilter(request, response);

		} catch (MojaloopAuthenticationException e) {
			LOG.error("Mojaloop AuthenticationException", e);
			respond(response, e.getCode(), e.getMessage());
		}

		catch (MojaloopGeneralException e) {
			LOG.error("Mojaloop GeneralException", e);
			respond(response, e.getCode(), e.getMessage());
		}

		catch (Exception e) {
			LOG.error("Exception", e);
			respond(response, ResponseCodes.UNABLE_TO_PROCESS_CODE, e.getMessage());
		}

	}

	private void respond(HttpServletResponse httpResponse, String code, String message) throws IOException {
		SecurityContextHolder.clearContext();

		Map<String, Object> map = new HashMap<>();
		map.put("timestamp", new Date());
		map.put("message", ResponseCodes.RESPONSE_MAP.get(code));
		map.put("error", "Internal Error");

		ErrorJson errorJson = new ErrorJson(500, map);

		GenericResponse<ErrorJson> response = new GenericResponse<ErrorJson>(code, errorJson);
		String tokenJsonResponse = new ObjectMapper().writeValueAsString(response);
		httpResponse.addHeader("Content-Type", "application/json");
		httpResponse.getWriter().print(tokenJsonResponse);
	}

}

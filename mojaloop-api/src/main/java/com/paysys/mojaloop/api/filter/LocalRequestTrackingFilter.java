package com.paysys.mojaloop.api.filter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;
import org.springframework.web.filter.GenericFilterBean;

public class LocalRequestTrackingFilter extends GenericFilterBean{

    public static final String COUNTER_SESSION_KEY = "trackingNumber";
    private AtomicLong requestCounter = new AtomicLong(0);
    
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		MDC.put(COUNTER_SESSION_KEY, String.format("%d", requestCounter.incrementAndGet()));
        chain.doFilter(request, response);
	}

}

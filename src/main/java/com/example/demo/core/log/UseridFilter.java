package com.example.demo.core.log;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * ユーザIDフィルター
 */
@Component
public class UseridFilter implements OrderedFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			SecurityContext securityContext = SecurityContextHolder.getContext();
			Authentication authentication = securityContext.getAuthentication();
			if (authentication != null) {
				String userid = authentication.getName();
				request.setAttribute("access-converter-userid", userid);
				MDC.put("userid", userid);
			}
			chain.doFilter(request, response);
		} finally {
			MDC.remove("userid");
		}
	}

	@Override
	public int getOrder() {
		return 1;
	}
}

package com.example.demo.core.log;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.MDC;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.stereotype.Component;

@Component
public class SessionidFilter implements OrderedFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			if (session != null) {
				String sessionid = session.getId();
				request.setAttribute("access-converter-sessionid", sessionid);
				MDC.put("sessionid", sessionid);
			}
			chain.doFilter(request, response);
		} finally {
			MDC.remove("sessionid");
		}
	}

	@Override
	public int getOrder() {
		return 1;
	}
}

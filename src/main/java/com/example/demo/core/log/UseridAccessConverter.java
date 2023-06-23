package com.example.demo.core.log;

import javax.servlet.http.HttpServletRequest;

import ch.qos.logback.access.pattern.AccessConverter;
import ch.qos.logback.access.spi.IAccessEvent;

/**
 * ユーザID変換
 */
public class UseridAccessConverter extends AccessConverter {

	@Override
	public String convert(IAccessEvent event) {
		HttpServletRequest request = event.getRequest();
		if (request != null) {
			Object value = request.getAttribute("access-converter-userid");
			if (value instanceof String) {
				return (String) value;
			}
		}
		return IAccessEvent.NA;
	}
}

package com.example.demo.core.log;

import javax.servlet.http.HttpServletRequest;

import ch.qos.logback.access.pattern.AccessConverter;
import ch.qos.logback.access.spi.IAccessEvent;


public class SessionidAccessConverter extends AccessConverter {

	static final String REQUEST_ATTRIBUTE_NAME = SessionidAccessConverter.class.getName();

	@Override
	public String convert(IAccessEvent event) {
		HttpServletRequest request = event.getRequest();
		if (request != null) {
			Object value = request.getAttribute("access-converter-sessionid");
			if (value instanceof String) {
				return (String) value;
			}
		}
		return IAccessEvent.NA;
	}
}

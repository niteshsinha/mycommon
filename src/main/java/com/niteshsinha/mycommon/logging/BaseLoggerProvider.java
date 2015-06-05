package com.niteshsinha.mycommon.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseLoggerProvider {

	public static Logger getLogger(Class callerClass) {
		return LoggerFactory.getLogger(callerClass);
	}
	
	public static Logger getLogger(String callerClassName) {
		return LoggerFactory.getLogger(callerClassName);
	}
}

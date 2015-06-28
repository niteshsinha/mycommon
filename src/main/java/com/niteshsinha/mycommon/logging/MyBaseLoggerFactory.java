package com.niteshsinha.mycommon.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class MyBaseLoggerFactory implements LoggerFactory {

	public Logger makeNewLoggerInstance(String arg0) {
		// TODO Auto-generated method stub
		return new MyBaseLogger(arg0);
	}

}

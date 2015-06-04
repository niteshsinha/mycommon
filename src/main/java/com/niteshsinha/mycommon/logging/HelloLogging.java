package com.niteshsinha.mycommon.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloLogging {

	public static Logger logger = LoggerFactory.getLogger(HelloLogging.class);
	
	public static void main(String args[]) {
		
		for(int i=1; i<=10; i++) {
			if(i % 2 == 0) {
				logger.info("Hello {}",i);
			} else {
				logger.debug("I am at index {}",i);
			}
		}
	}
}

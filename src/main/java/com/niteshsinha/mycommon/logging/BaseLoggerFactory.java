package com.niteshsinha.mycommon.logging;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class BaseLoggerFactory implements ILoggerFactory{

	public Logger getLogger(String name) {
		// TODO Auto-generated method stub
		return new BaseLogger(name);
		//return null;
	}

}

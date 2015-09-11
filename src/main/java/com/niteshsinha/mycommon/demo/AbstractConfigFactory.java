package com.niteshsinha.mycommon.demo;

public class AbstractConfigFactory {

	public ConfigFactory getConfigFactory(String type) {
		if("sql".equals(type))
			return new SqlConfigImpl();
		else
			return new NosqlConfigImpl();
	}
}

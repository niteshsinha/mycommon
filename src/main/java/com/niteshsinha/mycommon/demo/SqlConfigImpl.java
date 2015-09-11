package com.niteshsinha.mycommon.demo;

public class SqlConfigImpl extends ConfigFactory {

	public DBConfig getConfig(String type) {
		if("mysql".equals(type))
			return new MysqlConfig();
		else if("hsql".equals(type))
			return new HsqlConfig();
		else 
			return null;
	}

}

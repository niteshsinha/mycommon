package com.niteshsinha.mycommon.demo;

public class NosqlConfigImpl extends ConfigFactory {

	public DBConfig getConfig(String type) {
		// TODO Auto-generated method stub
		if("mongo".equals(type))
			return new MongoConfig();
		else if("cassandra".equals(type))
			return new CassandraConfig();
		else
			return null;
	}

}

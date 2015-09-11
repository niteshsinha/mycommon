package com.niteshsinha.mycommon.demo;

public class ConfigFactoryDemo {

	public static void main(String[] args) {
		AbstractConfigFactory absConfigFActory = new AbstractConfigFactory();
		
		ConfigFactory sqlConfigFactory = absConfigFActory.getConfigFactory("sql");
		ConfigFactory nosqlConfigFactory = absConfigFActory.getConfigFactory("nosql");
		
		DBConfig mysqlConfig = sqlConfigFactory.getConfig("mysql");
		DBConfig mongoConfig = nosqlConfigFactory.getConfig("mongo");
		
		DBConfig hsqlConfig = sqlConfigFactory.getConfig("hsql");
		DBConfig cassandraConfig = nosqlConfigFactory.getConfig("cassandra");
		
		System.out.println(mysqlConfig.createConfig());
		System.out.println(mongoConfig.createConfig());
		System.out.println(cassandraConfig.createConfig());
		System.out.println(hsqlConfig.createConfig());
	}
}

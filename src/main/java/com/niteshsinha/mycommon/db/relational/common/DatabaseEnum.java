package com.niteshsinha.mycommon.db.relational.common;

import java.util.HashMap;
import java.util.Map;

public enum DatabaseEnum {
	
	DB_MYSQL("sql","mysql",1),
	DB_PSQL("sql","postgresql",2),
	DB_CASSANDRA("nosql","cassandra",3),
	DB_MONGO("nosql","mongodb",4),
	DB_HSQL("nosql","hsqldb",5);
	
	private static Map<String, DatabaseEnum> codeToEnums = new HashMap<String, DatabaseEnum>();
	
	static {
		codeToEnums.put("mysql", DB_MYSQL);
		codeToEnums.put("postgresql", DB_PSQL);
		codeToEnums.put("cassandra", DB_CASSANDRA);
		codeToEnums.put("mongo", DB_MONGO);
		codeToEnums.put("hsql", DB_HSQL);
	}
	
	private String databaseType;
	private String databaseName;
	private int databaseCode;

	private DatabaseEnum(String type,String name,int code) {
		this.databaseType = type;
		this.databaseName = name;
		this.databaseCode = code;
	}
	
	public int getDatabaseCode() {
		return databaseCode;
	}
	
	public String getDatabaseType() {
		return databaseType;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public static DatabaseEnum getEnumFor(String s) {
		return codeToEnums.get(s);
	}

	public String toString() {
		return super.toString();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(DatabaseEnum.getEnumFor("mysql"));
			System.out.println(DatabaseEnum.DB_MONGO);
		} catch(IllegalArgumentException e) {
			System.out.println("Error");
		}
		
	}

}

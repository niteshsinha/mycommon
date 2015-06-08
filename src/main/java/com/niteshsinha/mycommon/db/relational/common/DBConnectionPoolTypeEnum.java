package com.niteshsinha.mycommon.db.relational.common;

import java.util.HashMap;
import java.util.Map;

public enum DBConnectionPoolTypeEnum {

	DB_CONNECTION_POOL_MODE_LOCAL("local","1"), 
	DB_CONNECTION_POOL_MODE_GLOBAL("global","2");
	
	private static Map<String, DBConnectionPoolTypeEnum> codeToEnums = new HashMap<String, DBConnectionPoolTypeEnum>();
	
	static {
		codeToEnums.put("local", DB_CONNECTION_POOL_MODE_LOCAL);
		codeToEnums.put("global", DB_CONNECTION_POOL_MODE_GLOBAL);
	}
	
	private String connectionPoolModeCode;
	private String connectionPoolModeName;


	public String getDBConnectionPoolTypeCode() {
		return connectionPoolModeCode;
	}
	

	private DBConnectionPoolTypeEnum(String typeName,String typeCode) {
		this.connectionPoolModeName = typeName;
		this.connectionPoolModeCode = typeCode;
	}


	public static DBConnectionPoolTypeEnum getEnumFor(String s) {
		return codeToEnums.get(s);
	}


	/**
	 * @return the flowModeName
	 */
	public String getDBConnectionPoolTypeName() {
		return connectionPoolModeName;
	}


	public String toString() {
		return super.toString();
	}
	
	public static void main(String[] args) {
		try {
		System.out.println(DBConnectionPoolTypeEnum.getEnumFor("local"));
		System.out.println(DBConnectionPoolTypeEnum.getEnumFor("global"));
		System.out.println(DBConnectionPoolTypeEnum.DB_CONNECTION_POOL_MODE_GLOBAL);
		} catch(IllegalArgumentException e) {
			System.out.println("Error");
		}
		
	}

}

package com.niteshsinha.mycommon.logging;

public class MyBaseLoggerConfig {

	private static final MyBaseLoggerConfig instance = new MyBaseLoggerConfig();
	private String applicationName="";
	private String machineHostName="";
	
	private MyBaseLoggerConfig() {
		super();
	}
	
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getMachineHostName() {
		return machineHostName;
	}
	public void setMachineHostName(String machineHostName) {
		this.machineHostName = machineHostName;
	}
	public static MyBaseLoggerConfig getInstance() {
		return instance;
	}
	
	
}

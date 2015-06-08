package com.niteshsinha.mycommon.exception;

public class DBException extends MyBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7667322386247685395L;
	
	private String sqlState = null;
	private int errorCode;

	public DBException(String message) {
		super(message);
	}

	public DBException() {
		super();
	}
	
	public DBException(Throwable t){
		super(t);
	}
	
	public DBException(Throwable t, String message){
		super(t, message);
	}

	public String getSqlState() {
		return sqlState;
	}

	public void setSqlState(String sqlState) {
		this.sqlState = sqlState;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}	
	
}

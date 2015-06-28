package com.niteshsinha.mycommon.exception;

public class NSDBException extends MyBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -290735315697909034L;
	private String sqlState = null;
	private int errorCode;

	public NSDBException(String message) {
		super(message);
	}

	public NSDBException() {
		super();
	}
	
	public NSDBException(Throwable t){
		super(t);
	}
	
	public NSDBException(Throwable t, String message){
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

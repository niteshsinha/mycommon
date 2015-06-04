package com.niteshsinha.mycommon.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MyBaseException extends Exception {

	private static final long serialVersionUID = -8574556335780393804L;
	private int exceptionCode;
	private int statusCode;

	public MyBaseException() {

	}

	public MyBaseException(String message) {
		super(message);
	}

	public MyBaseException(Throwable nestestException) {
		super(getRootCause(nestestException));
		extractProperties(nestestException);
	}

	public MyBaseException(Throwable nestestException, String message) {
		super(message, getRootCause(nestestException));
		extractProperties(nestestException);
	}

	public int getExceptionCode() {
		return exceptionCode;
	}

	public String getExceptionCodeAsString() {
		return String.valueOf(this.exceptionCode);
	}

	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public void setExceptionCode(String code) {
		try {
			this.exceptionCode = Integer.parseInt(code);
		} catch (NumberFormatException e) {
			this.exceptionCode = 0;
		}
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = Integer.parseInt(statusCode);
	}

	public String getStatusCodeAsString() {
		return String.valueOf(this.statusCode);
	}

	public Throwable getNestedException() {
		return this.getCause();
	}

	public String getStackTraceString() {
		StringWriter s = new StringWriter();
		printStackTrace(new PrintWriter(s));
		return s.toString();
	}

	private static final Throwable getRootCause(Throwable throwable) {
		if (throwable instanceof MyBaseException) {
			return throwable.getCause() == null ? throwable : throwable.getCause();
		}
		return throwable;
	}

	private void extractProperties(Throwable throwable) {
		if (throwable instanceof MyBaseException && ((MyBaseException) throwable).getExceptionCode() != 0) {
			this.setExceptionCode(((MyBaseException) throwable).getExceptionCode());
		}

		if (throwable instanceof MyBaseException && ((MyBaseException) throwable).getStatusCode() != 0) {
			this.setStatusCode(((MyBaseException) throwable).getStatusCode());
		}
	}
}

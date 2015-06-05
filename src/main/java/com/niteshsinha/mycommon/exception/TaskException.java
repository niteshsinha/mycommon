package com.niteshsinha.mycommon.exception;

public class TaskException extends MyBaseException {

	private static final long serialVersionUID = 9047034400128497825L;
	
	private String message;
	private Integer statuscode;
	private boolean doTask = false;
	
	public boolean isDoTask() {
		return doTask;
	}

	public void setDoTask(boolean doTask) {
		this.doTask = doTask;
	}

	public Integer getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(Integer statuscode) {
		this.statuscode = statuscode;
	}

	public TaskException(String message) {
		this.message = message;
	}
	
	public TaskException(String message, Throwable t) {
		super(t,message);
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}	
}

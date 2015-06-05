package com.niteshsinha.mycommon.thread;

import java.util.concurrent.Callable;

import com.niteshsinha.mycommon.exception.TaskException;

public interface ICallableTask extends Callable {

	public void execute() throws TaskException;
	public void execute(long milliseconds) throws TaskException;
	public void kill();
	public boolean isStop();
	public void setStop(boolean stop);
	
}

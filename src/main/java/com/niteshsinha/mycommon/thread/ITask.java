package com.niteshsinha.mycommon.thread;

import com.niteshsinha.mycommon.exception.TaskException;

public interface ITask extends Runnable {
	public void execute() throws TaskException;
	public void execute(long milliseconds) throws TaskException;
	public void kill();
	public boolean isStop();
	public void setStop(boolean stop);
}

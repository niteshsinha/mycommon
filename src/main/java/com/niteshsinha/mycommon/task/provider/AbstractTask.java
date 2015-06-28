package com.niteshsinha.mycommon.task.provider;

import com.niteshsinha.mycommon.exception.TaskException;
import com.niteshsinha.mycommon.thread.ITask;

public abstract class AbstractTask implements ITask{

	protected long timeToExecute = 0;
	protected boolean stop = false;
	
	public void execute(long ms) throws TaskException {
		try {
			if(ms > 0) { Thread.sleep(ms); }
			this.execute();
		} catch(InterruptedException iex) {
			throw new TaskException("Exception " + iex.getMessage());
		}
	}
	
	public void run() {
		try {
		    this.execute(this.timeToExecute);
		} catch (TaskException tex) {
			//Thread Specific Logging ... The Thread Failed to run
			//for some reason
			//TODO: log4j log message goes here
			tex.printStackTrace();
		}
	}
	
	public Object retrieveTaskHandle() {
		return null;
	}
	
	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public void kill() {}
}

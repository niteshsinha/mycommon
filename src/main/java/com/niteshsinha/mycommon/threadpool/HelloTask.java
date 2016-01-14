package com.niteshsinha.mycommon.threadpool;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.exception.TaskException;
import com.niteshsinha.mycommon.logging.BaseLoggerProvider;
import com.niteshsinha.mycommon.thread.ITask;

public class HelloTask implements ITask {
	
	static Logger logger = BaseLoggerProvider.getLogger(HelloTask.class);

	public void run() {
		// TODO Auto-generated method stub
		//logger.info("Inside Run..");
		//Long duration = (long) (Math.random() * 10);
		Long duration = 5l;
		logger.info("Duration: "+duration);
        //System.out.println("Doing a task during HelloTask at: "+ System.nanoTime());
        try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logger.info("Completed");
		
	}

	public void execute() throws TaskException {
		// TODO Auto-generated method stub
		
	}

	public void execute(long milliseconds) throws TaskException {
		// TODO Auto-generated method stub
		
	}

	public void kill() {
		// TODO Auto-generated method stub
		
	}

	public boolean isStop() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setStop(boolean stop) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString(){
		return "Nitesh";
	}
}

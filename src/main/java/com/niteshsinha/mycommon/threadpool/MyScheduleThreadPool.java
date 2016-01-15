package com.niteshsinha.mycommon.threadpool;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.logging.BaseLoggerProvider;
import com.niteshsinha.mycommon.thread.ITask;

public class MyScheduleThreadPool {

	private final Logger logger = BaseLoggerProvider.getLogger(MyScheduleThreadPool.class);
	private final ScheduledExecutorService scheduledThreadPoolExecutor;

	public MyScheduleThreadPool(int corePoolSize, String poolName, boolean doStatistics){
		if(logger.isDebugEnabled()){
    		logger.debug("Inside contructor: poolName:" + poolName);
    	}
		scheduledThreadPoolExecutor = new MyScheduleThreadPoolExecutor(corePoolSize, poolName, doStatistics, new MyThreadPoolFactory(poolName));
	}

	public ScheduledFuture<?> schedule(ITask task, long delay, TimeUnit unit) {
		if(logger.isDebugEnabled()){
    		logger.debug("Inside schedule" + String.valueOf(task));
    	}

		/**
		 * Shall we use ThreadDecorator????????
		 */
		ScheduledFuture<?> futureTask = scheduledThreadPoolExecutor.schedule(task, delay, unit);

		if(logger.isDebugEnabled()){
    		logger.debug("Exiting schedule" + String.valueOf(task));
    	}
		
		return futureTask;
	}
	
	public ScheduledFuture<?> scheduleAtFixedRate(ITask task, long initialDelay, long delay, TimeUnit unit) {
		if(logger.isDebugEnabled()){
    		logger.debug("Inside scheduleAtFixedRate" + String.valueOf(task));
    	}
		
		/**
		 * Shall we use ThreadDecorator????????
		 */
		ScheduledFuture<?> futureTask = scheduledThreadPoolExecutor.scheduleAtFixedRate(task, initialDelay, delay, unit);

		if(logger.isDebugEnabled()){
    		logger.debug("Exiting scheduleAtFixedRate" + String.valueOf(task));
    	}
		
		return futureTask;
	}

	public ScheduledFuture<?> scheduleWithFixedDelay(ITask task, long initialDelay, long delay, TimeUnit unit) {
		if(logger.isDebugEnabled()){
    		logger.debug("Inside scheduleWithFixedDelay" + String.valueOf(task));
    	}
		
		/**
		 * Shall we use ThreadDecorator????????
		 */
		ScheduledFuture<?> futureTask = scheduledThreadPoolExecutor.scheduleWithFixedDelay(task, initialDelay, delay, unit);

		if(logger.isDebugEnabled()){
    		logger.debug("Exiting scheduleWithFixedDelay" + String.valueOf(task));
    	}
		
		return futureTask;
	}

	public void shutdown(){
		logger.debug("Inside method shutDown.");
		scheduledThreadPoolExecutor.shutdown();
		logger.debug("Exiting method shutDown.");
	}

	public List<Runnable> shutdownNow(){
		logger.debug("Inside method shutDownNow.");
		return scheduledThreadPoolExecutor.shutdownNow();
	}


}

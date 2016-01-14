package com.niteshsinha.mycommon.threadpool;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.logging.BaseLoggerProvider;
import com.niteshsinha.mycommon.thread.ITask;

public class MyThreadPool {

	private final Logger logger = BaseLoggerProvider.getLogger(MyThreadPool.class);
	private final ThreadPoolExecutor threadPoolExecutor;
	
	public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, String poolName, boolean doStatistics) {
		if(logger.isDebugEnabled()){
    		logger.debug("Inside contructor: poolName:" + poolName);
    	}
		threadPoolExecutor = new MyThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, new MyThreadPoolFactory(poolName), poolName, doStatistics);
	}
	
	public Future<?> submit(ITask task) {
		if(logger.isDebugEnabled()){
    		//logger.debug("Inside submit: " + String.valueOf(task));
    	}

		/**
		 * Shall we use a ThreadDecorator
		 */
		Future<?> future = threadPoolExecutor.submit(task);
		
		/**
		 * TODO: Log
		 */
		return future;
	}

	protected void shutdown(){
		if(logger.isDebugEnabled()){
    		logger.debug("Inside shutdown: ");
    	}
		threadPoolExecutor.shutdown();
	}

	protected List<Runnable> shutdownNow(){
		if(logger.isDebugEnabled()){
    		logger.debug("Inside shutdownNow: ");
    	}
		return threadPoolExecutor.shutdownNow();
	}
	
	protected int getActiveThreadsCount() {
	    return threadPoolExecutor.getActiveCount();
	}
	
	protected int getMaxPoolSize() {
	    return threadPoolExecutor.getMaximumPoolSize();
	}
	
}

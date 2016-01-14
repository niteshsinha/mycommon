package com.niteshsinha.mycommon.threadpool;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.logging.BaseLoggerProvider;

public class HelloMain {
	
	private static Logger logger = BaseLoggerProvider.getLogger(HelloMain.class);
	
	public static void main(String args []) {
		
		int maxThreads = MyThreadPoolExecutorManager.getInstance().getMaxPoolThreadsCount();
		logger.info("Max Thread Count: "+maxThreads);
		
		int activeThreadCount = MyThreadPoolExecutorManager.getInstance().getActiveThreadsCount();
		logger.info("Active Thread Count: "+activeThreadCount);
		
		int idleThreadCount = maxThreads - activeThreadCount;
		
		logger.info("Running Hello in ThreadName: "+Thread.currentThread().getName());
		
		if(idleThreadCount > 0) {
			
			for(int i=0; i<maxThreads-activeThreadCount; i++){
				MyThreadPoolExecutorManager.getInstance().submit(new HelloTask());
			}
			
		} else {
			logger.warn("All Worker Threads Busy. No idle threads");
		}
		
		logger.info("Active Thread Count:" + MyThreadPoolExecutorManager.getInstance().getActiveThreadsCount());
		logger.info("Max Thread Count: "+MyThreadPoolExecutorManager.getInstance().getMaxPoolThreadsCount());
		MyThreadPoolExecutorManager.getInstance().shutDownThreadPool();
		
	}
}

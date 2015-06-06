package com.niteshsinha.mycommon.threadpool;

import java.util.concurrent.Future;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.logging.BaseLoggerProvider;
import com.niteshsinha.mycommon.thread.ITask;

public class MyThreadPoolExecutorManager {

	static Logger logger = BaseLoggerProvider.getLogger(MyThreadPoolExecutorManager.class);
	
	private static final int IMMEDIATE_POOL_ONLY=1;
	private static final String DEFAULT_CONFIG_FILENAME="mythreadpoolconfig.properties";
	
	private final int threadPoolType;
	
	private final static MyThreadPoolExecutorManager instance = new MyThreadPoolExecutorManager();
	private final MyThreadPool threadPoolExecutor;
	
	private MyThreadPoolExecutorManager() {
		//shoudl be read from properties files
		logger.info("Initialized a ThreadPool of 5");
		threadPoolExecutor = new MyThreadPool(5, 10, 60, "MY_TP", true);
		threadPoolType = IMMEDIATE_POOL_ONLY;
	}
	
	public void init() {
		
	}
	
	public static MyThreadPoolExecutorManager getInstance() {
		return instance;
	}
	
	public Future<?> submit(ITask task) {
		if (threadPoolExecutor != null){
			return threadPoolExecutor.submit(task);
		}else{
			throw new IllegalStateException("Immediate thread pool not initialized.PoolType:" + threadPoolType);
		}
	}
	
	public void shutDownThreadPool(){
		if (threadPoolExecutor != null){
			threadPoolExecutor.shutdown();
		}else{
			logger.info("Immediate threadpool was not running, so no need to stop.PoolType:" + threadPoolType);
		}
	}

	public void shutDownThreadPoolNow(){
		if (threadPoolExecutor != null){
			threadPoolExecutor.shutdownNow();
		}else{
			logger.info("Immediate threadpool was not running, so no need to stop.PoolType:" + threadPoolType);
		}
	}
	
	public int getActiveThreadsCount() {
	    int count = 0;
	    if (threadPoolExecutor != null && (this.threadPoolType == IMMEDIATE_POOL_ONLY)){
	        count += threadPoolExecutor.getActiveThreadsCount();
        }
	    return count;        
	}
	
	public int getMaxPoolThreadsCount() {
        int count = 0;
        if (threadPoolExecutor != null && (this.threadPoolType == IMMEDIATE_POOL_ONLY)){
            count += threadPoolExecutor.getMaxPoolSize();
        }
        return count;        
    }
}
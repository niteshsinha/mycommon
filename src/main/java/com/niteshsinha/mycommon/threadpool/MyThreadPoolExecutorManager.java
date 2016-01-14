package com.niteshsinha.mycommon.threadpool;

import java.util.Properties;
import java.util.concurrent.Future;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.config.ConfigManager;
import com.niteshsinha.mycommon.config.IConfigFactory;
import com.niteshsinha.mycommon.logging.BaseLoggerProvider;
import com.niteshsinha.mycommon.thread.ITask;
import com.niteshsinha.mycommon.threadpool.config.MyThreadPoolConfig;

public class MyThreadPoolExecutorManager {

	static Logger logger = BaseLoggerProvider.getLogger(MyThreadPoolExecutorManager.class);
	
	private static final int IMMEDIATE_POOL_ONLY=1;
	private static final String DEFAULT_CONFIG_FILENAME="mythreadpoolconfig.properties";
	
	private final int threadPoolType;
	
	public static final String DEFAULT_CONF_FILENAME = "mythreadpoolconfig.properties";
	public static final String KEY_CONF_FILENAME = "my.threadPool.ConfigFileName";
	private final ConfigManager<MyThreadPoolConfig> configManager;
	private final MyThreadPoolConfig myThreadPoolConfig;
	
	private final static MyThreadPoolExecutorManager instance = new MyThreadPoolExecutorManager();
	private final MyThreadPool threadPoolExecutor;
	
	private MyThreadPoolExecutorManager() {
		
		String configFile = getConfigFileName();
		this.configManager = new ConfigManager<MyThreadPoolConfig>(configFile, new IConfigFactory<MyThreadPoolConfig>() {

			public MyThreadPoolConfig createConfig(Properties properties) {
				return new MyThreadPoolConfig(properties);
			}
			
		});
		this.myThreadPoolConfig = this.configManager.getConfig();
		logger.info("My Thread Pool Config : " + String.valueOf(this.myThreadPoolConfig));
		//threadPoolExecutor = new MyThreadPool(25, 50, 60, "MY_TP", true);
		threadPoolExecutor = new MyThreadPool(this.myThreadPoolConfig.getThreadPoolCoreSize(),
				this.myThreadPoolConfig.getThreadPoolMaxSize(), 
				0l, this.myThreadPoolConfig.getImmediateThreadPoolName(), true);
		threadPoolType = IMMEDIATE_POOL_ONLY;
	}
	
	private String getConfigFileName() {
		Object confFileName = System.getProperties().get(KEY_CONF_FILENAME);

		if (confFileName instanceof String) {
			return (String) confFileName;
		}

		return DEFAULT_CONF_FILENAME;
	}
	
	public void init() {
		
	}
	
	public static MyThreadPoolExecutorManager getInstance() {
		return instance;
	}
	
	public MyThreadPoolConfig getConfig(){
		return myThreadPoolConfig;
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

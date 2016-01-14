package com.niteshsinha.mycommon.threadpool.config;

import java.util.Properties;

import com.niteshsinha.mycommon.config.ConfigUtil;
import com.niteshsinha.mycommon.config.IConfig;

public class MyThreadPoolConfig implements IConfig {

	private static final long serialVersionUID = -8975470140126508009L;

	private final int threadPoolCoreSize;
	private static final String KEY_THREAD_POOL_CORE_SIZE = "default.threadPoolCoreSize";
	private static final int DEFAULT_THREAD_POOL_CORE_SIZE = 20;

	private final int threadPoolMaxSize;
	private static final String KEY_THREAD_POOL_MAX_SIZE = "default.threadPoolMaxSize";
	private static final int DEFAULT_THREAD_POOL_MAX_SIZE = 50;

	private final int scheduledThreadPoolCoreSize;
	private static final String KEY_SCHEDULED_THREAD_POOL_CORE_SIZE = "default.scheduledThreadPoolSize";
	private static final int DEFAULT_SCHEDULED_THREAD_POOL_CORE_SIZE = 20;
	
	private final boolean isTPStatisticsEnabled;
	private static final String KEY_DO_THREAD_POOL_STATISTICS = "default.isTPStatisticsEnabled";
	private static final boolean DEFAULT_DO_THREAD_POOL_STATISTICS = false;
	
	private final String threadPoolName;
	private static final String KEY_THREAD_POOL_NAME = "default.immediateThreadPoolName";
	private static final String DEFAULT_THREAD_POOL_NAME = "MY_TP";
	
	private final String scheduledThreadPoolName;
	private static final String KEY_SCHEDULED_THREAD_POOL_NAME = "default.scheduledThreadPoolName";
	private static final String DEFAULT_SCHEDULED_THREAD_POOL_NAME = "MY_STP";
	
	private final long threadPoolKeepAliveTime;
	private static final String KEY_THREAD_POOL_KEEP_ALIVE_TIME = "default.keepAliveTime";
	private static final long DEFAULT_THREAD_POOL_KEEP_ALIVE_TIME = 0L;
	
	private final int threadPoolType;
	private static final String KEY_THREAD_POOL_TYPE = "default.threadPoolType";
	private static final int DEFAULT_THREAD_POOL_TYPE = 1;
	
	public MyThreadPoolConfig(Properties properties) {
		/**
		 * TODO : Log
		 */
		threadPoolCoreSize = ConfigUtil.getInt(properties, KEY_THREAD_POOL_CORE_SIZE, DEFAULT_THREAD_POOL_CORE_SIZE);
		threadPoolMaxSize = ConfigUtil.getInt(properties, KEY_THREAD_POOL_MAX_SIZE, DEFAULT_THREAD_POOL_MAX_SIZE);
		scheduledThreadPoolCoreSize = ConfigUtil.getInt(properties, KEY_SCHEDULED_THREAD_POOL_CORE_SIZE, DEFAULT_SCHEDULED_THREAD_POOL_CORE_SIZE);
		isTPStatisticsEnabled = ConfigUtil.getBoolean(properties, KEY_DO_THREAD_POOL_STATISTICS, DEFAULT_DO_THREAD_POOL_STATISTICS);
		threadPoolKeepAliveTime = ConfigUtil.getLong(properties, KEY_THREAD_POOL_KEEP_ALIVE_TIME, DEFAULT_THREAD_POOL_KEEP_ALIVE_TIME);
		threadPoolName = ConfigUtil.getString(properties, KEY_THREAD_POOL_NAME, DEFAULT_THREAD_POOL_NAME);
		scheduledThreadPoolName = ConfigUtil.getString(properties, KEY_SCHEDULED_THREAD_POOL_NAME, DEFAULT_SCHEDULED_THREAD_POOL_NAME);
		threadPoolType = ConfigUtil.getInt(properties, KEY_THREAD_POOL_TYPE, DEFAULT_THREAD_POOL_TYPE);
	}
	
	public String toString(){
		return "ThreadPoolConfig[coreSize="+this.threadPoolCoreSize +", poolMaxSize:" + this.threadPoolMaxSize +", scheduledPoolCoreSize:" + this.scheduledThreadPoolCoreSize + "\n"+
								 "Enable statistics:" + this.isTPStatisticsEnabled +", Keep Alive Time:" + this.threadPoolKeepAliveTime +"\n"+
								 "Immediate Thread PoolName:" + this.threadPoolName + ", Scheduled Pool Name:" + this.scheduledThreadPoolName; 
	}

	/**
	 * @return the threadPoolCoreSize
	 */
	public int getThreadPoolCoreSize() {
		return threadPoolCoreSize;
	}

	/**
	 * @return the threadPoolMaxSize
	 */
	public int getThreadPoolMaxSize() {
		return threadPoolMaxSize;
	}

	/**
	 * @return the scheduledThreadPoolCoreSize
	 */
	public int getScheduledThreadPoolCoreSize() {
		return scheduledThreadPoolCoreSize;
	}

	/**
	 * @return the threadPoolKeepAliveTime
	 */
	public long getThreadPoolKeepAliveTime() {
		return threadPoolKeepAliveTime;
	}

	/**
	 * @return the immediateThreadPoolName
	 */
	public String getImmediateThreadPoolName() {
		return threadPoolName;
	}

	/**
	 * @return the scheduledThreadPoolName
	 */
	public String getScheduledThreadPoolName() {
		return scheduledThreadPoolName;
	}

	/**
	 * @return the isTPStatisticsEnabled
	 */
	public boolean isTPStatisticsEnabled() {
		return isTPStatisticsEnabled;
	}

	/**
	 * @return the threadPoolType
	 */
	public int getThreadPoolType() {
		return threadPoolType;
	}
	
}

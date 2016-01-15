package com.niteshsinha.mycommon.threadpool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyScheduleThreadPoolExecutor extends ScheduledThreadPoolExecutor {

	private final boolean doStatistics;
    private final String poolName;
	
	public MyScheduleThreadPoolExecutor(int corePoolSize, String poolName, boolean doStatistics, ThreadFactory myThreadFactory){
		super(corePoolSize, myThreadFactory);
		this.doStatistics = doStatistics;
		this.poolName = poolName;
	}
	
	protected void afterExecute(Runnable runnable, Throwable throwable) {
        super.afterExecute(runnable, throwable);
        
        /**
         * TODO : we need to do exception handling
        if (throwable != null) {
            handleException(throwable, runnable);
        }
		*/
        
        if (doStatistics) {
            logStatistics(this, poolName);
        }
    }
	
	public static void logStatistics(ThreadPoolExecutor threadPoolExecutor, String poolName) {
        StringBuilder statistics = new StringBuilder();

        int maxPoolSize = threadPoolExecutor.getMaximumPoolSize();
        int corePoolSize = threadPoolExecutor.getCorePoolSize();
        int largestPoolSize = threadPoolExecutor.getLargestPoolSize();
        int poolSize = threadPoolExecutor.getPoolSize();

        long taskCount = threadPoolExecutor.getTaskCount();
        long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
        int activeCount = threadPoolExecutor.getActiveCount();

        long queueSize =  taskCount - (completedTaskCount + activeCount);

        statistics.append(" KeepAliveTime : ").append(threadPoolExecutor.getKeepAliveTime(TimeUnit.SECONDS)).append(" secs ")

        .append(" MaximumPoolSize : ").append(maxPoolSize)
        .append(" CorePoolSize : ").append(corePoolSize)
        .append(" LargestPoolSize : ").append(largestPoolSize)
        .append(" PoolSize : ").append(poolSize)

        .append(" TaskCount : ").append(taskCount)
        .append(" CompletedTaskCount : ").append(completedTaskCount)
        .append(" ActiveCount : ").append(activeCount)

        .append(" QueueSize : ").append(queueSize)
        .append(" end.");

        /**
         * TODO : Log
        Logger("logStatistics " + poolName + " " + statistics);
        */
    }

}

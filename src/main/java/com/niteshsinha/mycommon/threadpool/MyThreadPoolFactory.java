package com.niteshsinha.mycommon.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPoolFactory implements ThreadFactory {
	
	private final ThreadFactory myThreadPoolFactory;
	private final String poolName;
	private final AtomicInteger threadNumber;
	
	public MyThreadPoolFactory(String poolName) {
		this.poolName = poolName;
		myThreadPoolFactory = Executors.defaultThreadFactory();
		threadNumber = new AtomicInteger(1);
	}
	
	public Thread newThread(Runnable arg0) {
		// TODO Auto-generated method stub
		Thread thread = myThreadPoolFactory.newThread(arg0);
		String newThreadName = poolName + " : "+ threadNumber.getAndIncrement();
		thread.setName(newThreadName);
		return thread;
	}

}

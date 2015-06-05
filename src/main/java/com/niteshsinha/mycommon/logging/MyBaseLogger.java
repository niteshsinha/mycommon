package com.niteshsinha.mycommon.logging;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.slf4j.MDC;

public class MyBaseLogger extends Logger {
	
	private String loggerName = MyBaseLogger.class.getName()+".";
	private boolean trace;
	private boolean debug;
	private boolean error;
	private boolean fatal;
	private boolean info;
	private boolean warn;
	
	static Logger logger;
	
	protected MyBaseLogger(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public void trace(Object message) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.TRACE, message, null);
		removeThreadId();
		removeAppName();
	}

	public void trace(Object message, Throwable t) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.TRACE, "[" + message + "]", t);
		removeThreadId();
		removeAppName();
	}

	public void debug(Object message) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.DEBUG, message, null);
		removeThreadId();
		removeAppName();
	}

	public void debug(Object message, Throwable t) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.DEBUG, "[" + message + "]", t);
		removeThreadId();
		removeAppName();
	}

	public void info(Object message) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.INFO, message, null);
		removeThreadId();
		removeAppName();
	}

	public void info(Object message, Throwable t) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.INFO, "[" + message + "]", t);
		removeThreadId();
		removeAppName();
	}

	public void warn(Object message) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.WARN, message, null);
		removeThreadId();
		removeAppName();
	}

	public void warn(Object message, Throwable t) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.WARN, "[" + message + "]", t);
		removeThreadId();
		removeAppName();
	}

	public void error(Object message) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.ERROR, message, null);
		removeThreadId();
		removeAppName();
	}

	public void error(Object message, Throwable t) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.ERROR, "[" + message + "]", t);
		removeThreadId();
		removeAppName();
	}

	public void fatal(Object message) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.FATAL, message, null);
		removeThreadId();
		removeAppName();
	}

	public void fatal(Object message, Throwable t) {
	    addAppName();
		addThreadId();
		super.log(loggerName, Level.FATAL, "[" + message + "]", t);
		removeThreadId();
		removeAppName();
	}

	private void resetAll() {
		trace = false;
		debug = false;
		error = false;
		fatal = false;
		info = false;
		warn = false;
	}

	public boolean isDebugEnabled() {
		return debug||trace||super.isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		return true;
	}
	
	private void addThreadId() {
		StringBuffer br = new StringBuffer("Thread Id ");
		br.append(Thread.currentThread().getId());br.append(" ");
		MDC.put("thread",br.toString());
	}
	
	private void removeThreadId() {
		MDC.remove("thread");
	}

	public boolean isFatalEnabled() {
		return true;
	}

	public boolean isInfoEnabled() {
		return info||debug||trace||super.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return trace||super.isTraceEnabled();
	}

	public boolean isWarnEnabled() {
		return warn||info||debug||trace||super.isEnabledFor(Level.WARN);
	}

	public void setDebugLog(boolean debug) {
		resetAll();
		this.debug = debug;
	}

	public void setErrorLog(boolean error) {
		resetAll();
		this.error = error;
	}

	public void setFatalLog(boolean fatal) {
		resetAll();
		this.fatal = fatal;
	}

	public void setInfoLog(boolean info) {
		System.out.println("SETTING INFO AS LOG LEVEL");
		resetAll();
		this.info = info;
	}

	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	public void setTraceLog(boolean trace) {
		resetAll();
		this.trace = trace;
	}

	public void setWarnLog(boolean warn) {
		resetAll();
		this.warn = warn;
	}
	
	private void addAppName() {
		StringBuffer br = new StringBuffer("");
		br.append(MyBaseLoggerConfig.getInstance().getMachineHostName());
		br.append(" ");
		br.append(MyBaseLoggerConfig.getInstance().getApplicationName());
		br.append(" ");
		MDC.put("appname", br.toString());
	}
	
	private void removeAppName() {
		MDC.remove("appname");
	}
}

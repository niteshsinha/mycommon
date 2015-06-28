package com.niteshsinha.mycommon.db.relational.config;


import com.niteshsinha.mycommon.config.IConfig;

public abstract class DBConfig implements IConfig {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8419735125810275636L;

	public abstract String getHostName();

	public abstract int getPortNumber();

	public abstract String getUserName();

	public abstract String getPassword();

	public abstract String getServiceName();

	public abstract int getConnectionMaxPoolSize();

	public abstract int getConnectionMinPoolSize();

	public abstract String getDriverClass();

	public abstract String getProtocol();

	public abstract String getSubProtocol();

	public abstract int getMaxQueryPoolSize();

	public abstract int getMinQueryPoolSize();

	public abstract int getMaxPersistencePoolSize();

	public abstract int getDbMaxStatements();

	public abstract String getConnectionProviderType();

	public abstract String getSubProtocolHostSeparator();

	public abstract String getJndiName();

	public abstract String getJndiEnv();

	public abstract int getMaxIdleConnectionTestPeriod();

	public abstract int getMaxIdleTimeExcessConnectionPeriod();

	public abstract boolean isTestConnectionOnCheckout();

	public abstract String getPreferredTestQuery();

	public abstract String getConnectionTesterClassName() ;

	public abstract int getBatchSize();
	
	public abstract int getTransactionTimeout();

	public abstract int getPageSize();

	public abstract int getAcquireIncrement();

	public abstract int getAcquireRetryAttempts();

	public abstract boolean isBreakAfterAcquireFailure();

	public abstract int getCheckoutTimeout();

}

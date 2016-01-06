package com.niteshsinha.mycommon.db.relational.config;

import java.util.Properties;

import com.niteshsinha.mycommon.config.ConfigUtil;

public class PsqlDbConfig extends DBConfig {

	public static final String KEY_DB_CONNECTION_PROVIDER_TYPE = "db.connection.provider.type";
	private static final String DEFAULT_DB_CONNECTION_PROVIDER_TYPE = "c3p0";
	private String connectionProviderType = DEFAULT_DB_CONNECTION_PROVIDER_TYPE;
	
	public static final String KEY_DB_CONNECTION_DRIVER_CLASS = "db.connection.driver.class";
	private static final String DEFAULT_DB_CONNECTION_DRIVER_CLASS = "org.postgresql.Driver";
	private String driverClass = DEFAULT_DB_CONNECTION_DRIVER_CLASS;
	
	public static final String KEY_DB_CONNECTION_PROTOCOL_TYPE = "db.connection.protocol.type";
	private static final String DEFAULT_DB_CONNECTION_PROTOCOL_TYPE = "jdbc";
	private String connectionProtocol = DEFAULT_DB_CONNECTION_PROTOCOL_TYPE;
	
	public static final String KEY_DB_CONNECTION_SUBPROTOCOL_TYPE = "db.connection.subprotocol.type";
	private static final String DEFAULT_DB_CONNECTION_SUBPROTOCOL_TYPE = "postgresql";
	private String subProtocol = DEFAULT_DB_CONNECTION_SUBPROTOCOL_TYPE;
	
	public static final String KEY_DB_SUBPROTOCOL_HOST_SEPARATOR = "db.connection.subprotocol.host.separator";
	private static final String DEFAULT_DB_SUBPROTOCOL_HOST_SEPARATOR = "//";
	private String subProtocolHostSeparator = DEFAULT_DB_SUBPROTOCOL_HOST_SEPARATOR;

	public static final String KEY_DB_HOSTNAME = "db.hostName";
	private static final String DEFAULT_DB_HOSTNAME = "localhost";
	private String host = DEFAULT_DB_HOSTNAME;

	public static final String KEY_DB_PORT = "db.port";
	private static final int DEFAULT_DB_PORT = 5432;
	private int port = DEFAULT_DB_PORT;

	public static final String KEY_DB_USERNAME = "db.user";
	private static final String DEFAULT_DB_USERNAME = "";
	private String userName = DEFAULT_DB_USERNAME;

	public static final String KEY_DB_PASSWORD = "db.password";
	private static final String DEFAULT_DB_PASSWORD = "";
	private String password = DEFAULT_DB_PASSWORD;

	public static final String KEY_DB_SERVICE_NAME = "db.serviceName";
	private static final String DEFAULT_DB_SERVICE_NAME = "testdb";
	private String serviceName = DEFAULT_DB_SERVICE_NAME;
	
	public static final String KEY_DB_JNDI_NAME = "db.jndiName";
	private static final String DEFAULT_DB_JNDI_NAME = "jdbc/mobiusDB";
	private String jndiName = DEFAULT_DB_JNDI_NAME;
	
	public static final String KEY_DB_JNDI_ENV = "db.jndiEnv";
	private static final String DEFAULT_DB_JNDI_ENV = "java:comp/env";
	private String jndiEnv = DEFAULT_DB_JNDI_ENV;

	public static final String KEY_DB_CONNECTION_MAXPOOL_SIZE = "db.connection.maxPoolSize";
	private static final int DEFAULT_DB_CONNECTION_MAXPOOL_SIZE = 30;
	private int connectionMaxPoolSize = DEFAULT_DB_CONNECTION_MAXPOOL_SIZE;

	public static final String KEY_DB_CONNECTION_MINPOOL_SIZE = "db.connection.minPoolSize";
	private static final int DEFAULT_DB_CONNECTION_MINPOOL_SIZE = 10;
	private int connectionMinPoolSize = DEFAULT_DB_CONNECTION_MINPOOL_SIZE;

	public static final String KEY_DB_MAX_STATEMENTS = "db.statements.max";
	private static final int DEFAULT_KEY_DB_MAX_STATEMENTS = 0;
	private int dbMaxStatements = DEFAULT_KEY_DB_MAX_STATEMENTS;

	public static final String KEY_DB_CONNECTION_MAXQUERYPOOL_SIZE = "db.connection.maxQueryPoolSize";
	private static final int DEFAULT_DB_CONNECTION_MAXQUERYPOOL_SIZE = 20;
	private int maxQueryPoolSize = DEFAULT_DB_CONNECTION_MAXQUERYPOOL_SIZE;

	public static final String KEY_DB_CONNECTION_MINQUERYPOOL_SIZE = "db.connection.minQueryPoolSize";
	private static final int DEFAULT_DB_CONNECTION_MINQUERYPOOL_SIZE = 5;
	private int minQueryPoolSize = DEFAULT_DB_CONNECTION_MINQUERYPOOL_SIZE;

	public static final String KEY_DB_CONNECTION_MAXPERSISTENCEPOOL_SIZE = "db.connection.maxPersistencePoolSize";
	private static final int DEFAULT_DB_CONNECTION_MAXPERSISTENCEPOOL_SIZE = 20;
	private int maxPersistencePoolSize = DEFAULT_DB_CONNECTION_MAXPERSISTENCEPOOL_SIZE;
	
	public static final String KEY_DB_IDLE_CONNECTION_TEST_PERIOD = "db.idle.connection.testPeriod";
	private static final int DEFAULT_DB_IDLE_CONNECTION_TEST_PERIOD = 28680;
	private int maxIdleConnectionTestPeriod = DEFAULT_DB_IDLE_CONNECTION_TEST_PERIOD;
	
	public static final String KEY_DB_MAX_IDLE_TIME_EXCESS_CONNECTION = "db.max.idle.time.excess.connection";
	private static final int DEFAULT_DB_MAX_IDLE_TIME_EXCESS_CONNECTION = 240;
	private int maxIdleTimeExcessConnectionPeriod = DEFAULT_DB_MAX_IDLE_TIME_EXCESS_CONNECTION;
	
	public static final String KEY_DB_TEST_CONNECTION_ON_CHECKOUT = "db.test.connection.on.checkout";
	private static final boolean DEFAULT_DB_TEST_CONNECTION_ON_CHECKOUT = false;
	private boolean isTestConnectionOnCheckout = DEFAULT_DB_TEST_CONNECTION_ON_CHECKOUT;
	
	public static final String KEY_DB_PREFERRED_TEST_QUERY = "db.preferred.test.query";
	private static final String DEFAULT_DB_PREFERRED_TEST_QUERY = "SELECT 1";
	private String preferredTestQuery = DEFAULT_DB_PREFERRED_TEST_QUERY;
	
	public static final String KEY_DB_CONNECTION_TESTER_CLASS_NAME = "db.connection.tester.class.name";
	private static final String DEFAULT_DB_CONNECTION_TESTER_CLASS_NAME = "com.mchange.v2.c3p0.impl.DefaultConnectionTester";
	private String connectionTesterClassName = DEFAULT_DB_CONNECTION_TESTER_CLASS_NAME;
	
	public static final String KEY_DATABASE_CONNECTION_ACQUIRE_INCREMENT = "db.connection.acquireIncrement";
	private static final int DEFAULT_DATABASE_CONNECTION_ACQUIRE_INCREMENT = 5;
	private int acquireIncrement = DEFAULT_DATABASE_CONNECTION_ACQUIRE_INCREMENT;	
	
	public static final String KEY_DATABASE_CONNECTION_ACQUIRE_RETRY_ATTEMPTS = "db.connection.acquireRetryAttempts";
	private static final int DEFAULT_KEY_DATABASE_CONNECTION_ACQUIRE_RETRY_ATTEMPTS = 3;
	private int acquireRetryAttempts = DEFAULT_KEY_DATABASE_CONNECTION_ACQUIRE_RETRY_ATTEMPTS;
	
	public static final String KEY_DATABASE_CONNECTION_CHECKOUT_TIMEOUT = "db.connection.checkoutTimeout";
	private static final int DEFAULT_KEY_DATABASE_CONNECTION_CHECKOUT_TIMEOUT = 30000;
	private int checkoutTimeout = DEFAULT_KEY_DATABASE_CONNECTION_CHECKOUT_TIMEOUT;
	
	public static final String KEY_DATABASE_CONNECTION_BREAK_AFTER_ACQUIRE_FAILURE = "db.connection.breakAfterAcquireFailure";
	private static final boolean DEFAULT_KEY_DATABASE_CONNECTION_BREAK_AFTER_ACQUIRE_FAILURE = false;
	private boolean isBreakAfterAcquireFailure = DEFAULT_KEY_DATABASE_CONNECTION_BREAK_AFTER_ACQUIRE_FAILURE;
	
	public static final String KEY_BATCH_SIZE = "batch.Size";
	private static final int  DEFAULT_BATCH_SIZE = 30;
	private int batchSize = DEFAULT_BATCH_SIZE;
	
	public static final String KEY_PAGE_SIZE = "page.Size";
	private static final int  DEFAULT_PAGE_SIZE = 1000;
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	public static final String KEY_TRANSACTION_TIMEOUT_IN_SEC = "transaction.Timeout";
	private static final int  DEFAULT_TRANSACTION_TIMEOUT_IN_SEC = 10;
	private int transactionTimeout = DEFAULT_TRANSACTION_TIMEOUT_IN_SEC;
	
	public PsqlDbConfig(Properties properties) {
		connectionProviderType = ConfigUtil.getString(properties, KEY_DB_CONNECTION_PROVIDER_TYPE, DEFAULT_DB_CONNECTION_PROVIDER_TYPE);
		driverClass = ConfigUtil.getString(properties, KEY_DB_CONNECTION_DRIVER_CLASS, DEFAULT_DB_CONNECTION_DRIVER_CLASS);
		connectionProtocol = ConfigUtil.getString(properties, KEY_DB_CONNECTION_PROTOCOL_TYPE, DEFAULT_DB_CONNECTION_PROTOCOL_TYPE);
		subProtocol = ConfigUtil.getString(properties, KEY_DB_CONNECTION_SUBPROTOCOL_TYPE, DEFAULT_DB_CONNECTION_SUBPROTOCOL_TYPE);
		subProtocolHostSeparator = ConfigUtil.getString(properties, KEY_DB_SUBPROTOCOL_HOST_SEPARATOR, DEFAULT_DB_SUBPROTOCOL_HOST_SEPARATOR);
		host = ConfigUtil.getString(properties, KEY_DB_HOSTNAME, DEFAULT_DB_HOSTNAME);
		port = ConfigUtil.getInt(properties, KEY_DB_PORT, DEFAULT_DB_PORT);
		userName = ConfigUtil.getString(properties, KEY_DB_USERNAME, DEFAULT_DB_USERNAME);
		password = ConfigUtil.getString(properties, KEY_DB_PASSWORD, DEFAULT_DB_PASSWORD);
		serviceName = ConfigUtil.getString(properties, KEY_DB_SERVICE_NAME, DEFAULT_DB_SERVICE_NAME);
		connectionMaxPoolSize = ConfigUtil.getInt(properties, KEY_DB_CONNECTION_MAXPOOL_SIZE, DEFAULT_DB_CONNECTION_MAXPOOL_SIZE);
		connectionMinPoolSize = ConfigUtil.getInt(properties, KEY_DB_CONNECTION_MINPOOL_SIZE, DEFAULT_DB_CONNECTION_MINPOOL_SIZE);
		dbMaxStatements = ConfigUtil.getInt(properties, KEY_DB_CONNECTION_MINPOOL_SIZE, DEFAULT_DB_CONNECTION_MINPOOL_SIZE);
		maxIdleConnectionTestPeriod = ConfigUtil.getInt(properties, KEY_DB_IDLE_CONNECTION_TEST_PERIOD, DEFAULT_DB_IDLE_CONNECTION_TEST_PERIOD);
		maxIdleTimeExcessConnectionPeriod = ConfigUtil.getInt(properties, KEY_DB_MAX_IDLE_TIME_EXCESS_CONNECTION, DEFAULT_DB_MAX_IDLE_TIME_EXCESS_CONNECTION);
		isTestConnectionOnCheckout = ConfigUtil.getBoolean(properties, KEY_DB_TEST_CONNECTION_ON_CHECKOUT, DEFAULT_DB_TEST_CONNECTION_ON_CHECKOUT);
		preferredTestQuery = ConfigUtil.getString(properties, KEY_DB_PREFERRED_TEST_QUERY, DEFAULT_DB_PREFERRED_TEST_QUERY);
		connectionTesterClassName = ConfigUtil.getString(properties, KEY_DB_CONNECTION_TESTER_CLASS_NAME, DEFAULT_DB_CONNECTION_TESTER_CLASS_NAME);
		batchSize = ConfigUtil.getInt(properties, KEY_BATCH_SIZE, DEFAULT_BATCH_SIZE);
		pageSize = ConfigUtil.getInt(properties, KEY_PAGE_SIZE, DEFAULT_PAGE_SIZE);
		transactionTimeout = ConfigUtil.getInt(properties, KEY_TRANSACTION_TIMEOUT_IN_SEC, DEFAULT_TRANSACTION_TIMEOUT_IN_SEC);
		acquireIncrement = ConfigUtil.getInt(properties, KEY_DATABASE_CONNECTION_ACQUIRE_INCREMENT, DEFAULT_DATABASE_CONNECTION_ACQUIRE_INCREMENT);
		acquireRetryAttempts = ConfigUtil.getInt(properties, KEY_DATABASE_CONNECTION_ACQUIRE_RETRY_ATTEMPTS, DEFAULT_KEY_DATABASE_CONNECTION_ACQUIRE_RETRY_ATTEMPTS);
		checkoutTimeout = ConfigUtil.getInt(properties, KEY_DATABASE_CONNECTION_CHECKOUT_TIMEOUT, DEFAULT_KEY_DATABASE_CONNECTION_CHECKOUT_TIMEOUT);
		
		isBreakAfterAcquireFailure = ConfigUtil.getBoolean(properties, KEY_DATABASE_CONNECTION_BREAK_AFTER_ACQUIRE_FAILURE, DEFAULT_KEY_DATABASE_CONNECTION_BREAK_AFTER_ACQUIRE_FAILURE);
		
		
		//JNDI
		jndiName = ConfigUtil.getString(properties, KEY_DB_JNDI_NAME, DEFAULT_DB_JNDI_NAME);
		jndiEnv = ConfigUtil.getString(properties, KEY_DB_JNDI_ENV, DEFAULT_DB_JNDI_ENV);
	}

	public PsqlDbConfig() {
		this(null);
	}


	public String getHostName() {
		return host;
	}


	public int getPortNumber() {
		return port;
	}

	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return this.password;
	}

	public String getServiceName() {
		return serviceName;
	}


	public int getConnectionMaxPoolSize() {
		return connectionMaxPoolSize;
	}


	public int getConnectionMinPoolSize() {
		return connectionMinPoolSize;
	}


	public String getDriverClass() {
		return driverClass;
	}


	public String getProtocol() {
		return connectionProtocol;
	}


	public String getSubProtocol() {
		return subProtocol;
	}

	public int getMaxQueryPoolSize() {
		return maxQueryPoolSize;
	}


	public int getMinQueryPoolSize() {
		return minQueryPoolSize;
	}


	public int getMaxPersistencePoolSize() {
		return maxPersistencePoolSize;
	}

	public int getDbMaxStatements() {
		return dbMaxStatements;
	}

	/**
	 * @return the connectionProviderType
	 */
	public String getConnectionProviderType() {
		return connectionProviderType;
	}

	/**
	 * @return the subProtocolHostSeparator
	 */
	public String getSubProtocolHostSeparator() {
		return subProtocolHostSeparator;
	}

	/**
	 * @return the jndiName
	 */
	public String getJndiName() {
		return jndiName;
	}

	/**
	 * @param jndiName the jndiName to set
	 */
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	/**
	 * @return the jndiEnv
	 */
	public String getJndiEnv() {
		return jndiEnv;
	}

	/**
	 * @param jndiEnv the jndiEnv to set
	 */
	public void setJndiEnv(String jndiEnv) {
		this.jndiEnv = jndiEnv;
	}

	/**
	 * @return the maxIdleConnectionTestPeriod
	 */
	public int getMaxIdleConnectionTestPeriod() {
		return maxIdleConnectionTestPeriod;
	}

	/**
	 * @return the maxIdleTimeExcessConnectionPeriod
	 */
	public int getMaxIdleTimeExcessConnectionPeriod() {
		return maxIdleTimeExcessConnectionPeriod;
	}

	/**
	 * @return the isTestConnectionOnCheckout
	 */
	public boolean isTestConnectionOnCheckout() {
		return isTestConnectionOnCheckout;
	}

	/**
	 * @return the preferredTestQuery
	 */
	public String getPreferredTestQuery() {
		return preferredTestQuery;
	}

	/**
	 * @return the connectionTesterClassName
	 */
	public String getConnectionTesterClassName() {
		return connectionTesterClassName;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public int getTransactionTimeout() {
		return transactionTimeout;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @return the acquireIncrement
	 */
	public int getAcquireIncrement() {
		return acquireIncrement;
	}

	/**
	 * @return the acquireRetryAttempts
	 */
	public int getAcquireRetryAttempts() {
		return acquireRetryAttempts;
	}

	/**
	 * @return the isBreakAfterAcquireFailure
	 */
	public boolean isBreakAfterAcquireFailure() {
		return isBreakAfterAcquireFailure;
	}

	/**
	 * @return the checkoutTimeout
	 */
	public int getCheckoutTimeout() {
		return checkoutTimeout;
	}

	/**
	 * @param host the host to set
	 */
	public void setHostName(String host) {
		this.host = host;
	}

	/**
	 * @param connectionMaxPoolSize the connectionMaxPoolSize to set
	 */
	public void setConnectionMaxPoolSize(int connectionMaxPoolSize) {
		this.connectionMaxPoolSize = connectionMaxPoolSize;
	}

	/**
	 * @param connectionMinPoolSize the connectionMinPoolSize to set
	 */
	public void setConnectionMinPoolSize(int connectionMinPoolSize) {
		this.connectionMinPoolSize = connectionMinPoolSize;
	}

	
}

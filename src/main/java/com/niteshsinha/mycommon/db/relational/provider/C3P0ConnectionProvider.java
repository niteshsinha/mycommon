package com.niteshsinha.mycommon.db.relational.provider;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.management.ActiveManagementCoordinator;

import com.niteshsinha.mycommon.db.relational.config.DBConfig;
import com.niteshsinha.mycommon.exception.DBException;
import com.niteshsinha.mycommon.logging.BaseLoggerProvider;

public class C3P0ConnectionProvider implements ConnectionProvider {

	private ComboPooledDataSource cpds = new ComboPooledDataSource();
	private final DBConfig dbConfig;
	private static Logger logger = BaseLoggerProvider.getLogger(C3P0ConnectionProvider.class);
	
    public C3P0ConnectionProvider(DBConfig dbConfig) {
    	if (logger.isDebugEnabled()){
    		logger.debug("Entering constructor of C3P0ConnectionProvider.");
		}
    	
    	this.dbConfig = dbConfig;
    	
    	if (logger.isDebugEnabled()){
    		logger.debug("Leaving constructor of C3P0ConnectionProvider.");
		}
	}
    
    public void init() {
    	if (logger.isDebugEnabled()){
    		logger.debug("Entering init of C3P0ConnectionProvider.");
		}
    	
		try {
			Class.forName(dbConfig.getDriverClass());
		} catch (ClassNotFoundException e) {
			logger.error("SERVERE ERROR: Unable to load jdbc driver:", e);
		}
		//Oracle : jdbc:oracle:thin:@10.32.19.152:1521:ORCL
		//Mysql  : jdbc:mysql://localhost:3306/testdb
		
		/**
		 * Get the decrypted password
		 
		String decryptedPassord = "";
		try {
			decryptedPassord = EncryptionHandler.decryptHexToData(dbConfig.getPassword(), Constants.AES_ENCRYPTION_PROVIDER_TYPE);
		} catch (EncryptionException e1) {
			logger.error("[SEVERE]ERROR while decrypting db password", e1);
		}
		*/
		
		cpds.setJdbcUrl(dbConfig.getProtocol() + ":" + dbConfig.getSubProtocol() + ":" + dbConfig.getSubProtocolHostSeparator() + "" + dbConfig.getHostName() + ":"
				+ dbConfig.getPortNumber() + "/" + dbConfig.getServiceName());
		
		cpds.setUser(dbConfig.getUserName());
		cpds.setPassword(dbConfig.getPassword());
		cpds.setInitialPoolSize(dbConfig.getConnectionMinPoolSize());
		cpds.setMaxPoolSize(dbConfig.getConnectionMaxPoolSize());
		cpds.setMinPoolSize(dbConfig.getConnectionMinPoolSize());
		cpds.setAcquireIncrement(dbConfig.getAcquireIncrement());
		cpds.setAcquireRetryAttempts(dbConfig.getAcquireRetryAttempts());
		cpds.setCheckoutTimeout(dbConfig.getCheckoutTimeout());
		cpds.setBreakAfterAcquireFailure(dbConfig.isBreakAfterAcquireFailure());
		
		logger.info("whts the retry attempts:" + dbConfig.getAcquireRetryAttempts());
		logger.info("whts the CheckoutTimeout:" + dbConfig.getCheckoutTimeout());
		
		/**
		 * <!� this property forces the revalidation of a connection after the given amount of time (in secs) �>
			<!� it must be set to LESS than the wait_timout setting for the mysql server (this setting defaults to 28800 secs (8 hours)) �>
		 */
		cpds.setIdleConnectionTestPeriod(dbConfig.getMaxIdleConnectionTestPeriod());
		cpds.setPreferredTestQuery(dbConfig.getPreferredTestQuery());
		//cpds.setProperties(getUserProperties(decryptedPassord));
		cpds.setProperties(getUserProperties());
		
		/**
		 * 	Default: false
			Use only if necessary. Expensive. If true, an operation will be performed at every connection checkout to verify that the connection is valid. 
			Better choice: verify connections periodically using idleConnectionTestPeriod. Also, setting an automaticTestTable or preferredTestQuery will 
			usually speed up all connection tests.
			
			Note that if a database restart occurs, a pool may contain previously acquired but now stale Connections. By default, these stale Connections will 
			only be detected and purged lazily, when an application attempts to use them, and sees an Exception. Setting maxIdleTime or maxConnectionAge can help 
			speed up the replacement of broken Connections. (See Managing ConnectionAge.) If you wish to avoid application Exceptions entirely, you must adopt a 
			connection testing strategy that is likely to detect stale Connections prior to their delivery to clients. (See "Configuring Connection Testing".) 
			Even with active Connection testing (testConnectionsOnCheckout set to true, or testConnectionsOnCheckin and a short idleConnectionTestPeriod), 
			your application may see occasional Exceptions on database restart, for example if the restart occurs after a Connection to the database has already 
			been checked out. 
			
			http://www.mchange.com/projects/c3p0/index.html#testConnectionOnCheckout
			
		 */
		//cpds.setTestConnectionOnCheckout(dbConfig.isTestConnectionOnCheckout());
		
		try {
			cpds.setConnectionTesterClassName(dbConfig.getConnectionTesterClassName());
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cpds.setMaxIdleTimeExcessConnections(dbConfig.getMaxIdleTimeExcessConnectionPeriod());
		
		if (logger.isDebugEnabled()){
    		logger.debug("Leaving init of C3P0ConnectionProvider.");
		}
	}
	
	public Connection getConnection() throws DBException {
		Connection conn = null;
        //Statement stmt = null;
        boolean exception = false;
//        int count = 0;
		DBException dbException = null;
        
        //while(count < cpds.getMaxPoolSize()) {
	        try {
	            conn = cpds.getConnection();
//	            stmt = conn.createStatement();
//	            stmt.executeUpdate("SET NAMES utf8");
//	            stmt.close();
//	            
	            return conn;            
	        } catch (SQLException e) {
	        	//count++;
	        	exception = true;
	        	//logger.error("Method: getConnection :[SEVERE] Error while getting connection : count : " + count, e);
	        	logger.error("Method: getConnection :[SEVERE] SQLException Error while getting connection : ", e);
	        	dbException = new DBException(e.getMessage());
	        	dbException.setErrorCode(e.getErrorCode());
	        } catch (Exception e) {
	        	//count++;
	        	exception = true;
	        	//logger.error("Method: getConnection :[SEVERE] Error while getting connection : count : " + count, e);
	        	logger.error("Method: getConnection :[SEVERE] Error while getting connection : ", e);
	        	dbException = new DBException(e.getMessage());
	        } finally {
	        	if(exception) {
	        		try {
						printStatistics();
					} catch (Exception e) {
						logger.error("[SEVERE] Error while printing statistics", e);
					}
	        		exception = false;
		        	closeConnection(conn);
	        	}
	        	
	        	//closeStatement(stmt);
	        }
        //}
        
        throw (dbException == null) ? new DBException() : dbException;
    }
	
	
	private void printStatistics() throws Exception{
		int idleConnections = cpds.getNumIdleConnections();
		int orphanedConnections = cpds.getNumUnclosedOrphanedConnections(cpds.getUser(), dbConfig.getPassword());
		
		logger.info("Idle connections:" + idleConnections);
		logger.info("Orphaned connections:" + orphanedConnections);
		logger.error("Busy connections:" + cpds.getNumBusyConnections());
		logger.error("Connections:" + cpds.getNumConnections());
		logger.info("CPDS:" + String.valueOf(cpds));
	}
	
	public void destroy() throws Exception{
		//printStatistics();
		
		ActiveManagementCoordinator c3p0Coordinator = new ActiveManagementCoordinator();
		c3p0Coordinator.attemptUnmanagePooledDataSource(cpds);
		c3p0Coordinator.attemptUnmanageC3P0Registry();
	}
	
	public void closeConnection(Connection connection) throws DBException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("Method: closeConnection :[SEVERE] Error while closing connection :" , e);
			}
		}
	}

	public void closeStatement(Statement stmt) throws DBException{
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.error("Method: closeStatement :[SEVERE] Error while closing statement :" , e);
			}
		}
	}

	public void closeResultSet(ResultSet rs) throws DBException{
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("Method: closeResultSet :[SEVERE] Error while closing result set :" , e);
			}
		}
	}
	
	protected Properties getUserProperties() {
		Properties additionalProperties = new Properties();
        additionalProperties.put("DYNAMIC_PREPARE", "true");
        additionalProperties.put("user", dbConfig.getUserName());
        additionalProperties.put("password", dbConfig.getPassword());
        additionalProperties.put("useUnicode", "true");
        additionalProperties.put("characterEncoding", "utf-8");
        additionalProperties.put("zeroDateTimeBehavior","convertToNull");
        additionalProperties.put("useServerPrepStmts","true");
        additionalProperties.put("rewriteBatchedStatements","true");
        //additionalProperties.put("continueBatchOnError","true");
        
        
        return additionalProperties;
    }

	public DataSource getDataSource() throws DBException {
		return cpds;
	}

	public void setDataSource(DataSource cpds) {
		this.cpds = (ComboPooledDataSource)cpds;
	}	
}

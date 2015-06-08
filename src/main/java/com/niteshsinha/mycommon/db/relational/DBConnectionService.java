package com.niteshsinha.mycommon.db.relational;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.config.ConfigManager;
import com.niteshsinha.mycommon.db.relational.common.DBConnectionPoolTypeEnum;
import com.niteshsinha.mycommon.db.relational.config.DBConfig;
import com.niteshsinha.mycommon.db.relational.factory.ConnectionManagerFactory;
import com.niteshsinha.mycommon.db.relational.manager.DBConfigManager;
import com.niteshsinha.mycommon.db.relational.provider.ConnectionProvider;
import com.niteshsinha.mycommon.exception.DBException;
import com.niteshsinha.mycommon.logging.BaseLoggerProvider;

public class DBConnectionService {
	
	private static Logger logger = BaseLoggerProvider.getLogger(DBConnectionService.class);
	
	private static enum State {
		CREATED, INITIALIZED, DESTROYED
	};

	private volatile State state;
	private static final DBConnectionService instance = new DBConnectionService();
	
	private DBConnectionService() {
		if (logger.isDebugEnabled()) {         
			logger.debug("Initializing DB Connection Service:");        
		}

		setState(State.CREATED);
	}

	private void setState(State newState) {
		if (logger.isDebugEnabled()){
			logger.debug("Method : setState");
		}

		state = newState;
	}
	
	private void printDBConfigDetails(DBConfig dbConfig){
		if (dbConfig != null){
			System.out.println("DB config details:");
			logger.info("DB config details:");
			if ("c3p0".equals(dbConfig.getConnectionProviderType())){
				System.out.println("url:" +dbConfig.getHostName());
				System.out.println("user:" +dbConfig.getUserName());
				System.out.println("port:" +dbConfig.getPortNumber());
				System.out.println("Acquire Retry Attempts:" + dbConfig.getAcquireRetryAttempts());
				System.out.println("Acquire Checkout Timeout:" + dbConfig.getCheckoutTimeout());
				logger.info("url:" +dbConfig.getHostName());
				logger.info("user:" +dbConfig.getUserName());
				logger.info("port:" +dbConfig.getPortNumber());
				logger.info("Acquire Retry Attempts:" + dbConfig.getAcquireRetryAttempts());
				logger.info("Acquire Checkout Timeout:" + dbConfig.getCheckoutTimeout());
			}else if ("jndi".equals(dbConfig.getConnectionProviderType())){
				System.out.println("JNDI Env:" +dbConfig.getJndiEnv());
				System.out.println("JNDI Name:" +dbConfig.getJndiName());
				logger.info("JNDI Env:" +dbConfig.getJndiEnv());
				logger.info("JNDI Name:" +dbConfig.getJndiName());
			}
		}
	}
	
	private void testDBConnection(DBConnectionPoolTypeEnum poolType, DBConfig dbConfig) throws DBException{
		//for(int i=0;i<2;i++){                                         
			Connection connection = null;
			PreparedStatement pstmt = null;

			try {
				logger.info("Trying to connect to the DB.....");
				printDBConfigDetails(dbConfig);
				connection = DBConnectionService.getInstance().getConnectionProvider(poolType).getConnection();
				logger.info("Successfully connected to the DB.");
				pstmt = connection.prepareStatement("select now()");

				pstmt.close();
				connection.close();
			}
			catch (DBException ex) {
				logger.info("Attempt to connect DB failed. Maximum tries exceeded :" + dbConfig.getAcquireRetryAttempts());
					throw ex;
				
				/*
					if(i==4) {
						logger.info("Attempt to connect DB failed. Maximum tries exceeded :" + i);
						throw ex;
					}
					try{
						Thread.sleep(10000);                                
					}
					catch(Exception ee){

					}
				 */
			} catch (SQLException e) {
				logger.info("Attempt to connect DB failed. Maximum tries exceeded :" + dbConfig.getAcquireRetryAttempts());
				throw new DBException("Attempt to connect DB failed. Maximum tries exceeded :" + dbConfig.getAcquireRetryAttempts());
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
					}
				}

				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
					}
				}
			}
		//}

	}

	public synchronized void init(DBConnectionPoolTypeEnum poolType) throws DBException{
		if (state == State.CREATED || state == State.INITIALIZED) {
			DBConfig dbConfig;
			if(DBConnectionPoolTypeEnum.DB_CONNECTION_POOL_MODE_GLOBAL == poolType){
				dbConfig = getGlobalDBConfig();
			}else if(DBConnectionPoolTypeEnum.DB_CONNECTION_POOL_MODE_LOCAL == poolType) {
				dbConfig = getLocalDBConfig();
			}else{
				throw new DBException("Unsupported connection pool type:" + poolType.getDBConnectionPoolTypeName());
			}
			
			ConnectionManagerFactory.getInstance().createConnectionManager(poolType, dbConfig);
			
			setState(State.INITIALIZED);
		} else {
			logger.error("Illegal state : " + state);
		}
	}
	
	/*
	 * This method is added to support custom dbConfig object, instead of reading it from file
	 * For Bug 45997 - Use single db connection in csrm
	 */
	public synchronized void init(DBConnectionPoolTypeEnum poolType, DBConfig dbConfig) throws DBException{
		if (state == State.CREATED || state == State.INITIALIZED) {
			if(DBConnectionPoolTypeEnum.DB_CONNECTION_POOL_MODE_GLOBAL == poolType){
//				dbConfig = getGlobalDBConfig();
			}else if(DBConnectionPoolTypeEnum.DB_CONNECTION_POOL_MODE_LOCAL == poolType) {
	//			dbConfig = getLocalDBConfig();
			}else{
				throw new DBException("Unsupported connection pool type:" + poolType.getDBConnectionPoolTypeName());
			}
			
			ConnectionManagerFactory.getInstance().createConnectionManager(poolType, dbConfig);
			
			setState(State.INITIALIZED);
		} else {
			logger.error("Illegal state : " + state);
		}
	}
	
	public ConnectionProvider getConnectionProvider(DBConnectionPoolTypeEnum poolType) {
		return ConnectionManagerFactory.getInstance().getConnectionProvider(poolType);
	}

	public ConfigManager<DBConfig> getConfigManager(DBConnectionPoolTypeEnum poolType) throws DBException{
		if(DBConnectionPoolTypeEnum.DB_CONNECTION_POOL_MODE_GLOBAL == poolType){
			return DBConfigManager.getInstance().getGlobalConfigManager();
		}else if(DBConnectionPoolTypeEnum.DB_CONNECTION_POOL_MODE_LOCAL == poolType) {
			return DBConfigManager.getInstance().getLocalConfigManager();
		}else{
			throw new DBException("Unsupported connection pool type:" + poolType.getDBConnectionPoolTypeName());
		}
	}

	public DBConfig getGlobalDBConfig() {
		DBConfigManager.getInstance().loadGlobalDBConfig();
		return DBConfigManager.getInstance().getGlobalConfigManager().getConfig();
	}
	
	public DBConfig getLocalDBConfig() {
		DBConfigManager.getInstance().loadLocalDBConfig();
		return DBConfigManager.getInstance().getLocalConfigManager().getConfig();
	}
	
	public DBConfig getDBConfig(DBConnectionPoolTypeEnum poolType) throws DBException{
		if(DBConnectionPoolTypeEnum.DB_CONNECTION_POOL_MODE_GLOBAL == poolType){
			return getGlobalDBConfig();
		}else if(DBConnectionPoolTypeEnum.DB_CONNECTION_POOL_MODE_LOCAL == poolType) {
			return getLocalDBConfig();
		}else{
			throw new DBException("Unsupported connection pool type:" + poolType.getDBConnectionPoolTypeName());
		}
	}

	public void testDBConnection(DBConnectionPoolTypeEnum poolType) throws DBException{
		testDBConnection(poolType, getDBConfig(poolType));
	}

	/**
	 * TODO : Revisit
	 * Incase AWS comes into picture
	public Session getSession() throws SessionException{
		return DAOSessionFactory.getInstance().createSession(getDAOConfigFactory());
	}
	 */

	public static DBConnectionService getInstance(){
		return instance;
	}
}

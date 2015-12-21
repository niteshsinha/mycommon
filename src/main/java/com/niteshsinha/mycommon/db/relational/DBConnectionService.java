package com.niteshsinha.mycommon.db.relational;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.config.ConfigManager;
import com.niteshsinha.mycommon.db.relational.common.DatabaseEnum;
import com.niteshsinha.mycommon.db.relational.config.DBConfig;
import com.niteshsinha.mycommon.db.relational.config.MysqlDbConfig;
import com.niteshsinha.mycommon.db.relational.factory.ConnectionManagerFactory;
import com.niteshsinha.mycommon.db.relational.manager.MysqlDbConfigManager;
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
	
	private void testDBConnection(DatabaseEnum poolType, DBConfig dbConfig) throws DBException{
		//for(int i=0;i<2;i++){                                         
			Connection connection = null;
			PreparedStatement pstmt = null;

			try {
				logger.info("Trying to connect to the DB.....");
				printDBConfigDetails(dbConfig);
				connection = DBConnectionService.getInstance().getConnectionProvider(poolType).getConnection();
				logger.info("Successfully connected to the DB.");
				pstmt = connection.prepareStatement("select now()");
				//ResultSet rs = pstmt.executeQuery();
				//logger.info("Time from DB: "+rs.getString(0));
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

	public synchronized void init(DatabaseEnum poolType) throws DBException{
		if (state == State.CREATED || state == State.INITIALIZED) {
			DBConfig dbConfig = getMysqlConfig();
			
//			if(DatabaseEnum.DB_CONNECTION_POOL_MODE_GLOBAL == poolType){
//				dbConfig = getGlobalDBConfig();
//			}else if(DatabaseEnum.DB_CONNECTION_POOL_MODE_LOCAL == poolType) {
//				dbConfig = getLocalDBConfig();
//			}else{
//				throw new DBException("Unsupported connection pool type:" + poolType.getDBConnectionPoolTypeName());
//			}
			
			
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
	public synchronized void init(DatabaseEnum dbType, DBConfig dbConfig) throws DBException{
		if (state == State.CREATED || state == State.INITIALIZED) {
			if(DatabaseEnum.DB_MYSQL == dbType){
//				dbConfig = getGlobalDBConfig();
			}else if(DatabaseEnum.DB_PSQL == dbType) {
	//			dbConfig = getLocalDBConfig();
			}else{
				throw new DBException("Unsupported Database:" + dbType.getDatabaseName());
			}
			
			ConnectionManagerFactory.getInstance().createConnectionManager(dbType, dbConfig);
			
			setState(State.INITIALIZED);
		} else {
			logger.error("Illegal state : " + state);
		}
	}
	
	public ConnectionProvider getConnectionProvider(DatabaseEnum dbType) {
		return ConnectionManagerFactory.getInstance().getConnectionProvider(dbType);
	}

	public ConfigManager<MysqlDbConfig> getConfigManager(DatabaseEnum db) throws DBException{
//		if(DatabaseEnum.DB_MYSQL == db){
//			return MysqlDbConfigManager.getInstance().getConfigManager();
//		}else if(DatabaseEnum.DB_MONGO == db) {
//			return DBConfigManager.getInstance().getLocalConfigManager();
//		}else{
//			throw new DBException("Unsupported connection pool type:" + poolType.getDBConnectionPoolTypeName());
//		}
		return MysqlDbConfigManager.getInstance().getConfigManager();
	}

//	public DBConfig getGlobalDBConfig() {
//		DBConfigManager.getInstance().loadGlobalDBConfig();
//		return DBConfigManager.getInstance().getGlobalConfigManager().getConfig();
//	}
//	
	public DBConfig getPsqlConfig() {
		/*
		 * TODO:create a PsqlDbConfigManager and updated below.
		 */
		MysqlDbConfigManager.getInstance().loadDbConfig();
		return MysqlDbConfigManager.getInstance().getConfigManager().getConfig();
	}
	
	public MysqlDbConfig getMysqlConfig() {
		MysqlDbConfigManager.getInstance().loadDbConfig();
		return MysqlDbConfigManager.getInstance().getConfigManager().getConfig();
	}
	
	
	public DBConfig getDBConfig(DatabaseEnum dbType) throws DBException{
		if(DatabaseEnum.DB_MYSQL == dbType){
			return getMysqlConfig();
		}else if(DatabaseEnum.DB_PSQL == dbType) {
			return getMysqlConfig();
		}else{
			throw new DBException("Unsupported Database:" + dbType.getDatabaseName());
		}
	}

	public void testDBConnection(DatabaseEnum poolType) throws DBException{
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
	
	public static void main(String[] args) {
		try {
			DBConnectionService.getInstance().init(DatabaseEnum.DB_MYSQL);
			DBConnectionService.getInstance().testDBConnection(DatabaseEnum.DB_MYSQL);
			
			Connection connection = DBConnectionService.getInstance().getConnectionProvider(DatabaseEnum.DB_MYSQL).getConnection();
			PreparedStatement stmt=null;
			try {
				stmt = connection.prepareStatement("insert into tworld.scopes values (DEFAULT,1000)");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int insertcount = -1;
			try {
				insertcount = stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Inserted: "+ insertcount);
			
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

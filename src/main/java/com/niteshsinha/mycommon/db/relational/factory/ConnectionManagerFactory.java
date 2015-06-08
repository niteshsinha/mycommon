package com.niteshsinha.mycommon.db.relational.factory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.db.relational.common.DBConnectionPoolTypeEnum;
import com.niteshsinha.mycommon.db.relational.config.DBConfig;
import com.niteshsinha.mycommon.db.relational.provider.ConnectionProvider;
import com.niteshsinha.mycommon.logging.BaseLoggerProvider;

public class ConnectionManagerFactory {
	
	private static final Logger logger = BaseLoggerProvider.getLogger(ConnectionManagerFactory.class);
	private static final ConnectionManagerFactory instance = new ConnectionManagerFactory();
	private static final Map<DBConnectionPoolTypeEnum, ConnectionProvider> connectionProviderTable = new HashMap<DBConnectionPoolTypeEnum, ConnectionProvider>();
	
	private ConnectionManagerFactory(){
		
	}
	
	public static ConnectionManagerFactory getInstance(){
		return instance;
	}
	
	/**
	 * Instantiates and returns the connection provider implementation
	 * @param connectionProviderType
	 * @return
	 */
	public void createConnectionManager(DBConnectionPoolTypeEnum poolType, DBConfig dbConfig){
		if (logger.isDebugEnabled()){
			logger.debug("Inside createConnectionManager : database connection type : " + poolType.getDBConnectionPoolTypeName());
		}
		
		ConnectionProvider connectionProvider = connectionProviderTable.get(poolType);
		if (connectionProvider == null){
			logger.info("Initializing connection provider for database connection  type:" + poolType.getDBConnectionPoolTypeName());
			
			connectionProvider = ConnectionProviderFactory.getInstance().createConnectionProvider(dbConfig.getConnectionProviderType(), dbConfig);
			connectionProvider.init();
			
			connectionProviderTable.put(poolType, connectionProvider);
		}
	}
	
	public ConnectionProvider getConnectionProvider(DBConnectionPoolTypeEnum poolType){
		ConnectionProvider connectionProvider = connectionProviderTable.get(poolType);
		if (connectionProvider == null){
			logger.error("No connection provider found for database connection  type:" + poolType.getDBConnectionPoolTypeName());
			return null;
		}else{
			return connectionProvider;
		}
		
	}
}

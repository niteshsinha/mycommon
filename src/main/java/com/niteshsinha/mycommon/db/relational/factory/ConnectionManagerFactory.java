package com.niteshsinha.mycommon.db.relational.factory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.db.relational.common.DatabaseEnum;
import com.niteshsinha.mycommon.db.relational.config.DBConfig;
import com.niteshsinha.mycommon.db.relational.provider.ConnectionProvider;
import com.niteshsinha.mycommon.logging.BaseLoggerProvider;

public class ConnectionManagerFactory {
	
	private static final Logger logger = BaseLoggerProvider.getLogger(ConnectionManagerFactory.class);
	private static final ConnectionManagerFactory instance = new ConnectionManagerFactory();
	private static final Map<DatabaseEnum, ConnectionProvider> connectionProviderTable = new HashMap<DatabaseEnum, ConnectionProvider>();
	
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
	public void createConnectionManager(DatabaseEnum dbType, DBConfig dbConfig){
		if (logger.isDebugEnabled()){
			logger.debug("Inside createConnectionManager : database connection type : " + dbType.getDatabaseType());
		}
		
		ConnectionProvider connectionProvider = connectionProviderTable.get(dbType);
		if (connectionProvider == null){
			logger.info("Initializing connection provider for database connection  type:" + dbType.getDatabaseType());
			
			connectionProvider = ConnectionProviderFactory.getInstance().createConnectionProvider(dbType.getDatabaseType(), dbConfig);
			connectionProvider.init();
			
			connectionProviderTable.put(dbType, connectionProvider);
		}
	}
	
	public ConnectionProvider getConnectionProvider(DatabaseEnum dbType){
		ConnectionProvider connectionProvider = connectionProviderTable.get(dbType);
		if (connectionProvider == null){
			logger.error("No connection provider found for database connection  type:" + dbType.getDatabaseName());
			return null;
		}else{
			return connectionProvider;
		}
		
	}
}

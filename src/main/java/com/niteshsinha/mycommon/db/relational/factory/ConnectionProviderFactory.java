package com.niteshsinha.mycommon.db.relational.factory;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.db.relational.config.DBConfig;
import com.niteshsinha.mycommon.db.relational.provider.C3P0ConnectionProvider;
import com.niteshsinha.mycommon.db.relational.provider.ConnectionProvider;
import com.niteshsinha.mycommon.logging.BaseLoggerProvider;

public class ConnectionProviderFactory {

	private static final Logger logger = BaseLoggerProvider.getLogger(ConnectionProviderFactory.class);
	private static final ConnectionProviderFactory instance = new ConnectionProviderFactory();
	
	private ConnectionProviderFactory(){
		
	}
	
	public static ConnectionProviderFactory getInstance(){
		return instance;
	}
	
	/**
	 * Instantiates and returns the connection provider implementation
	 * @param connectionProviderType
	 * @return
	 */
	protected ConnectionProvider createConnectionProvider(String connectionProviderType, DBConfig dbConfig){
		if (logger.isDebugEnabled()){
			logger.debug("Inside getConnectionProvider : connectionProviderType : " + connectionProviderType);
		}
		
		if ("c3p0".equals(connectionProviderType)){
			return new C3P0ConnectionProvider(dbConfig);
		}else if ("jndi".equals(connectionProviderType)){
			//return new JNDIConnectionProvider(dbConfig);
			return null;
		}else{
			return null;
		}
	}
}

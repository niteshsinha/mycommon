package com.niteshsinha.mycommon.db.relational;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import com.niteshsinha.mycommon.logging.BaseLoggerProvider;

public class C3P0Test {
	/**
	    * A singleton that represents a pooled datasource. It is composed of a C3PO
	    * pooled datasource. Can be changed to any connect pool provider
	    */
	    private Properties props;
	    private ComboPooledDataSource cpds;
	    private static C3P0Test datasource;
	    private static Logger log = BaseLoggerProvider.getLogger(C3P0Test.class);

	    private C3P0Test() throws IOException, SQLException {
	        // load datasource properties
	        log.info("Reading datasource.properties from classpath");
	        props = Utils.readProperties("datastore.properties");
	        cpds = new ComboPooledDataSource();
	        cpds.setJdbcUrl(props.getProperty("jdbcUrl"));
	        cpds.setUser(props.getProperty("username"));
	        cpds.setPassword(props.getProperty("password"));

	        cpds.setInitialPoolSize(new Integer((String) props.getProperty("initialPoolSize")));
	        cpds.setAcquireIncrement(new Integer((String) props.getProperty("acquireIncrement")));
	        cpds.setMaxPoolSize(new Integer((String) props.getProperty("maxPoolSize")));
	        cpds.setMinPoolSize(new Integer((String) props.getProperty("minPoolSize")));
	        cpds.setMaxStatements(new Integer((String) props.getProperty("maxStatements")));

	        Connection testConnection = null;
	        Statement testStatement = null;

	        // test connectivity and initialize pool
	        try {
	               testConnection = cpds.getConnection();
	               testStatement = testConnection.createStatement();
	               testStatement.executeQuery("select 1+1 from CURRENT_TIMESTAMP");
	               //testStatement.executeQuery("select 1+1 from DUAL");
	            } catch (SQLException e) {
	                throw e;
	            } finally {
	                testStatement.close();
	                testConnection.close();
	        }

	    }

	    public static C3P0Test getInstance() throws IOException, SQLException {
	        if (datasource == null) {
	              datasource = new C3P0Test();
	              return datasource;
	            } else {
	              return datasource;
	            }
	    }

	    public Connection getConnection() throws SQLException {
	        return this.cpds.getConnection();
	    }
	    
	    public static void main(String args[]) throws IOException, SQLException{
	    	C3P0Test ds = C3P0Test.getInstance();
	    	Connection connection = ds.getConnection();
	    	Statement statement = connection.createStatement();
	    	ResultSet resultSet = statement.executeQuery(" SELECT CURRENT_TIMESTAMP");
	    	//ResultSet resultSet = statement.executeQuery(" SELECT NOW(),CURDATE(),CURTIME()");
	    	while(resultSet.next()){
	    		System.out.println("RES--"+resultSet.getString(1));
	    	}
	    }
}

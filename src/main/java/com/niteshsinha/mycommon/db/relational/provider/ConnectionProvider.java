package com.niteshsinha.mycommon.db.relational.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.niteshsinha.mycommon.exception.DBException;

public interface ConnectionProvider {
	public void init(); // TODO throw InitializationException
	public void destroy() throws Exception; 
	public Connection getConnection() throws DBException;
	public void closeResultSet(ResultSet rs) throws DBException;
	public void closeStatement(Statement stmt) throws DBException;;
	public void closeConnection(Connection connection) throws DBException;
	public DataSource getDataSource() throws DBException;
	public void setDataSource(DataSource cpds);
}

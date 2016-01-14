package com.niteshsinha.mycommon.db.relational.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;

import com.niteshsinha.mycommon.db.relational.DBConnectionService;
import com.niteshsinha.mycommon.db.relational.common.DatabaseEnum;
import com.niteshsinha.mycommon.exception.DBException;
import com.niteshsinha.mycommon.logging.BaseLoggerProvider;

public abstract class AbstractSQLConnectionManager{
	
	private final Logger logger = BaseLoggerProvider.getLogger(AbstractSQLConnectionManager.class);
	
	public Connection getConnection() throws Exception{
		return (Connection)DBConnectionService.getInstance().getConnectionProvider(DatabaseEnum.DB_PSQL).getConnection();
	}
	
	public void beginTransaction(Connection connection) throws SQLException {
		logger.debug("===begin transaction ====");
		connection.setAutoCommit(false);
    }

	public void commitTransaction(Connection connection) throws SQLException {
		logger.debug(" ===== commit transation=======");
    	connection.commit();
    }

	public void rollbackTransaction(Connection connection) throws SQLException {
		if (connection != null){
			connection.rollback();
		}
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
	
	public void clearBatchStatement(Statement statement){
		if(statement != null){
			try{
				statement.clearBatch();
			}catch(SQLException e){
				logger.error("Method: clearBatchStatement :[SEVERE] Error while clearing batch statement :" , e);
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
	
	/**
	 * The function will return true if connection is valid, otherwise throw DBException
	 * @param connection - connection to validate
	 * @return
	 * @throws DBException
	 */
	protected boolean validateConnection(Connection connection) throws DBException{		
		Statement stmt = null;
		DBException e = null;
		try {
			stmt = connection.createStatement();
	        stmt.executeQuery("SELECT 1");
	        stmt.close();
		} catch (SQLException e1) {
			e = new DBException(e1.getMessage()); 
			throw new DBException();
		}finally {
        	if(e != null) {
	        	closeConnection(connection);
        	}
    		closeStatement(stmt);
		}
		
		return true;
	}
}

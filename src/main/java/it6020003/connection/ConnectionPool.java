package it6020003.connection;

import java.sql.*;

public interface ConnectionPool {
	public Connection getConnection(String objectname) throws SQLException;
	
	public void releaseConnection(Connection con, String objectname) throws SQLException;
}

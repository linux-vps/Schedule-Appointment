package it6020003.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool{
	
	private String driver; //Define driver
	
	private String url; // Define execute path
	
	//Account
	private String username;
	private String password;
	
	private Stack<Connection> pool;
	
	public ConnectionPoolImpl() {
		//define driver
		this.driver = "com.mysql.cj.jdbc.Driver";
		
		//nap trinh dieu khien
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.url = "jdbc:mysql://localhost:3306/it6020003_data_healthcare";
		
		this.username = "it6020003_sannh";
		this.password = "s12345678";
		
		this.pool = new Stack<>();
	}

	@Override
	public Connection getConnection(String objectname) throws SQLException {
		if (this.pool.isEmpty()) {
			System.out.println(objectname + " has created a new connection!");
			return DriverManager.getConnection(this.url, this.username, this.password);
		}
		else {
			System.out.println(objectname + " has poped a connection!");
			return this.pool.pop();
		}
	}

	@Override
	public void releaseConnection(Connection con, String objectname) throws SQLException {
		System.out.println(objectname + " has pushed a connection!");
		this.pool.push(con);
	}
	public void finalize() throws Throwable {
		this.pool.clear();
		this.pool = null;
		System.out.println("The connection pool has closed!");
	}

}

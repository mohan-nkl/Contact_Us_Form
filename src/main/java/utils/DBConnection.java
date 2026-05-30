package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String url = "jdbc:postgres://localhost:5432/contactus_database";
	private static final String username = "postgres";
	private static final String password = "mohan1997";
	
	private DBConnection() {
		
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}

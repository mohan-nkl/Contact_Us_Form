package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String url = "jdbc:postgresql://localhost:5432/contactus_database";
	private static final String username = "postgres";
	private static final String password = "mohan1997";
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("PostgreSQL JDBC driver not found on classpath", e);
		}
	}

	
	private DBConnection() {
		
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}

package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Admin;
import utils.DBConnection;

public class AdminDAO {

	public Admin findAdminByUsername(String username) throws SQLException {
		
		String sql = "SELECT id, username, password_hash FROM admins WHERE username = ?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);	
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				Admin admin = new Admin();
				admin.setAdminId(resultSet.getInt("id"));
				admin.setName(resultSet.getString("username"));
				admin.setHashedPassword(resultSet.getString("password_hash"));
				return admin;
			}
			
			return null;
		}
		finally {
			
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}

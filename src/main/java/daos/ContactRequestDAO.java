package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.ContactRequest;
import utils.DBConnection;

public class ContactRequestDAO {
	
	public void saveRequest(ContactRequest request) throws SQLException {
		
		String sql = "INSERT INTO contact_requests (full_name, email, message) " + 
						"VALUES(?, ?, ?)";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, request.getFullName());
			preparedStatement.setString(2, request.getEmail());
			preparedStatement.setString(3, request.getMessage());
			preparedStatement.executeUpdate();
		}
		finally {
			
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	
	public List<ContactRequest> getAllRequests() throws SQLException {
		
		String sql = "SELECT id, full_name, email, message, status, created_at FROM contact_requests ORDER BY created_at DESC";
		
		List<ContactRequest> requests = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				ContactRequest request = new ContactRequest();
                request.setRequestId(resultSet.getInt("id"));
                request.setFullName(resultSet.getString("full_name"));
                request.setEmail(resultSet.getString("email"));
                request.setMessage(resultSet.getString("message"));
                request.setStatus(resultSet.getString("status"));
                request.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                requests.add(request);
			}
			
			return requests;
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
	
	public void updateRequestStatus(int id, String status) throws SQLException {
		
		String sql = "UPDATE contact_requests SET status = ? WHERE id = ?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		}
		finally {
			
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}

package models;

import java.time.LocalDateTime;

public class ContactRequest {
	
	private int requestId;
	private String fullName;
	private String email;
	private String message;
	private String status; 
	private LocalDateTime createdAt;
	
	
	public ContactRequest() {
		
	}
	
	public ContactRequest(String fullName, String email, String message) {
		this.fullName = fullName;
		this.email = email;
		this.message = message;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}

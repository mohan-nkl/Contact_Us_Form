package models;

public class Admin {

	private int adminId;
	private String name;
	private String hashedPassword;
	
	public Admin() {
		
	}
	
	public Admin(int adminId, String name, String hashedPassword) {
		
		this.adminId = adminId;
		this.name = name;
		this.hashedPassword = hashedPassword;
	}
	
	public int getAdminId() {
		return adminId;
	}
	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHashedPassword() {
		return hashedPassword;
	}
	
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	
}

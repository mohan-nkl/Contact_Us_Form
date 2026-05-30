package utils;

public class HashGenerator {

	public static void main(String[] args) {
		
		String adminPassword = "Admin@ContactUsDatabase";
		System.out.println(PasswordUtil.hashPassword(adminPassword));
	}
}

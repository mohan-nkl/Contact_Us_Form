package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

	private PasswordUtil() {
		
	}
	
	public static String hashPassword(String inputPassword) {
		return BCrypt.hashpw(inputPassword, BCrypt.gensalt(12));
	}
	
	public static boolean verifyPassword(String inputPassword, String hashedPassword) {
		return BCrypt.checkpw(inputPassword, hashedPassword);
	}
}

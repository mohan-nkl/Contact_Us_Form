package utils;

import java.util.Scanner;

public class HashGenerator {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the admin password: ");
        String plainPassword = scanner.nextLine();
        scanner.close();

        System.out.print("Hashed password:");
        System.out.println(PasswordUtil.hashPassword(plainPassword));
	}
}

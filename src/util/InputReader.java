package util;

import java.util.Scanner;

public class InputReader {
	static Scanner scanner = new Scanner(System.in);
	
	public static String readString (String message) {
		System.out.print(message);
		String value = scanner.nextLine();
		
		return value;
	}
	
	public static char readChar (String message) {
		System.out.print(message);
		
		char value = scanner.next().charAt(0);
		value = Character.toLowerCase(value);
		scanner.nextLine();
		
		return value;
	}
	
	public static int readInt (String message) {
		System.out.print(message);
		int value = scanner.nextInt();
		scanner.nextLine();
		
		return value;
	}
	
	public static double readDouble (String message) {
		System.out.print(message);
		double value = scanner.nextDouble();
		scanner.nextLine();
		
		return value;
	}
}

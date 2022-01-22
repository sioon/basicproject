package sioon.util;



import java.util.Scanner;

public class ScannerUtil {
	private static Scanner s = new Scanner(System.in);
	
	public static String nextLine() {
		return s.nextLine();
	}
	
	public static int nextint() {
		return Integer.parseInt(s.nextLine());
	}
}
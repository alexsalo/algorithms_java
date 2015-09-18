package strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SedgewickProblems {
	public static Set<String> dict = new HashSet<String>(Arrays.asList("hello",
			"world", "geography"));

	public static void main(String[] args) {
		System.out.println(isGoodPassword("geography2world"));
		System.out.println("%d %s, ");
	}

	/**
	 * Password checker. Write a program that reads in a string from the command
	 * line and a dictionary of words from standard input, and checks whether it
	 * is a "good" password. Here, assume "good" means that it (i) is at least 8
	 * characters long, (ii) is not a word in the dictionary, (iii) is not a
	 * word in the dictionary followed by a digit 0-9 (e.g., hello5), (iv) is
	 * not two words separated by a digit (e.g., hello2world) *
	 * 
	 * @param pass
	 *            Password to be checked
	 * @return boolean whether the password is safe
	 */
	public static boolean isGoodPassword(String pass) {
		if (pass.length() < 8)
			return false;
		if (dict.contains(pass))
			return false;
		if (dict.contains(pass.substring(0, pass.length() - 1))
				&& isDigit(pass.charAt(pass.length() - 1)))
			return false;
		
		int digitPos = -1;
		for (int i = 0; i < pass.length(); i++) {
			if (isDigit(pass.charAt(i))) {
				digitPos = i;
			}
		}
		if (digitPos != -1 && dict.contains(pass.substring(0, digitPos))
				&& dict.contains(pass.substring(digitPos + 1, pass.length())))
			return false;

		// all checks passed
		return true;
	}

	private static boolean isDigit(char c) {
		for (int i = 0; i < 10; i++) {
			if (i == Character.getNumericValue(c)) {
				return true;
			}
		}
		return false;
	}

}

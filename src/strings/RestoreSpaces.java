package strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RestoreSpaces {
	static Set<String> dictionary = new HashSet<String>(
			Arrays.asList("pea", "nut", "peanut", "butter", "car", "i", "am", "alex"));

	public static void main(String[] args) {
		System.out.println(restoreSpaces("iamalexcarpoolpeanutbutter"));
	}

	// assuming only words in the dictionary
	public static String restoreSpaces(String s) {
		for (int i = 0; i < s.length(); i++){
			String left = s.substring(0, i);
			String right = s.substring(i, s.length());
			if (dictionary.contains(left)) 
				return left + " " + restoreSpaces(right);
		}
		return s;
	}
}

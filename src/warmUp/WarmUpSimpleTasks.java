package warmUp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class WarmUpSimpleTasks {

	public static void main(String[] args) throws Exception {
        System.out.println(isBal("(())"));
        System.out.println(isBal("(()"));
        System.out.println(isBal("()()"));
        System.out.println(isBal(")()("));
		System.out.println(reverseString("alex"));
		System.out.println(reverseStringFast("james"));
		System.out.println(revString("Vasek"));
		System.out.println(String.valueOf(Fibonacci(10)));
		System.out.println(String.valueOf(Fib(1000)));
		System.out.println(String.valueOf(Fibo(10)));
		printMultiplicationTable(5);
		System.out.println(sumInts("sumints.txt"));
		printOdds(33);
		System.out.println(formatRGB(10, 140, 255));
		System.out.println(countBits(1024));
		System.out.println(cntBits(1023));
		System.out.println(isPalindrome(7));
		System.out.println(maskString("Vasek"));
		System.out.println(isUnique("bob", new String[] { "bab", "cat" }));
		System.out.println();
		System.out.println(isBal("()(dfdf)"));
		System.out.println();
		System.out.println(isBalancedFigured("([)]"));
		System.out.println(isBalancedFigured("([{}])"));
		System.out.println("Punished: " + isToBeBunished("OOLAL"));
		System.out.println(-11%3);
	}

	// EXAMPLE SET # 1

	// take a string, return first char + number of chars between + last char
	public static String maskString(String s) {
		return s.charAt(0) + String.valueOf(s.length() - 2)
				+ s.charAt(s.length() - 1);
	}

	// see if the encoded version of the string is in a given array
	public static boolean isUnique(String s, String[] array) {
		String masked = maskString(s);
		for (String as : array) {
			if (masked.equals(maskString(as))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isBalancedFigured(String s) {
		Stack<Character> stack = new Stack<Character>();
		List<Character> opening = Arrays.asList('(', '{', '[');
		List<Character> closing = Arrays.asList(')', '}', ']');
		for (char c : s.toCharArray()) {
			if (opening.contains(c)) { // expect opening
				stack.push(c);
			} else if (closing.contains(c)) { // expect closing
				if (stack.isEmpty())
					return false;
				int bracketType = opening.lastIndexOf(stack.pop());
				if (closing.get(bracketType) != c)
					return false;
			} // else ignore other chars
		}
		return stack.isEmpty();
	}

	public static boolean isBal(String s) {
		int opened = 0;
		for (char c : s.toCharArray()) {
			if (c == '(') {
				opened++;
			} else if (c == ')') {
				if (opened == 0) {
					return false; // closing before opening
				} else {
					opened--;
				}
			} // else do noting
		}
		return opened == 0;
	}

	// assuming absent doesn’t help the series of being late
	public static boolean isToBeBunished(String s) {
		int lateMax = 2;
		int absentMax = 1;
		
		int late = 0;
		int absent = 0;
		for (char c : s.toCharArray()) {
			if (c == 'A') {
				if (absent < absentMax) {
					absent++;
				} else {
					return true;
				}
			} else if (c == 'L') {
				if (late < lateMax) {
					late++;
				} else {
					return true;
				}
			} else {
				late = 0;
			}
		}
		return false;
	}

	// TRIVIA

	// Count all bits
	public static int countBits(int x) {
		int i = 0;
		while (x != 0) {
			i++;
			x = x & (x - 1);
		}
		return i;
	}

	// Binary Palindrome
	public static boolean isPalindrome(int x) {
		int[] binary = new int[32];
		int size = 0;
		while (x != 0) {
			binary[size++] = x & 1;
			x = x >> 1;
		}
		for (int i = 0; i < size / 2; i++) {
			if (binary[i] != binary[size - 1 - i]) {
				return false;
			}
		}
		return true;
	}

	public static int cntBits(int x) {
		return x == 0 ? 0 : cntBits(x & (x - 1)) + 1;
	}

	// 1. Reverse a String
	public static String reverseString(String s) {
		char[] chars = s.toCharArray();
		char[] newStringChars = new char[chars.length];
		for (int i = 0; i < chars.length; i++) {
			newStringChars[i] = chars[chars.length - 1 - i];
		}
		return new String(newStringChars);
	}

	public static String reverseStringFast(String s) {
		char[] chars = s.toCharArray();
		char tmp;
		int len = chars.length;
		for (int i = 0; i < len / 2; i++) {
			tmp = chars[len - 1 - i];
			chars[len - 1 - i] = chars[i];
			chars[i] = tmp;
		}
		return new String(chars);
	}

	public static String revString(String s) {
		return s.length() == 1 ? s : s.substring(s.length() - 1, s.length())
				+ revString(s.substring(0, s.length() - 1));
	}

	// 2. Nth Fibonacci Number
	public static int Fibonacci(int n) throws Exception {
		if (n < 1) {
			throw new Exception("N must positive integer");
		} else if (n == 1 || n == 2) {
			return (1);
		} else {
			return Fibonacci(n - 1) + Fibonacci(n - 2);
		}
	}

	public static long Fib(int n) throws Exception {
		if (n < 1) {
			throw new Exception("N must positive integer");
		} else if (n == 1 || n == 2) {
			return (1);
		} else {
		    long[] lastTwo = new long[] { 1, 1 };
			int i = 3;
			while (i++ <= n) {
			    long tmp = lastTwo[1];
				lastTwo[1] += lastTwo[0];
				lastTwo[0] = tmp;
			}
			return lastTwo[1];
		}
	}

	public static int Fibo(int n) {
		return n <= 1 ? n : Fibo(n - 1) + Fibo(n - 2);
	}

	// 3. Multiplication Table
	public static void printMultiplicationTable(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(String.format("%4d", i * j));
			}
			System.out.println();
		} // ~O(n^2)
	}

	public static void printMultiplicationTableFast(int n) {
		int[] base = new int[n];
		for (int i = 0; i < n; i++) {
			base[i] = i + 1;
		}

		for (int i = 0; i < n; i++) {
			printRow(base);
			for (int j = 0; j < n; j++) {
				base[j] += (j + 1);
			}
		}
	}

	private static void printRow(int[] row) {
		for (int j = 0; j < row.length; j++) {
			System.out.print(row[j] + " ");
		}
		System.out.println();
	}

	// 4. Sum ints in file
	public static int sumInts(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
		int res = 0;
		while (sc.hasNextInt()) {
			res += sc.nextInt();
		}
		sc.close();
		return res;
	}

	// Example 5: Write function to print the odd numbers from 1 to 99.
	public static void printOdds(int n) {
		int i = 1;
		while (i <= n) {
			System.out.print(i + " ");
			i += 2;
		}
		System.out.println();
	}

	public static String formatRGB(int r, int g, int b) {
		return String.format("%02X%02X%02X", r, g, b);
	}
}

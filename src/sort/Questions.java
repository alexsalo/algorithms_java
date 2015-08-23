package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Stack;

public class Questions {

	public static void main(String[] args) {
		int blen = 10;
		int alen = blen + 15;
		int[] b = new int[blen];
		for (int i = 1; i < blen; i++)
			b[i] = i;
		int[] a = new int[alen];
		for (int i = 1; i < alen - blen; i++)
			a[i] = i;

		print(b);
		print(a);
		print(merge(a, b));
		print(mergeNoExtraSpace(a, b));

		String[] strings = new String[] { "aba", "cca", "aab", "c", "bbc",
				"baa", "cbb", "acc" };
		System.out.println(Arrays.toString(sortAsAnagrams(strings)));
		System.out.println(Arrays.toString(sortByAnagrams(strings)));
		System.out.println(Arrays.toString(groupByAnagrams(strings)));

		int[] ints = new int[] { -16, -15, 2, 3, 6, 8, 15, 20, 43, 50, 100,
				101, 101 };
		int[] ints2 = new int[] { 6, 8, 15, 20, 43, 50, 100, 101, 101, -16,
				-15, 2, 3 };
		System.out.println(Arrays.toString(ints));
		int toFind = -15;
		System.out.println("Looking for: " + toFind + "... pos = "
				+ findInSortedArray(ints, toFind));
		System.out.println(Arrays.toString(ints2));
		System.out.println("Looking for: " + toFind + "... pos = "
				+ findInSortedArray(ints2, toFind));

		String[] interspersed = new String[] { "at", "", "", "", "ball", "",
				"", "car", "", "", "dad", "", "" };
		System.out.println(findStringInSortedArray(interspersed, "ball"));
		System.out.println(findStringInSortedArray(interspersed, "at"));
		System.out.println(findStringInSortedArray(interspersed, "dad"));
		System.out.println(findStringInSortedArray(interspersed, "balla"));
		
		int[][] matrix = {
			{0, 1, 2, 3, 4},
			{5, 6, 7, 8, 9}, 
			{10, 11, 12, 13, 14}
		};
		System.out.println("len = " + matrix.length + ", len[0] = " + matrix[0].length);
		for (int i = 0; i < matrix.length; i++)
			System.out.println(Arrays.toString(matrix[i]));
		System.out.println(Arrays.toString(matrixBinarySearch(matrix, 7)));
		
		int[][] mat = {
				{15, 20, 40, 85, 96},
				{20, 35, 80, 95, 98},
				{30, 55, 95, 105, 110},
				{40, 80, 100, 120, 130}
			};
		System.out.println("len = " + mat.length + ", len[0] = " + mat[0].length);
		for (int i = 0; i < mat.length; i++)
			System.out.println(Arrays.toString(mat[i]));
		
		
	}

	// 11.1
	public static int[] mergeNoExtraSpace(int[] a, int[] b) {
		int ai = a.length - b.length - 1;
		int bi = b.length - 1;
		int pos = a.length - 1;
		while (ai >= 0 && bi >= 0) {
			if (a[ai] >= b[bi]) {
				a[pos--] = a[ai--];
			} else {
				a[pos--] = b[bi--];
			}
		}
		while (bi >= 0)
			a[pos--] = b[bi--];
		return a;
	}

	public static int[] merge(int[] a, int[] b) {
		int ai = 0;
		int bi = 0;
		int tmpi = 0;
		int[] tmp = new int[a.length];
		while (bi < b.length && tmpi < tmp.length) {
			if (b[bi] <= a[ai]) {
				tmp[tmpi++] = b[bi++];
			} else {
				tmp[tmpi++] = a[ai++];
			}
		}
		while (tmpi < tmp.length) {
			tmp[tmpi++] = a[ai++];
		}
		return tmp;
	}

	public static void print(int[] a) {
		System.out.print("[");
		for (int i = 0; i < a.length - 1; i++)
			System.out.print(a[i] + " ");
		System.out.print(a[a.length - 1] + "]\n");
	}

	// 11.2
	public static String[] sortAsAnagrams(String[] a) {
		// 0. Store original indices
		HashMap<String, Stack<String>> indices = new HashMap<String, Stack<String>>();

		// 1. Sort chars within strings
		String[] sorted_chars = new String[a.length];
		for (int i = 0; i < a.length; i++) {
			String from = a[i];
			String to = String.valueOf(iSort(from.toCharArray()));
			if (indices.containsKey(to)) {
				Stack<String> stack = indices.get(to);
				stack.add(from);
				indices.put(to, stack);
			} else {
				Stack<String> stack = new Stack<String>();
				stack.add(from);
				indices.put(to, stack);
			}
			sorted_chars[i] = to;
		}

		// 2. Sort strings lexicographically
		String[] sorted_strings = new String[a.length];
		sorted_strings = iSortStrings(sorted_chars);

		// 3. Gather original strings
		String[] result = new String[a.length];
		for (int i = 0; i < a.length; i++) {
			Stack<String> stack = indices.get(sorted_strings[i]);
			result[i] = (String) stack.pop();
		}
		return result;
	}

	static boolean compareStrings(String s1, String s2) {
		char[] sc1 = s1.toCharArray();
		char[] sc2 = s2.toCharArray();
		int i = 0;
		while (i < sc1.length && i < sc2.length) {
			if (sc1[i] < sc2[i]) {
				return true;
			} else if (sc1[i] > sc2[i]) {
				return false;
			} else {
				i++;
			}
		}
		return i == sc1.length ? true : false;
	}

	private static String[] iSortStrings(String[] a) {
		for (int i = 1; i < a.length; i++) {
			String x = a[i];
			int j;
			for (j = i; j > 0 && compareStrings(a[j - 1], x); j--) {
				a[j] = a[j - 1];
			}
			a[j] = x;
		}
		return a;
	}

	private static char[] iSort(char[] a) {
		for (int i = 1; i < a.length; i++) {
			char x = a[i];
			int j;
			for (j = i; j > 0 && a[j - 1] > x; j--) {
				a[j] = a[j - 1];
			}
			a[j] = x;
		}
		return a;
	}

	// option2
	public static String[] sortByAnagrams(String[] a) {
		Arrays.sort(a, new StringCharComparator());
		return a;
	}

	// option 3 (since no order required)
	public static String[] groupByAnagrams(String[] a) {
		HashMap<String, Stack<String>> buckets = new HashMap<String, Stack<String>>();
		for (String s : a) {
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			String key = new String(chars);
			if (buckets.containsKey(key)) {
				buckets.get(key).add(s);
			} else {
				Stack<String> stack = new Stack<String>();
				stack.add(s);
				buckets.put(key, stack);
			}
		}
		String[] result = new String[a.length];
		int i = 0;
		for (String key : buckets.keySet()) {
			Stack<String> stack = buckets.get(key);
			while (!stack.empty()) {
				result[i++] = (String) stack.pop();
			}
		}
		return result;
	}

	// 11.3
	public static int findInSortedArray(int[] a, int x) {
		// 1. Find rotation point
		int left = 0;
		int right = a.length - 1;
		if (a[0] > a[a.length - 1]) {
			while (a[left] > a[right]) {
				int mid = left + (right - left) / 2;
				if (a[mid] > a[right]) {
					left = mid + 1;
				} else {
					right = mid;
				}
			}
			if (x < a[left]) {
				return binSearch(a, x, 0, left);
			} else if (x > a[left]) {
				return binSearch(a, x, left, a.length - 1);
			} else {
				return left;
			}
		} else {
			// it is just sorted
			return binSearch(a, x, 0, a.length - 1);
		}
	}

	private static int binSearch(int[] a, int x, int left, int right) {
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (a[mid] < x)
				left = mid + 1;
			else if (a[mid] > x)
				right = mid - 1;
			else
				// a[mid] == x
				return mid;
		}
		return -1;
	}

	// 11.5
	public static int findStringInSortedArray(String[] a, String x){
		// Assuming sorted mean sorted lexicographically
		// Brute Force solution yields O(n).	
		// Could have done BinarySearch, however we have spaces interspersed.
		// What if just move left-right if empty?
		
		int left = 0;
		int right = a.length - 1;
		while (left <= right){ //TO DO check that 
			int mid = left + (right - left) / 2;
			if (a[mid].equals("")){
				int old_mid = mid++;
				while (a[mid].equals("")){
					mid++;
				}
				if (mid >= right){
					mid = old_mid;
					while (a[mid].equals("")){
						mid--;						
					}
					if (mid <= left && a[mid].compareTo(x) != 0){
						return -1;
					}
				}
			}
			if (a[mid].compareTo(x) < 0){ // a[mid] is to the left of x
				left = mid + 1;
			}else if(a[mid].compareTo(x) > 0){
				right = mid - 1;
			}else{ // found
				return mid;
			}
		}
		return -1;
	}
	
	// 11.6
	public static int[] matrixBinarySearch(int[][] a, int x){
		int low = 0; int high = a.length - 1;
		if (a[0][0] > x || a[a.length - 1][a[0].length - 1] < x){
			return new int[]{-1, -1};
		}
		int mid = 0;
		while (low <= high){ //TO DO : check if && instead
			mid = low + (high - low) / 2;
			if (x < a[mid][0]){
				high = mid - 1;
			}else if (x > a[mid][a[mid].length - 1]){
				low = mid + 1;
			}else{
				break;
			}
		}
		int row = mid;
		low = 0; 
		high = a[0].length;
		while (low <= high){
			mid = low + (high - low) / 2;
			if (x < a[row][mid]){
				high = mid;
			}else if(x > a[row][mid]){
				low = mid;
			}else{
				return new int[]{row, mid};
			}
		}
		return new int[]{-1, -1};
	}
}

class StringCharComparator implements Comparator<String> {
	private String sort(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

	public int compare(String s1, String s2) {
		return sort(s1).compareTo(sort(s2));
	}
}

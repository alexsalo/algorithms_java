package sort;

import java.util.Arrays;
import java.util.HashMap;

import linkedLists.Lists.Stack;

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
		
		print(b); print(a);
		print(merge(a, b));
		print(mergeNoExtraSpace(a, b));
		
		String[] strings = new String[]{"aba", "cca", "aab", "c", "bbc", "baa", "cbb", "acc"};
		System.out.println(Arrays.toString(sortAsAnagrams(strings)));
	}
	
	public static int[] mergeNoExtraSpace(int[] a, int[] b){
		int ai = a.length - b.length - 1;
		int bi = b.length - 1;
		int pos = a.length - 1;
		while(ai >= 0 && bi >= 0){
			if (a[ai] >= b[bi]){
				a[pos--] = a[ai--];
			}else{
				a[pos--] = b[bi--];
			}
		}
		while(bi >= 0)
			a[pos--] = b[bi--];
		return a;
	}
	
	public static int[] merge(int[] a, int[] b){
		int ai = 0;
		int bi = 0;
		int tmpi = 0;
		int[] tmp = new int[a.length];
		while (bi < b.length && tmpi < tmp.length){
			if (b[bi] <= a[ai]){
				tmp[tmpi++] = b[bi++];
			}else{
				tmp[tmpi++] = a[ai++];
			}
		}
		while (tmpi < tmp.length){
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
	public static String[] sortAsAnagrams(String [] a){
		// 0. Store original indices
		HashMap<String, Stack> indices = new HashMap<String, Stack>();
		
		// 1. Sort chars within strings
		String[] sorted_chars = new String[a.length];
		for (int i = 0; i < a.length; i++){
			String from = a[i];
			String to = String.valueOf(iSort(from.toCharArray()));
			if (indices.containsKey(to)){
				Stack stack = indices.get(to);
				stack.add(from);
				indices.put(to, stack);
			}else{
				Stack stack = new Stack();
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
		for (int i = 0; i < a.length; i++){
			Stack stack = indices.get(sorted_strings[i]);
			result[i] = (String) stack.pop();			
		}
		return result;
	}

	static boolean compareStrings(String s1, String s2){
		char[] sc1 = s1.toCharArray();
		char[] sc2 = s2.toCharArray();
		int i = 0;
		while (i < sc1.length && i < sc2.length){
			if (sc1[i] < sc2[i]){
				return true;
			}else if (sc1[i] > sc2[i]){
				return false;
			}else{
				i++;
			}
		}
		return i == sc1.length ? true : false;
	}

	private static String[] iSortStrings(String[] a){
		for (int i = 1; i < a.length; i++){
			String x = a[i];
			int j;
			for (j = i; j > 0 && compareStrings(a[j-1], x); j--){
				a[j] = a[j-1];
			}
			a[j] = x;
		}
		return a;
	}

	private static char[] iSort(char[] a){
		for (int i = 1; i < a.length; i++){
			char x = a[i];
			int j;
			for (j = i; j > 0 && a[j-1] > x; j--){
				a[j] = a[j-1];
			}
			a[j] = x;
		}
		return a;
	}

}

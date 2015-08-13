package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeQuestions {

	public static void main(String[] args) {
		System.out.println(isAllCharsUnique("Stringt"));
		System.out.println(isAllCharsUniqueNoExtraStructure("Stringt"));
		System.out.println(InsertionSort(Arrays.asList(1,2,5,3,6,1,3)));
		System.out.println(reverse("astkra"));
		System.out.println(isPermutation("hellow", "elwohl"));
		System.out.println(Arrays.toString(sort_int(new int[]{4,1,5,3,2,1})));
		System.out.println(isPerm("hellow", "elwohl"));

		System.out.println(replaceSpaces("mr john Smith111111".toCharArray(), 13));
		System.out.println(replaceSpacesStrings("mr john Smith"));
		System.out.println(Arrays.toString(sortInt(new int[]{4,1,5,3,2,1})));
		System.out.println(containedInBoth("hellow", "wel"));
		
		System.out.println(Integer.toBinaryString(72));
		System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(0.72)));
	}
	
	static boolean isAllCharsUnique(String str){
		ArrayList<Character> chars = new ArrayList<Character>();
		Character c;
		for (int i = 0; i < str.length(); i++){
			c = str.charAt(i);
			if (chars.contains(c)){
				return false;
			}else{
				chars.add(c);
			}
		}	
		return true;
	}
	
	static boolean isAllCharsUniqueNoExtraStructure(String str){
		for (int i = 0; i < str.length(); i++){
			for (int j = i + 1; j < str.length(); j++){
				if (str.charAt(i) == str.charAt(j)){
					return false;
				}
			}
		}
		return true;
	}
	
	static List<Integer> InsertionSort(List<Integer> list){
		for (int i = 1; i < list.size(); i ++){
			int x = list.get(i);
			int j;
			for (j = i; j > 0 && list.get(j - 1) > x; j --){
				list.set(j, list.get(j-1));
			}
			list.set(j, x);
		}
		return list;
	}
	
	static String reverse(String str){ // O(n/2)
		char[] string = str.toCharArray();
		int len = string.length;
		for (int i = 0; i < len / 2; i++){
			char tmp = string[i];
			string[i] = string[len - 1 -i];
			string[len - 1 -i] = tmp;
		}		
		return String.valueOf(string);
	}
	
	static char[] sort(char[] c){
		for (int i = 1; i < c.length; i++){
			char x = c[i];
			int j;
			for (j = i; j > 0 && c[j - 1] < x; j--){
				c[j] = c[j-1];
			}
			c[j] = x;
		}
		return c;
	}

	static boolean isPermutation(String s1, String s2){ //O(n^2)
		if (s1.length() != s2.length()){
			return false;
		}
		
		char[] chars1 = s1.toCharArray();
		char[] chars2 = s2.toCharArray();
		chars1 = sort(chars1);
		chars2 = sort(chars2);
		for (int i = 0; i < chars1.length; i++){
			if (chars1[i] != chars2[i]){
				return false;
			}
		}
		return true;
	}
	
	static int[] sort_int(int a[]){
		for (int i = 1; i < a.length; i++){
			int x = a[i];
			int j;
			for (j = i; j > 0 && a[j - 1] > x; j--){
				a[j] = a[j - 1];
			}
			a[j] = x;
		}
		return a;
	}
	
	static boolean isPerm(String s1, String s2){ // assuming ASCI
		if (s1.length() != s2.length()){
			return false;
		}
		
		int[] counters = new int[256];
		for (char c : s1.toCharArray()){
			counters[(int)c]++;
		}
		for (char c : s2.toCharArray()){
			if (--counters[(int)c] < 0){
				return false;
			}
		}
		return true;
	}
	
	static char[] replaceSpaces(char[] str, int len){
		int spaceCnt = 0;
		for (int i = 0; i < len; i ++){
			if (str[i] == ' '){
				spaceCnt ++;
			}
		}
		int newlen = len + 2 * spaceCnt;
		str[newlen] = '\0';
		for (int i = len - 1; i >= 0; i--){
			if (str[i] != ' '){
				str[newlen - 1] = str[i];
				newlen--;
			}else{
				str[newlen - 1] = '0';
				str[newlen - 2] = '2';
				str[newlen - 3] = '%';
				newlen-=3;
			}
		}
		return str;
	}
	
	static String replaceSpacesStrings(String str){
		String newstr = "";
		for (int i = 0; i < str.length(); i++){
			if (str.charAt(i) == ' '){
				newstr += "%20";
			}else{
				newstr += String.valueOf(str.charAt(i));
			}
		}
		return newstr;
	}
	
	static int[] sortInt(int[] a){
		for (int i = 1; i < a.length; i++){
			int x = a[i];
			int j;
			for (j = i; j > 0 && a[j - 1] > x; j--){
				a[j] = a[j-1];
			}
			a[j] = x;
		}
		return a;
	}
	
	static String containedInBoth(String a, String b){
		char[] ac = a.toCharArray();
		char[] bc = b.toCharArray();
		
		int[] alphabet = new int[256];
		for (char c : bc){
			alphabet[c]++;
		}
		String s = "";
		for (int i = 0; i < ac.length; i++){
			char c = ac[i];
			if (alphabet[c] > 0){
				s += String.valueOf(c);
				alphabet[c]--;
			}
		}
		return s;
	}

}

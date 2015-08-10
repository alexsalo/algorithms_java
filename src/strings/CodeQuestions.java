package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeQuestions {

	public static void main(String[] args) {
		System.out.println(isAllCharsUnique("Stringt"));
		System.out.println(isAllCharsUniqueNoExtraStructure("Stringt"));
		System.out.println(InsertionSort(Arrays.asList(1,2,5,3,6,1,3)));
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

}

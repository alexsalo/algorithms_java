package strings;

import java.util.HashSet;
import java.util.Set;

public class ParseOneString {
	private static Set<String> dict;	

	private static int findMaxWordLen(Set<String> dict){
		int maxWordLen = 0;
		for (String s : dict)
			if (s.length() > maxWordLen)
				maxWordLen = s.length();
		return maxWordLen;
	}
	
	private static String capFirst(String s){
		char[] data = s.toCharArray();
		data[0] += (int)('A'-'a');
		return String.valueOf(data);
	}

	public static String uncapitalize(String input){		
		// 0. Want to give high priority to longer words, check backwards
		// 1. Not all words are in dict
		String res = "";
		int maxWordLen = findMaxWordLen(dict);				
		int start = 0;
		int end = maxWordLen;
		int len = input.length();
		boolean newWordAlert = false;
		int newWordStart = 0;
		while (start < len){		//check 
			while (end > len) // do not check beyond the input string
				end--;
			String word = input.substring(start, end);
			if (dict.contains(word)){
				if (newWordAlert){
					res += capFirst(input.substring(newWordStart, start)) + " ";
					newWordAlert = false;
					}
			
				res += word + " ";
				start = end;
				end = start + maxWordLen;
			}else{
				if ((start + 1) < end) // could be word of at least 1 char
					end--;
				else{ // There must be a word that is not in the dict
					if (!newWordAlert) // fix new word start
						newWordStart = start;
					newWordAlert = true;
					start++;
					end = start + maxWordLen;			
				}				
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String data = "jesslookedjustliketimbrother";
		String dataSimple = "applejustlikeabrother";
		dict = new HashSet<String>();
		dict.add("looked");
		dict.add("just");
		dict.add("like");
		dict.add("this");
		dict.add("is");
		dict.add("awesome");
		dict.add("we");
		dict.add("brother"); dict.add("apple"); dict.add("a");
		System.out.println(dict.toString());
		
		System.out.println(uncapitalize(dataSimple));
		System.out.println(uncapitalize(data));
		System.out.println(uncapitalize("thisisawesome"));
		
		
	}

}

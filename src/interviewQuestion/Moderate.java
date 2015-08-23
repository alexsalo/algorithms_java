package interviewQuestion;


import java.util.HashMap;

public class Moderate {
	public static void main(String[] args) {
		System.out.println(wordify(1234));
		System.out.println(wordify(2134));
		System.out.println(wordify(1234567890));
		
		int[] ints = new int[] {-3, -2, -1, 0, 1,2, 2,3,4,5,6};
		pairsToSum(ints, 5);		
		hashPairsToSum(ints, 5);
		printPairsToSum(ints, 5);
	}
	
	// 17.7
	public static String wordify(int num){		
		HashMap<Integer, String> groupNames = new HashMap<Integer, String>();
		groupNames.put(1000, "thousand");
		groupNames.put(1000000, "million");
		groupNames.put(1000000000, "billion");
		
		String s = "";
		int[] keys = new int[]{1000000000, 1000000, 1000};
		for (int key : keys){
			int cur = (num / key) % 1000;
			if (cur == 1){
				s += getStringBelowThousand(cur)
						+ " " + groupNames.get(key);	
			}else if(cur > 1){
				s += getStringBelowThousand(cur)
						+ " " + groupNames.get(key) + "s";	
			}
		}
		s += getStringBelowThousand(num % 1000);
		return s;
	}

	// return String for 0 - 999
	private static String getStringBelowThousand(int x){
		HashMap<Integer, String[]> digits = new HashMap<Integer, String[]>();
		digits.put(0, new String[]{"", ""});
		digits.put(1, new String[]{"one", "ten"});
	    digits.put(2, new String[]{"two", "twenty"});
	    digits.put(3, new String[]{"three", "thirty"});
	    digits.put(4, new String[]{"four", "forty"});
	    digits.put(5, new String[]{"five", "fifty"});
	    digits.put(6, new String[]{"six", "sixty"});
	    digits.put(7, new String[]{"seven", "seventy"});
	    digits.put(8, new String[]{"eight", "eighty"});
	    digits.put(9, new String[]{"nine", "ninety"});
	    
	    int hundreds = (x % 1000) / 100;
	    int tens = (x % 100) / 10;
	    int remainder = x % 10;
	    
	    String s = "";
	    if (hundreds == 1){
	    	s += " " + digits.get(hundreds)[0] + " hundred "; 	    
	    }else if (hundreds > 1){
	    	s += " " + digits.get(hundreds)[0] + " hundreds ";
	    }
	    
	    s += tens == 0 ? "" : digits.get(tens)[1] + " ";
	    s += remainder == 0 ? "" :  digits.get(remainder)[0];
	    
	    return s;
	}
	
	// 17.12
	public static void hashPairsToSum(int[] a, int sum){ // O(n) + Space(n)
		HashMap<Integer, Integer> table = new HashMap<Integer, Integer>();
		int c;
		for (int i : a){
			c = sum - i;
			if (table.containsKey(c)){
				if (table.get(c) == 0){
					System.out.println(i + " + " + c + " = " + sum);
					table.put(c, 1);
				}
			}else{
				table.put(i, 0);
			}
		}
		System.out.println("-------");
	}
	
	public static void printPairsToSum(int[] a, int sum){
		int low = 0;
		int high = a.length - 1;
		while (low < high){
			int s = a[low] + a[high];
			if (s == sum){
				System.out.println(a[low] + " + " + a[high] + " = " + sum);
				low++;
				high--;
			}else if(s < sum){
				low++;
			}else{
				high--;
			}
		}
		System.out.println("-------");
	}

	// O(n*(k+log(n)), k = 32, inplace - no additional space
	public static void pairsToSum(int[] a, int sum){
		// Would be NP-Complete if not restricted by "pairs"
		// There are n * (n - 1) / 2 pairs ~ expect roughly quadratic run time
		// for brute force bubble sort kinda alg
		// what if we sort in n*log(n), then for each number binary search the rest to the sum
		// which is n*log(n). Thus, whoaaaha, we have 2 * n * log(n) which is awesome!
		// Feature: trim all numbers that are bigger than sum to speed up
		//	in that special case (no negatives)
		
		radixSort(a);
		for (int x : a){
			if (binarySearch(a, sum - x) != -1){
				System.out.println(x + " + " + (sum - x) + " = " + sum);
			}
		}	
		System.out.println("-------");
	}

	private static int binarySearch(int[] a, int x){
		int left = 0;
		int right = a.length - 1;
		while (left <= right){
			int mid = left + (right - left) / 2;
			if (a[mid] < x){
				left = mid + 1;
			}else if (a[mid] > x){
				right = mid - 1;
			}else{
				return mid;
			}
		}
		return -1;
	}

	private static void radixSort(int[] a){ // LSD ~O(kn)
		for (int shift = Integer.SIZE - 1; shift >= 0; shift--){
			int[] zeros = new int[a.length];
			int nzeros = 0;
			for (int i = 0; i < a.length; i++){
				boolean move = (a[i] << shift) < 0 ? false : true;
				if (shift == 0 ? !move : move){
					zeros[nzeros++] = a[i];
				}else{
					a[i - nzeros] = a[i];
				}		
			}
			for (int i = nzeros; i < a.length; i++){
				zeros[i] = a[i - nzeros];
			}
			a = zeros;
		}
	}
}

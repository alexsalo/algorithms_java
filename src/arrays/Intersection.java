package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Intersection {

	public static void main(String[] args) {
		int[] a = {0,1,2,3,4,5};
		int[] b = {1,2,4};
		System.out.println(intersectionON(a, b));
		System.out.println(intersection(a, b));
		System.out.println(binarySearch(a, 4, 2));
		System.out.println(intersectionFast(a, b));
	}

	static ArrayList<Integer> intersectionON(int[] a, int[] b){
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		for (int i = 0; i < a.length; i++){
			if (counts.containsKey(a[i])){
				counts.put(a[i], counts.get(a[i]) + 1);
			}else{
				counts.put(a[i], 1);
			}
		}
		ArrayList<Integer> inter = new ArrayList<Integer>();
		for (int i = 0; i < b.length; i++){
			if (counts.containsKey(b[i])){
				if (counts.get(b[i]) > 0){
					inter.add(b[i]);
					counts.put(a[i], counts.get(a[i]) - 1);
				}
			}
		}
		return inter;
	}
	
	static ArrayList<Integer> intersection(int[] a, int[] b){
		ArrayList<Integer> inter = new ArrayList<Integer>();
		int ai = 0;
		int bi = 0;
		
		while (ai < a.length && bi < b.length){
			if (a[ai] == b[bi]){
				inter.add(a[ai]);
				ai++;
				bi++;
			}else if (a[ai] < b[bi]){
				ai++;
			}else{
				bi++;
			}
		}
		return inter;
	}
	
	static ArrayList<Integer> intersectionFast(int[] a, int[] b){
		return a.length < b.length ? intersectionLogN(a, b) : intersectionLogN(b, a);
	}

	static ArrayList<Integer> intersectionLogN(int[] a, int[] b){
	    ArrayList<Integer> inter = new ArrayList<Integer>();
	    int ai = 0;
	    int bi;
		int bstart = 0;
		while (ai < a.length){
			bi = binarySearch(b, a[ai], bstart); //could keep bstart while bsearch
	        if (bi != -1){
	            inter.add(a[ai]);
	            bstart = bi;
	        }
	        ai++;
	    }
	    return inter;
	}

	static int binarySearch(int[] b, int x, int start){
		int end = b.length - 1;
		while (start <= end){
			int mid = start + (end - start) / 2 ;
			if (b[mid] == x){
				return mid;
			}else if (b[mid] < x){
				start = mid + 1;
			}else{
				end = mid - 1;
			}
		}
		return -1;
	}
}

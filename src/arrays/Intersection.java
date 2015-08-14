package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Intersection {

	public static void main(String[] args) {
		int[] a = {1,2,3};
		int[] b = {1,2,4};
		System.out.println(intersectionON(a, b));
		System.out.println(intersection(a, b));
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
}

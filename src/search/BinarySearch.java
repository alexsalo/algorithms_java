package search;

import java.util.List;

public class BinarySearch {
	// perform binary search on ascendantly sorted array
	public int binary_search(List<Integer> list, int value) {
		int lo = 0;
		int hi = list.size() - 1;
		while (lo != hi){
			//because mid = (hi + lo)/2 might overflow memory
			int mid = lo + (hi - lo) / 2;
			if (value == list.get(mid)) 
				return mid;
			
			if (value > list.get(mid)) 
				lo = mid + 1;
			else 
				hi = mid - 1;
		}
		return -1;
	}
}

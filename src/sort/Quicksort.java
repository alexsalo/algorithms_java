package sort;

import java.util.ArrayList;
import java.util.List;

public class Quicksort {
	public List<Integer> qsort(List<Integer> list){
		//base case
		if (list.size() < 2){
			return list;
		}
		
		//choose pivot 
		int pivot = list.get(0);		
	
		//find low, pivots and high elems
		List<Integer> low = new ArrayList<Integer>();
		List<Integer> high = new ArrayList<Integer>();
		List<Integer> pivots = new ArrayList<Integer>();
		for (int i : list){
			if (i < pivot){
				low.add(i);
			}else if (i == pivot){
				pivots.add(i);
			}
			else{
				high.add(i);
			}
		}
		
		//qsort on low and high recursively
		low = qsort(low);
		high = qsort(high);
		
		//get here eventually after all recursive calls
		low.addAll(pivots);
		low.addAll(high);
		return low;		
	}
}

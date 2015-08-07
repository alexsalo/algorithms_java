package sort;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {	
	
	public List<Integer> insertionSort(List<Integer> list){
		for (int i = 1; i < list.size(); i ++){
			System.out.println(i);
			int x = list.get(i);
			int j;
			for (j = i; j > 0 && list.get(j - 1) > x; j--){
				list.set(j, list.get(j-1));
			}
			list.set(j,  x);
		}
		return list;		
	}
}


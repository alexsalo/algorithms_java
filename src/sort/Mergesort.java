package sort;

import java.util.ArrayList;
import java.util.List;

public class Mergesort<T> {	
	
	private List<Comparable<T>> merge(List<Comparable<T>> l, List<Comparable<T>> r){
		List<Comparable<T>> result = new ArrayList<Comparable<T>>();
		int i = 0; 
		int j = 0;
		while (i < l.size() & j < r.size()){			
			if (l.get(i).compareTo((T) r.get(j)) < 0){
				result.add(l.get(i));
				i++;
			}else{
				result.add(r.get(j));
				j++;
			}
		}
		while (i < l.size()){
			result.add(l.get(i));
			i++;
		}
		while (j < r.size()){
			result.add(r.get(j));	
			j++;
		}
		return result;		
	}
	
	public List<Comparable<T>> mergesort(List<Comparable<T>> list){
		if (list.size() == 1)
			return list;
		int mid = list.size() / 2;
		return merge(mergesort(list.subList(0, mid)), mergesort(list.subList(mid, list.size())));
	}
}

package sort;

import hash.HashTable;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import search.BinarySearch;
import utils.Plot;

public class Test {

	public static void main(String[] args) throws IOException {
		Mergesort mergsort = new Mergesort();
		Quicksort qsort = new Quicksort();		
		List<Integer> list = new ArrayList<>(Arrays.asList(101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15));
		
		//System.out.println(list);
		//System.out.println(mergsort.mergesort(list));
		//System.out.println(qsort.qsort(list));
		//System.out.println(sort(list));
		
		//int size = 100000;
//		int[] sizes = {1000, 10000, 100000};//, 1000000}, 5000000, 10000000, 50000000};
//		List<Duration> tqsort_time = new ArrayList<Duration>();
//		List<Duration> tmsort_time = new ArrayList<Duration>();
//		
//		for (double size : sizes){
//			Instant startTime = Instant.now();		
//			qsort.qsort(genrand((int)size));
//			Instant endTime = Instant.now();		
//			tqsort_time.add(Duration.between(startTime, endTime));
//			
//			startTime = Instant.now();		
//			qsort.qsort(genrand((int)size));
//			endTime = Instant.now();		
//			tmsort_time.add(Duration.between(startTime, endTime));
//		}
//		
//		System.out.println(tqsort_time);
//		System.out.println(tmsort_time);
//		
//		HashTable t = new HashTable();
//		t.put(1, 150);
//		t.put(5, 500);
//		System.out.println(t.get(1));
		
		BinarySearch bs = new BinarySearch();
		System.out.println(sort(list));
		System.out.println(bs.binary_search(sort(list), 3));
	}
	
	private static List<Integer> genrand(int size){
		Random rand = new Random();
		int i = 0;
		List<Integer> list = new ArrayList<Integer>();
		while (i++ < size){
			list.add(rand.nextInt());
		}
		return list;
	}

	
	public static List<Integer> sort(List<Integer> list){
		if (list.size() < 2){
			return list;
		}
		
		int pivot = list.get(0);
		
		List<Integer> pivots = new ArrayList<>();
		List<Integer> low = new ArrayList<>();
		List<Integer> high = new ArrayList<>();
		
		for (int i : list){
			if (i < pivot){
				low.add(i);
			}else if (i == pivot){
				pivots.add(i);
			}else{
				high.add(i);
			}			
		}
		
		low = sort(low);
		high = sort(high);
		
		low.addAll(pivots);
		low.addAll(high);
		return low;
	}
}

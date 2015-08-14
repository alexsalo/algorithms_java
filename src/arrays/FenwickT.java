package arrays;

import java.util.Arrays;

public class FenwickT{
	private int[] data;
	
	public FenwickT(int[] a){		
		data  = new int[a.length];
		for (int i = 0; i < a.length; i++){
			add(i, a[i]);
		}
	}
	
	// add value to ith position
	public void add(int i, int value){
		int opCnt = 0;
		for (; i < data.length; i = i | (i + 1) ){
			data[i] += value;	
			opCnt++;
		}
		System.out.println("value " + String.valueOf(value) + 
				" added // " + String.valueOf(opCnt) + " ops");
	}
	
	// sum of values up to ith position
	public int sum(int i){
		int opCnt = 0;
		int sum = 0;
		for (; i > 0; i = i & (i - 1) ){
			sum += data[i - 1];
			opCnt++;
		}
		System.out.println(String.valueOf(sum) + " // " + 
						String.valueOf(opCnt) + " ops");
		return sum;
	}
	
	// Usage example
	public static void main(String[] args) {
		// init simple array
		int N = 10;
		int[] t = new int[N];
		for (int i = 0; i < t.length; i++){
			t[i] = i;
		}		
		System.out.println(Arrays.toString(t));
		
		//make it FenwickTree
		FenwickT ft = new FenwickT(t);
		System.out.println(Arrays.toString(ft.data));
		
		ft.sum(8);
	
	}
}
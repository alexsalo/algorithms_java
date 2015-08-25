package sort;

import java.util.Arrays;

public class MaxPQ {
	private int[] pq;
	private int N;
	
	public MaxPQ(int[] a){
		this.N = a.length;
		this.pq = new int[N + 1];
		for (int i = 0; i < N; i++){
			pq[i + 1] = a[i];
		}
		System.out.println("Constructing PQ...");
		for (int k = N / 2; k >= 1; k--){
			sink(k);
			print();
		}
	}
	
	private void sortdown(){
		System.out.println("Sorting Down...");
		int[] ordered = new int[N];
		while (N >= 1){
			ordered[N-1] = pq[1];
			pq[1] = pq[N--];
			sink(1);
			System.out.println(Arrays.toString(ordered));
		}
	}
	
	private void exch(int i, int j){
		int tmp = pq[i];
		pq[i] = pq[j];
		pq[j] = tmp;
	}
	
	private boolean less(int i, int j){
		return pq[i] < pq[j];
	}
	
	@SuppressWarnings("unused")
	private void swim(int k){
		while (k > 1 && less(k / 2, k)){ //parent less child
			exch(k, k/2);
			k = k / 2;
		}
	}
	
	private void sink(int k){
		while (2 * k <= N){
			int child = 2 * k;
			// chose left or right child
			if (child < N && less(child, child + 1)) child++;
			if (!less(k, child)) break;
			exch(k, child);
			k = child;
		}
	}
	
	public void print(){
		System.out.print("[");
		for (int i = 1; i < N; i++)
			System.out.print(pq[i] + " ");
		System.out.println(pq[N] + "]");
	}
	

	public static void main(String[] args) {
		//int a[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		int a[] = new int[]{5, 7, 10, 12, 8, 9, 8};
		MaxPQ pq = new MaxPQ(a);
		pq.sortdown();
	}

}

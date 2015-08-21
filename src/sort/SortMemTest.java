package sort;

import java.util.Arrays;

public class SortMemTest {

	public static void main(String[] args) {
		int[] a = new int[] { -15, 101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15, -16};	
		int[] b = new int[] { -15, 101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15, -16};	
		int[] c = new int[] { -15, 101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15, -16};	
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(insertionSort(a)));
		mergeSort(b, 0, b.length - 1);
		System.out.println(Arrays.toString(b));
		System.out.println(Arrays.toString(radixLSD(c)));
	}
	
	// INSERTION SORT
	public static int[] insertionSort(int[] a){
		for (int i = 1; i < a.length; i++){
			int x = a[i];
			int j;
			for (j = i; j > 0 && a[j-1] > x; j--){
				a[j] = a[j-1];
			}
			a[j] = x;			
		}
		return a;
	}

	// MERGE SORT
	public static void mergeSort(int[] a, int left, int right){
		if (left < right){
			int mid = left + (right - left) / 2;
			mergeSort(a, left, mid);
			mergeSort(a, mid + 1, right);
			merge(a, left, mid, right);
		}
	}

	private static void merge(int[] a, int left, int mid, int right){
		int[] t = new int[a.length];
		for (int i = left; i<= right; i++){
			t[i] = a[i];
		}
		int tl = left;
		int tr = mid + 1;
		int cur = left;
		
		while (tl <= mid && tr <= right)
			if (t[tl] < t[tr])
				a[cur++] = t[tl++];
			else
				a[cur++] = t[tr++];
		
		for (int i = 0; i <= mid - tl; i++)
			a[cur + i] = t[tl + i];
	}

	// RADIX SORT
	public static int[] radixLSD(int[] a){
		for (int shift = Integer.SIZE - 1; shift >= 0; shift--){
			int[] zeros = new int[a.length];
			int nzeros = 0;
			
			for (int i = 0; i < a.length; i++){
				boolean move = (a[i] << shift) >= 0; //did not turn neg
				if (shift == 0 ? !move : move)
					zeros[nzeros++] = a[i];
				else
					a[i - nzeros] = a[i];
			}
			for (int i = nzeros; i < a.length; i++)
				zeros[i] = a[i - nzeros];
				
			a = zeros;
		}
		return a;
	}
	
	public static int[] radixLSDSort(int[] a) {
		for (int shift = Integer.SIZE - 1; shift >= 0; shift--){
			int[] tmp = new int[a.length];
			int nzeros = 0;
			
			//move 0s to tmp, 1s to old array
			for (int i = 0; i < a.length; i++){
				//will become negative if was one, if zero - stays >= 0
				boolean move = (a[i] << shift) >= 0;
				if (shift == 0 ? !move : move){
					tmp[nzeros++] = a[i];					
				}else{
					a[i - nzeros] = a[i];
				}
			}
			
			//since we think there will be more 0s we copy 1s to 0s.
			for (int i = nzeros; i < tmp.length; i++){
				tmp[i] = a[i - nzeros];
			}
			a = tmp;
		}
		return a;
	}

}

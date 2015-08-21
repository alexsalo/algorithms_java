package sort;

import java.util.Arrays;

public class SortBlindTests {

	public static void main(String[] args) {
		int[] a = new int[] { -15, 101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15, -16};	
		System.out.println(Arrays.toString(a));
		mergeSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
		
		int[] b = new int[] { -15, 101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15, -16};	
		System.out.println(Arrays.toString(b));
		mergeSort(b, 0, b.length - 1);
		System.out.println(Arrays.toString(b));


	}

	// MergeSort
	public static void mergeSort(int a[], int low, int high){
		if (low < high){
			int mid = low + (high - low) / 2;
			mergeSort(a, low, mid);
			mergeSort(a, mid + 1, high);
			merge(a, low, mid, high);
		}
	}

	static void merge(int[] a, int low, int mid, int high){
		int[] helper = new int[a.length];
		for (int i = low; i <= high; i++){
			helper[i] = a[i];
		}
		
		int help_low = low;
		int current = low;
		int help_high = mid + 1;
		
		while (help_low <= mid && help_high <= high){
			if (helper[help_low] <= helper[help_high]){
				a[current++] = helper[help_low++];
			}else{
				a[current++] = helper[help_high++];
			}
		}
		for (int i = 0; i <= mid - help_low; i++){
			a[current + i] = helper[help_low + i];
		}
	}
	
	// Minimalistic MergeSort
	static void msort(int[] a, int l, int r){
		if (l < r){
			int mid = l + (r - l) / 2;
			msort(a, l, mid);
			msort(a, mid + 1, r);
			mmerge(a, l, mid, r);
		}
	}

	static void mmerge(int[] a, int l, int mid, int r){
		int[] b = new int[a.length];
		for (int i = l; i <= r; i++)
			b[i] = a[i];
		int bl = l; int cur = l; int br = mid + 1;
		while (bl <= mid && br <= r)
			if (b[bl] < b[br])
				a[cur++] = b[bl++];
			else
				a[cur++] = b[br++];
		for (int i = 0; i <= mid - bl; i++)
			a[cur + i] = b[bl + i];
	}
}

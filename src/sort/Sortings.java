package sort;

public class Sortings {

	public static void main(String[] args) {
		int[] a = new int[] { 101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15, -15 };
		int[] b = new int[] { 5, 3, 1 };
		int[] c = new int[] { 101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15, -15 };
		int[] e = new int[] { 101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15, -15 };
		int[] f = new int[] { -15, 101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15, -16 };		
		
		print(insertionSort(a));
		
		mergeSort(b);
		print(b);

		quickSort(c);
		print(c);
		System.out.println("Binary Search: " + binarySearch(c, 2));
		
		print(radixLSDSort(e));
		
		print(f);
		radRSBsort(f); 
		print(f);
		printBinary(new int[]{-1, -3, -5, -6, 15, -15});
		System.out.println(Integer.toBinaryString(15 & (1 << 3)));
		System.out.println(Integer.toBinaryString(-15 & (1 << 3)));
	}
	
	public static int binarySearch(int[] a, int x){
		int left = 0;
		int right = a.length - 1;
		while (left <= right){
			int mid = left + (right - left) / 2;
			if (a[mid] < x){
				left = mid + 1;
			}else if(a[mid] > x){
				right = mid - 1;
			}else{
				return mid;
			}
		}
		return -1;
	}
	
	public static void radRSBsort(int[] a){		
		radRSBsort(a, 0, a.length - 1, Integer.SIZE - 1);
	}
	
	public static void radRSBsort(int[] a, int left, int right, int pos){		
		if (pos >= 0 && left < right){
			int[] zeros = new int[right - left + 1];
			int[] ones = new int[right - left + 1];
			int nzeros = 0;
			int nones = 0;
			for (int i = left; i <= right; i++){
				boolean move = (a[i] & (1 << pos)) == 0;
 				if (pos == Integer.SIZE - 1 ? !move : move){
					zeros[nzeros++] = a[i];
				}else{
					ones[nones++] = a[i];
				}
			}
			
			//copy zeros and ones back to a
			for (int i = 0; i < nzeros; i++){
				a[left + i] = zeros[i];
			}
			for (int i = 0; i < nones; i++){
				a[left + nzeros + i] = ones[i];
			}
			
			radRSBsort(a, left, left + nzeros - 1, pos - 1);
			radRSBsort(a, left + nzeros, right, pos - 1);
		}
	}
	
	
	/** 
	 * Radix Left Significant Digit
	 */
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

	/**
	 * Quick Sort
	 */
	public static void quickSort(int[] a) {
		quickSort(a, 0, a.length - 1);
	}

	private static void quickSort(int a[], int low, int high) {
		int index = partition(a, low, high);
		if (low < index - 1) {
			quickSort(a, low, index - 1);
		}
		if (index < high) {
			quickSort(a, index, high);
		}
	}

	private static int partition(int[] a, int low, int high) {
		int pivot = a[low + (high - low) / 2];
		// print(a, low, high);
		// int old_low = low; int old_high = high;
		// System.out.print(" via pivot = " + String.valueOf(pivot) + " -> ");
		while (low <= high) {
			// go up until found element that should be on the other side
			while (a[low] < pivot)
				low++;
			while (a[high] > pivot)
				high--;

			if (low <= high) { // swap
				int tmp = a[low];
				a[low++] = a[high];
				a[high--] = tmp;
			}
		}
		// print(a, old_low, old_high);
		// System.out.println(", returning low = " + String.valueOf(low));
		return low;
	}

	
	/**
	 * Merge Sort
	 */
	public static void mergeSort(int[] a) {
		mergeSort(a, 0, a.length - 1);
	}

	private static void mergeSort(int[] a, int low, int high) {
		if (low < high) {
			int middle = low + (high - low) / 2;
			mergeSort(a, low, middle);
			mergeSort(a, middle + 1, high);
			merge(a, low, middle, high);
		}
	}

	private static void merge(int[] a, int low, int middle, int high) {
		int[] helper = new int[a.length];
		for (int i = low; i <= high; i++)
			helper[i] = a[i];

		int helpLeft = low;
		int helpRight = middle + 1;
		int current = low;

		while (helpLeft <= middle && helpRight <= high)
			if (helper[helpLeft] < helper[helpRight])
				a[current++] = helper[helpLeft++];
			else
				a[current++] = helper[helpRight++];

		for (int i = 0; i <= middle - helpLeft; i++) {
			a[current + i] = helper[helpLeft + i];
		}
	}

	/**
	 * Insertion Sort
	 */
	public static int[] insertionSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int x = a[i];
			int j;
			for (j = i; j > 0 && a[j - 1] > x; j--) {
				a[j] = a[j - 1];
			}
			a[j] = x;
		}
		return a;
	}
	
	/**
	 * Aux Methods
	 */

	public static void print(int[] a) {
		System.out.print("[");
		for (int i = 0; i < a.length - 1; i++)
			System.out.print(a[i] + " ");
		System.out.print(a[a.length - 1] + "]\n");
	}

	public static void print(int[] a, int from, int to) {
		System.out.print("[");
		for (int i = from; i < to; i++)
			System.out.print(a[i] + " ");
		System.out.print(a[to] + "]");
	}

	public static void printBinary(int[] a) {
		int maxlen = 0;
		for (int i = 0; i < a.length; i++) {
			int len = Integer.toBinaryString(a[i]).length();
			if (len > maxlen) {
				maxlen = len;
			}
		}
		System.out.println("--------");
		for (int i = 0; i < a.length; i++) {
			String s = Integer.toBinaryString(a[i]);
			for (int j = 0; j < maxlen - s.length(); j++)
				System.out.print("0");
			System.out.println(s);
		}
	}

}

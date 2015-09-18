package sort;

import java.util.Arrays;

public class TestMySorts {

	public static void main(String[] args) {
		int[] a = new int[] { -15, 101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15,
				-16 };
		
		//mergeSort(a, 0, a.length - 1);
		inSort(a);
		System.out.println(Arrays.toString(a));
	}
	
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

	public static void inSort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			int x = data[i];
			int j;
			for (j = i; j > 0 && data[j - 1] > x; j--) {
				data[j] = data[j - 1];
			}
			data[j] = x;
		}
	}

	public static void mergeSort(int[] data, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSort(data, left, mid);
			mergeSort(data, mid + 1, right);
			merge(data, left, mid, right);
		}
	}

	private static void merge(int[] data, int left, int mid, int right) {
		int[] helper = new int[data.length];
		for (int i = left; i <= right; i++) {
			helper[i] = data[i];
		}

		int mleft = left;
		int mright = mid + 1;
		int current = left;

		while (mleft <= mid && mright <= right) {
			if (helper[mleft] < helper[mright]) {
				data[current++] = helper[mleft++];
			} else {
				data[current++] = helper[mright++];
			}
		}

		// finish left part if something left
		while (mleft <= mid) {
			data[current++] = helper[mleft++];
		}
		// no need to finish right side since it’s already in data
	}

}

package sort;

import java.util.Arrays;

public class MergeSortPerfect {

	public static void main(String[] args) {
		int[] a = new int[] { -15, 101, 50, 2, 43, 101, 6, 100, 8, 20, 3, 15 };
		mergeSort(a);
		System.out.println(Arrays.toString(a));
	}

	// Public MergeSort wrapper
	public static void mergeSort(int[] data) {
		mergeSort(data, 0, data.length - 1);
	}

	static void mergeSort(int[] data, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2; // to avoid overflow
			mergeSort(data, left, mid);
			mergeSort(data, mid + 1, right);
			merge(data, left, mid, right);
		}
	}

	static void merge(int[] data, int left, int mid, int right) {
		int[] helper = new int[data.length];
		for (int i = left; i <= right; i++) {
			helper[i] = data[i];
		}

		int helperLeft = left;
		int current = left;
		int helperRight = mid + 1;

		while (helperLeft <= mid && helperRight <= right) {
			if (helper[helperLeft] < helper[helperRight]) {
				data[current++] = helper[helperLeft++];
			} else {
				data[current++] = helper[helperRight++];
			}
		}

		// Copy leftovers from the left part
		while (helperLeft <= mid) {
			data[current++] = helper[helperLeft++];
		}

		// No need to copy right part since it's already there
	}

}

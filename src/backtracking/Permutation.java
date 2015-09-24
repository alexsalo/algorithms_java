package backtracking;

import java.util.Arrays;

public class Permutation {
    public static void permutation(String s) {
        permutation("", s);
    }

    private static void permutation(String prefix, String s) {
        int n = s.length();
        if (n == 0)
            System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + s.charAt(i),
                        s.substring(0, i) + s.substring(i + 1, n));
            }
        }
    }

    public static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int m = left + (right - left) / 2;
            mergeSort(a, left, m);
            mergeSort(a, m + 1, right);
            merge(a, left, m, right);
        }
    }

    private static void merge(int[] a, int left, int m, int right) {
        int len = a.length;
        int[] help = new int[len];
        for (int i = left; i <= right; i++) {
            help[i] = a[i];
        }

        int helpLeft = left;
        int helpRight = m + 1;
        int current = left;

        while (helpLeft <= m && helpRight <= right) {
            if (help[helpLeft] <= help[helpRight]) {
                a[current] = help[helpLeft];
                helpLeft++;
            } else {
                a[current] = help[helpRight];
                helpRight++;
            }
            current++;
        }

        // copy leftovers from the left
        while (helpLeft <= m) {
            a[current] = help[helpLeft];
            current++;
            helpLeft++;
        }

        // right side is already there
    }

    public static void main(String[] args) {
        int[] a = new int[]{34,67,5,7,3,65,4,734,366,57,73,3};
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
        permutation("ale");
        
    }

}

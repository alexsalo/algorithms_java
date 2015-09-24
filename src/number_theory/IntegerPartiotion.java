package number_theory;

import java.util.Arrays;

public class IntegerPartiotion {
    public static void printAllIntegerPartitions(int N) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = 0;
        a[0] = N;
        System.out.println(Arrays.toString(a));
        while (a[0] != 1) {
            // case 1 decrease
            // find last
            int pos = 0;
            while (a[pos] != 0)
                pos++;
            int last = pos--;

            // find back last bigger than 0
            while (a[pos] <= 1)
                pos--;
            a[pos]--;
            a[last] = 1;
            
            System.out.println(Arrays.toString(a));
        }
    }

    public static void main(String[] args) {
        printAllIntegerPartitions(4);
    }

}

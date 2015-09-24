package strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LettersPermutations {
    public static Set<String> dict = new HashSet<String>();
    public static String[] permutations;
    public static int counter;

    public static void printAllAnagrams(String string) {
        for (String s : lettersSubsets(string))
            for (String ss : permutation(s))
                if (dict.contains(ss))
                    System.out.println(ss);
    }

    public static String[] permutation(String s) {
        int size = factorial(s.length());
        permutations = new String[size];
        counter = 0;
        permutation("", s);
        return permutations;
    }

    public static int factorial(int N) {
        int res = 1;
        for (int i = 1; i <= N; i++)
            res *= i;
        return res;
    }

    private static void permutation(String prefix, String s) {
        int n = s.length();
        if (n == 0)
            permutations[counter++] = prefix;
        else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + s.charAt(i),
                        s.substring(0, i) + s.substring(i + 1, n));
            }
        }
    }

    public static String[] lettersSubsets(String s) {
        int N = s.length();
        char[] letters = s.toCharArray();
        int total = (int) Math.pow(2, N);
        String[] result = new String[total];

        for (int flags = 0; flags < total; flags++) {
            // 1. get binary of flags
            boolean[] choice = getFalseBools(N);
            int flag = flags;
            int pos = 0;
            while (flag > 0) {
                if (flag % 2 == 1)
                    choice[pos] = true;
                pos++;
                flag = flag / 2;
            }

            // 2. Copy over letters with choice==true
            String buf = "";
            for (int i = 0; i < N; i++)
                if (choice[i])
                    buf += String.valueOf(letters[i]);

            result[flags] = buf;
        }

        return result;
    }

    private static boolean[] getFalseBools(int N) {
        boolean[] choice = new boolean[N];
        for (int i = 0; i < N; i++) {
            choice[i] = false;
        }
        return choice;
    }

    public static void main(String[] args) {
        dict.add("cat");
        dict.add("bob");
        dict.add("bobcat");
        dict.add("abbot");

        System.out.println(Arrays.toString(lettersSubsets("abbcot")));
        System.out.println();
        System.out.println(Arrays.toString(permutation("ale")));
        System.out.println();
        printAllAnagrams("abbcot");
    }

}

package interviewQuestion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

public class Code {
    static int currentSize;
    static int[][] map;
    static int X;
    static int Y;

    /*
     * Dot Product
     */
    public static float dotProduct(float[] a, float[] b) {
        int alen = a.length;
        int blen = b.length;
        if (alen != blen)
            throw new NoSuchElementException();

        float res = 0;
        for (int i = 0; i < alen; i++) {
            res += a[i] * b[i];
        }
        return res;
    }

    /*
     * Random with probabilities
     */
    public static int nonUniformRand(double[] prob) {
        double[] cumsum = new double[prob.length];
        cumsum[0] = prob[0];
        for (int i = 1; i < prob.length; i++)
            cumsum[i] = cumsum[i - 1] + prob[i];

        Random r = new Random();
        float f = r.nextFloat(); // 0-1
        
        // corner cases
        if (f < cumsum[0])
            return 0;
        if (f > cumsum[cumsum.length - 2])
            return cumsum.length - 1;

        // binary search of the interval
        int left = 0;
        int right = cumsum.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (f > cumsum[mid - 1] && f < cumsum[mid])
                return mid;
            if (f < cumsum[mid - 1])
                right = mid - 1;
            else
                left = mid + 1; // check later
        }
        return -1;
    }

    /*
     * Max Island
     */
    public static int findMaxIsland() {
        int maxSize = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 2) {
                    int size = islandSize(i, j);
                    if (size > maxSize) {
                        maxSize = size;
                    }
                }
            }
        }
        return maxSize;
    }

    private static boolean validMove(int x, int y) {
        return (x >= 0 && x < X && y >= 0 && y < Y);
    }

    private static int islandSize(int x, int y) {
        currentSize = 0;
        X = map.length;
        Y = map[0].length;
        move(x, y);
        return currentSize;
    }

    private static void move(int x, int y) {
        if (validMove(x, y) && map[x][y] != 2 && map[x][y] != 0) {
            map[x][y] = 2;
            currentSize++;
            move(x + 1, y);
            move(x - 1, y);
            move(x, y + 1);
            move(x, y - 1);
        }
    }

    public static void main(String[] args) {
        float[] a = new float[] { (float) 0.5, 1 };
        float[] b = new float[] { (float) 2, 3 };
        System.out.println("Dot product is: " + dotProduct(a, b));
        

        double[] prob = new double[] { 0.1,  0.4, 0.5 };
        int N = 200;
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < N; i++){
            int rand = nonUniformRand(prob);
            if (frequencies.containsKey(rand))
                frequencies.put(rand, frequencies.get(rand) + 1);
            else
                frequencies.put(rand, 1);
        }
        System.out.print("Frequencies distribution is: ");
        for (int key : frequencies.keySet()){
            System.out.print(key + ": " + (1.0 * frequencies.get(key)) / N + ", ");
        }
        System.out.println();
        

        map = new int[][] { { 0, 0, 0, 0 }, { 1, 0, 1, 1 }, { 1, 1, 1, 1 },
                { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } };
        System.out.println("Max Island Size is: " + findMaxIsland());
    }

}

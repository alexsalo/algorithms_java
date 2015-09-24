package warmUp;

public class Recursion {

    public static void main(String[] args) {
        System.out.println(countWays(10));
        System.out.println(countWay2s(10));
        System.out.println("Robot go: " + robotGo(1, 1, 11, 11, 0));
        System.out.println("Robot go fact: " + factorial(10 + 10)
                / (factorial(10) * factorial(10)));
    }

    public static long factorial(int N) {
        long res = 1;
        for (int i = 1; i <= N; i++)
            res *= i;
        return res;
    }

    public static int robotGo(int x, int y, int X, int Y, int cnt) {
        if (x <= X && y <= Y) {
            if (x == X && y == Y)
                return cnt + 1;
            return robotGo(x + 1, y, X, Y, cnt) + robotGo(x, y + 1, X, Y, cnt);
        } else {
            return 0;
        }
    }

    public static int countWays(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n == 3)
            return 4;
        return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
    }

    public static int countWay2s(int n) {
        if (n < 0)
            return 0;
        if (n == 1)
            return 1;
        return countWay2s(n - 1) + countWay2s(n - 2) + countWay2s(n - 3);
    }
}

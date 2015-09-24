package games;

public class ChessHorse {
    public static final int N = 8;
    public static boolean finished = false;
    static boolean[][] desk = new boolean[N][N];
    static int[] options = new int[8];

    private static void move(int x, int y) {
        if (!finished)
                if (!desk[x][y]) {
                    desk[x][y] = true;
                    finished = check();
                    print();                  
                                        
                    options[0] = evaluateOptions(x + 2, y + 1);
                    options[1] = evaluateOptions(x + 2, y - 1);
                    options[2] = evaluateOptions(x - 2, y + 1);
                    options[3] = evaluateOptions(x - 2, y - 1);

                    options[4] = evaluateOptions(x + 1, y + 2);
                    options[5] = evaluateOptions(x - 1, y + 2);
                    options[6] = evaluateOptions(x + 1, y - 2);
                    options[7] = evaluateOptions(x - 1, y - 2);
                    
                    int imax = findImax();
                    
                    switch (imax){
                        case 0 : move(x + 2, y + 1); break;
                        case 1 : move(x + 2, y - 1); break;
                        case 2 : move(x - 2, y + 1); break;
                        case 3 : move(x - 2, y - 1); break;
    
                        case 4 : move(x + 1, y + 2); break;
                        case 5 : move(x - 1, y + 2); break;
                        case 6 : move(x + 1, y - 2); break;
                        case 7 : move(x - 1, y - 2); break;
                    }
                        
                } else {
                    desk[x][y] = false;
                }
            
    }

    public static void main(String[] args) {
        move(0, 0);
    }

    private static int findImax(){
        int imax = -1;
        int max = - 1;
        for (int i = 0; i < 8; i++)
            if (options[i] > max){
                max = options[i];
                imax = i;
            }
        return imax;
    }
    
    private static boolean check() {
        for (boolean[] line : desk)
            for (boolean b : line)
                if (!b)
                    return false;
        return true;
    }

    private static int isValidMove(int x, int y) {
        return (x >= 0 && y >= 0 && x < N && y < N && !desk[x][y]) ? 1 : 0;
    }

    private static int evaluateOptions(int x, int y) {
        if (isValidMove(x, y) == 0)
            return -1;

        int options = 0;
        options +=  isValidMove(x + 2, y + 1) +
                    isValidMove(x + 2, y - 1) +
                    isValidMove(x - 2, y + 1) +
                    isValidMove(x - 2, y - 1) +
    
                    isValidMove(x + 1, y + 2) +
                    isValidMove(x - 1, y + 2) +
                    isValidMove(x + 1, y - 2) +
                    isValidMove(x - 1, y - 2);
        return options;
    }

    private static void print() {
        for (boolean[] line : desk) {
            for (boolean b : line) {
                int a = b ? 1 : 0;
                System.out.print(a + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}

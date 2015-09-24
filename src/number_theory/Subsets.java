package number_theory;

import java.util.Arrays;

public class Subsets<T>{
    private int N;
    private T[] data;
    
    public Subsets(T[] data){
        this.data = data;
        this.N = data.length;
    }
    
    @SuppressWarnings("unchecked")
    public T[] getIthSubset(int i){
        boolean[] choice = new boolean[N];
        int pos = 0;
        int cnt = 0;
        while (i > 0){
            if (i % 2 == 1){
                choice[pos] = true;
                cnt++;
            }
            pos++;
            i = i / 2;
        }
        
        T[] res = (T[]) new Object[cnt];
        int r = 0;
        for (int j = 0; j < pos; j++)
            if (choice[j])
                res[r++] = data[j];
        return res;
    }
    
    public void printAllPermutations(){
        for (int i = 0; i < Math.pow(2, N); i++)
            System.out.println(Arrays.toString(getIthSubset(i)));
    }

    public static void main(String[] args) {
        String[] a = new String[]{"alex", "hey", "what", "up"};
        Subsets<String> ss = new Subsets<String>(a);
        ss.printAllPermutations();
    }
}

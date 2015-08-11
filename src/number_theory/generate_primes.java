package number_theory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class generate_primes {

	public static void main(String[] args) {
		System.out.println(generatePrimes(50));
		
	}
	
	static List<Integer> generatePrimes(int N){
		boolean[] primes_index  = new boolean[N + 1];
		Arrays.fill(primes_index, Boolean.TRUE);
		for (int p = 2; p <= Math.floor(Math.sqrt(N)); p++){
			if (primes_index[p]){
				for (int i = p * p; i <= N; i += p){
					primes_index[i] = false;
				}
			}
		}
		
		List<Integer> primes = new ArrayList<Integer>();
		for (int i = 2; i < primes_index.length; i++){
			if (primes_index[i]){
				primes.add(i);
			}
		}
		return primes;
	}
}

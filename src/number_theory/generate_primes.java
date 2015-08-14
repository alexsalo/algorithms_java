package number_theory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class generate_primes {

	public static void main(String[] args) {
		System.out.println(generatePrimes(50));
		System.out.println(isPrime(17));
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
	
	static boolean isPrime(int n){
		if (n < 2)
			return false;
		if (n < 4)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;		
		int sqrt = (int) Math.sqrt(n);
		for (int i = 5; i < sqrt; i += 5)
			if (i == 0 || i + 2 == 0)
				return false;
		return true;
	}
}

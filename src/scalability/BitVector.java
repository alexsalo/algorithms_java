package scalability;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class BitVector {

	public static void main(String[] args) {
		//generateInts(100);
		System.out.println(findOpenNumber());
	}

	static int findOpenNumber() {
		long numberOfInts = ((long) Integer.MAX_VALUE) + 1;
		byte[] bitvector = new byte[(int) (numberOfInts / 8)];

		Scanner sc;
		try {
			sc = new Scanner(new BufferedReader(new FileReader("ints.txt")));
			while (sc.hasNextInt()) {
				int n = sc.nextInt();
				// set bit - we have this number
				bitvector[n / 8] |= 1 << (n % 8);
			}
			sc.close();

			for (int i = 0; i < bitvector.length; i++) {
				for (int j = 0; j < 8; j++) {
					if ((bitvector[i] & (1 << j)) == 0) {
						return i * 8 + j;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		

		return -1;
	}

	// Accessory
	@SuppressWarnings("unused")
	private static void generateInts(int quantity) {
		MyRandom rng = new MyRandom(123);
		try {
			PrintWriter writer = new PrintWriter("ints.txt", "UTF-8");
			for (int i = 0; i < quantity; i++) {
				writer.print(String.valueOf(rng.nextNonNegative()) + " ");
				//writer.print(String.valueOf(i) + " ");
			}
			writer.print(String.valueOf(quantity + 5));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("serial")
	public static class MyRandom extends Random {
	    public MyRandom() {}
	    public MyRandom(int seed) { super(seed); }

	    public int nextNonNegative() {
	        return next(Integer.SIZE - 1);
	    }
	}

}

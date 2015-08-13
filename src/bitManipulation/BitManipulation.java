package bitManipulation;

public class BitManipulation {

	public static void main(String[] args) {
		System.out.println(getBit(6, 0));
		System.out.println(clearBitsIthrough0(7, 1));
		System.out.println(Integer.toBinaryString(1024));
		System.out.println(Integer.toBinaryString(19));
		System.out.println(Integer.toBinaryString(insertMintoNJThroughI(1024, 19, 6, 2)));
		
		System.out.println(Integer.toBinaryString(31));
		System.out.println(Integer.toBinaryString(14));
		System.out.println(howManyBitsToConvertAtoB(31, 14));
		System.out.println(bitCount(15));
		System.out.println(howManyBitsConvertAtoB(31, 14));
		
		System.out.println(sortChars("red small fox".toCharArray()));
		System.out.println(countBits(7));
		System.out.println(countBits2(7));
		
		System.out.println(addWithoutAriphmetic(-21, 15));
	}

	static boolean getBit(int num, int i){
		int mask = 1 << i;
		return (num & mask) != 0;
	}

	static int setBit(int num, int i){
		int mask = 1 << i;
		return num | mask;
	}

	static int clearBit(int num, int i){
		int mask =  ~(1 << i);
		return num & mask;
	}

	static int clearBitsMSBthroughI(int num, int i){
		int mask = (1 << i) - 1;
		return num & mask;
	}

	static int clearBitsIthrough0(int num, int i){
		int mask = ~((1 << (i + 1)) - 1);
		return num & mask;
	}

	static int updateBit(int num, int i, int value){
		int mask =  ~(1 << i);
		num = num & mask; //clearBit
		value = value << i;
		return num | value;
	}
	
	static int insertMintoNJThroughI(int N, int M, int j, int i){
		//clear j through i in N
		int mask = ~((int)(Math.pow(2, (j-i+1))-1) << i);
		N = N & mask;
		//or N j-i with M
		N = N | (M << i);
		return N;	
	}
	
	static int bitcount(int x){
		return (x==0) ? 0 : (1 + bitcount(x & (x - 1)));
	}	

	static int howManyBitsToConvertAtoB(int a, int b){
		int xor = a ^ b;
		return bitcount(xor);
	}
	
	static int bitCount(int x){
		System.out.println(Integer.toBinaryString(x));
		if (x==0)
			return 0;
		else{
			int t = bitCount(x & (x - 1));
			return 1 + t;
		}
	}
	
	static int howManyBitsConvertAtoB(int a, int b){
		int count = 0;
		for (int i = a ^ b; i > 0; i = i & (i - 1)){
			count++;
		}
		return count;
	}
	
	static char[] sortChars(char[] a){
		for (int i = 1; i < a.length; i++){
			char x = a[i];
			int j;
			for (j = i; j > 0 && a[j-1] > x; j--){
				a[j] = a[j-1];
			}
			a[j] = x;
		}
		return a;
	}

	static int countBits(int num){
		int cnt = 0;
		for (int i = num; i != 0; i = i & (i - 1)){
			cnt++;
		}
		return cnt;
	}

	static int countBits2(int num){
		int cnt = 0;
		for (int i = num; i > 0; i = i / 2){
			if ((num & 1) == 1){
				cnt ++;
			}
			num = num >> 1;
		}
		return cnt;
	}	
	
	static int addWithoutAriphmetic(int a, int b){
		if (b == 0){
			return a;
		}
		int sum = a ^ b;
		int carry = (a & b) << 1;
		return addWithoutAriphmetic(sum, carry);
	}
	
	
}

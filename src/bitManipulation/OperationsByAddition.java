package bitManipulation;

public class OperationsByAddition {

	public static void main(String[] args) {
		System.out.println(negate(-7));
		System.out.println(substract(-7, 5));
		System.out.println(multiply(-2, -7));
		System.out.println(divide(-10, 2));
	}
	
	static int negate(int x){
		int d = x < 0 ? 1 : -1;
		int neg = 0;
		while (x != 0){
			x += d;
			neg += d;
		}
		return neg;
	}
		

	static int substract(int a, int b){
		return a + negate(b);
	}

	static int multiply(int a, int b){
		if (a < b){ //to make if faster
			int tmp = a;
			a = b;
			b = tmp;
		}
		int res = 0;
		boolean bneg = b < 0;
		if (bneg){
			b = negate(b);
		}
		for (int i = 0; i < b; i++){
			res += a;
		}
		if (bneg){
			res = negate(res);
		}
		return res;
	}

	static int divide(int a, int b){
		if (b == 0){
			throw new java.lang.ArithmeticException("divide by zero");
		}	
		boolean aneg = a < 0;
		boolean bneg = b < 0;
		
		if (aneg){
			a = negate(a);
		}
		if (bneg){
			b = negate(b);
		}
		
		if (a < b){
			return 0;
		}
		
		int res = 0;
		int sum = b;
		while (sum <= a){
			sum += b;
			res ++;
		}
		
		if ((aneg && !bneg) || (!aneg && bneg)){
			res = negate(res);
		}
		return res;
	}

}

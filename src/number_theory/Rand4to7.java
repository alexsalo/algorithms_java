package number_theory;

import java.util.Random;

public class Rand4to7 {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.print(rand4() + " ");
		System.out.println();
		for (int i = 0; i < 10; i++)
			System.out.print(rand7() + " ");

	}
	
	public static int rand7(){
		int[][] val7 = {
				{1,2,3,4,5,},
				{6,7,1,2,3,},
				{4,5,6,7,1,},
				{2,3,4,5,6,},
				{7,0,0,0,0}
		};
		
		int res = 0;
		while (res == 0){
			int i = rand4();
			int j = rand4();
			res = val7[i][j];
		}
		return res - 1;
	}
	
	private static int rand4(){
		Random rand = new Random();
		return rand.nextInt(4);
	}

}

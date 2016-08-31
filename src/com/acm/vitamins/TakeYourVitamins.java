package com.acm.vitamins;

import java.util.Scanner;

public class TakeYourVitamins {
	
	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		
		//A U R V
		//A - aount of a vitamin/mineral present in one serving (float)
		//U - units in which A is measured (a string of alphabetic characters, no embedded spaces)
		//R - minimum daily rerquirement for the vitamin/mineral (same units as A) (float)
		//V - name of the vitamin/mineral (string of characters, could include spaces, 
		//all 4 variables will be seperated by one space and V will be terminated by the end of the input line
		//end of input is signaled by a line in which A is negative
		
//		For each line of input data, your program should determine the percentage of the recommended daily requirement being provided for that vitamin/mineral.
//		If it is at least 1%, your program should print a line of the form
//		
//		V A U P%
//		where V, A, and U are the quantities from the input, 
//		and P is the percentage of the minimum daily requirement represented by the amount A. 
//
//		V should be printed left-justified on the line. 
//		A should be printed with 1 digit precision, and P with zero digits precision. V, A, U, and P should be separated by one space each.
//
//		After the last such line, your program should print a line stating
//		Provides no significant amount of:
//		followed by a list of the names of all vitamins/minerals which are provided as less than 1% of the minimum daily requirement. 
//		These should be printed one name per line, in the order they occurred within the input.
		
		//input:
		//A    U    R     V
//		3500.0 iu 5000.0 Vitamin A
//		60.0 mg 60.0 Vitamin C
//		0.15 g 25.0 Fiber
//		109. mg 990. Phosphorus
//		0.0 mg 1000.0 Calcium
//		25.0 mg 20.0 Niacin
//		-1.0 x 0.0 x
		
		//output:
		//V         A    U   computed
//		Vitamin A 3500.0 iu 70%
//		Vitamin C 60.0 mg 100%
//		Phosphorus 109.0 mg 11%
//		Niacin 25.0 mg 125%
//		Provides no significant amount of:
//		Fiber
//		Calcium
		
		while(in.hasNextLine()) {
			String nextLine = in.nextLine();
			Scanner line = new Scanner(nextLine);
			
			//get A, U, and R
			float A = line.nextFloat();
			String U = line.next();
			float R = line.nextFloat();
			
			//get V (will be the rest of the line)
			String V = "";
			while(line.hasNext()) {
				V += line.nextLine();
			}
			
			//remove extra whitespace at the beginning of V if it exists
			if(V.charAt(0) == ' ') {
				V = V.substring(1);
			}
			
			//get the percentage of the daily minimum requirement for the current vitamin
			//multiply by 10 to get percentage
			float percentageOfMinReq = (A / R) * 100;
			
			//output
			System.out.print(V + " ");
			System.out.print(A + " ");
			System.out.print(U + " ");
			System.out.print((int)percentageOfMinReq + "%"); //cast to int to prevent decimal places (zero digit precision)
		}
	}
}

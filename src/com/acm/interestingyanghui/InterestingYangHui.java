package com.acm.interestingyanghui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterestingYangHui {
	
	/*
	 * Sample Input:
3 4
3 48
0 0
 * 
	 */

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int caseCount = 1;
		while(true) {
			//P < 1000, N <= 10^9
			int P = in.nextInt(); // a prime number
			int N = in.nextInt(); // a positive decimal integer
			System.out.println();
			
			if(P == 0 && N == 0) {
				break;
			}
			
			//for each test case, output the last four digits of the number of elements on the N + 1 line on the triangle
			//that cannot be divided by p in the format as indicated in the sample output 
			
			//since we're zero indexed, we actually need to do just N, instead of N + 1. Line 4 is 1 3 3 1 on the problem, but for us its 1 4 6 4 1
			List<Long> line = getLineInTriangle(N);
			long count = 0;
			for(Long n : line) {
//				System.out.print(n + ", ");
				if((n % P) != 0) {
					count++;
				}
			}
			
			String output = String.format("%04d", count);
			System.out.print("Case " + caseCount + ":  " + output);
			
			caseCount++;
		}
	}
	
	public static List<Long> getLineInTriangle(int n) {
		//can use this equation to get a line in pascal's triangle:
		//C(n, k+1) = C(n,k) * (n-k) / (k+1)
		//C(n, 0) = 1
		
		List<Long> lineInTriangle = new ArrayList<Long>();
		lineInTriangle.add(0, 1L);
		
		for(int k = 0; k < n; k++) {
			lineInTriangle.add((lineInTriangle.get(k) * (n - k) / (k + 1)));
		}
		
		return lineInTriangle;
	}
}

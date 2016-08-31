package com.acm.foldnewspaper;

import java.util.Scanner;

public class NewsPaperProblem {

	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		
		//get # of pages, missing pages
		int N = in.nextInt();
		int P = in.nextInt();
		
		//left side, right side
		//odd, even, odd, even
		
		//N = number of pages (12)
		//P = page number (2)
		// even or odd?
		if( P <= N/2) {
			//left side
			if(P%2 == 1) {
				//odd page
				System.out.println((P+1) + " " + (N-P) + " " + (N+1-P));
			} else {
				//even page
				System.out.println((P-1) + " " + (N-P+1) + " " + (N-P+2));
			}
		} else {
			//right side
			if(P%2 == 1) {
				//odd page
				System.out.println((N-P) + " " + (N-P+1) + " " + (P+1));
			} else {
				//even page
				System.out.println((N-P) + " " + (N-P+1) + " " + (P-1));
			}
		}
		
	}
}

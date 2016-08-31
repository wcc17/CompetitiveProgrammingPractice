package com.acm.newsarticle;

import java.util.HashMap;
import java.util.Scanner;

public class NewspaperArticle {
	
	/**
	 * Example input:
	 * 
	    1
		7
		a 3
		W 10
		A 100
		, 10
		k 7
		. 3
		I 13
		7
		ACM International Collegiate Programming Contest (abbreviated
		as ACM-ICPC or just ICPC) is an annual multi-tiered competition
		among the universities of the world. The ICPC challenges students
		to set ever higher standards of excellence for themselves
		through competition that rewards team work, problem analysis,
		and rapid software development.
		From Wikipedia.
		
	 */
	
	public static void main(String[] args)  {
		HashMap<Character, Integer> prices = new HashMap<Character, Integer>();
		Scanner in = new Scanner(System.in);
		
		//get # of tests
		int testCount = in.nextInt();
		for(int i = 0; i < testCount; i++) {
			int paidCount = in.nextInt();
			
			for(int j = 0; j < paidCount; j++) {
				in.nextLine();
				char c = in.findInLine(".").charAt(0);
				
				int price = in.nextInt();
						
				prices.put(c, price);
				System.err.println("Char: " + c + " price: " + price);
			}
			
			int lineCount = in.nextInt();
			in.nextLine();
			int amount = 0;
			for(int j = 0;j < lineCount; j++) {
				String curLine = in.nextLine();
				
				for(int cur = 0; cur < curLine.length(); cur++) {
					char c = curLine.charAt(cur);
					if(prices.containsKey(c)) {
						amount += prices.get(c);
					}
				}
			}
			
			System.out.println(amount);
			float a = (float)amount / 100;
//			System.out.format(format, args)(a);
		}
	}
}

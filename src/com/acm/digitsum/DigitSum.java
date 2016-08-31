package com.acm.digitsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DigitSum {
	
	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		
		while(true) {
			int count = in.nextInt();
			
			List<Integer> digits = new ArrayList<Integer>();
			for(int i = 0; i < count; i++) {
				digits.add(in.nextInt());
			}
			
			Collections.sort(digits);
			
			int num1 = 0;
			int num2 = 0;
			
			// find first digits for the numbers
			for (int i = 0; i < digits.size(); i++) {
				if(digits.get(i) != 0) {
					num1 = digits.get(i);
					num2 = digits.get(i+1);
					digits.remove(i+1);
					digits.remove(i);
					break;
				}
			}
			
			while(digits.size() > 0) {
				num1 = num1 * 10 + digits.get(0);
				digits.remove(0);
				
				//is there a second digit available?
				if (digits.size() > 0) {
					num2 = num2 * 10 + digits.get(0);
					digits.remove(0);
				}
			}
			
			System.out.println("num1: " + num1);
			System.out.println("num2: " + num2);
			System.out.println("sum: " + (num1+num2));
		}
	}
}

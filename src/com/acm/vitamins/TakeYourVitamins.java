package com.acm.vitamins;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TakeYourVitamins {
	
	/**
	 *  Input:
		3500.0 iu 5000.0 Vitamin A
		400.0 mg 1.0 Cholesterol
		60.0 mg 60.0 Vitamin C
		0.15 g 25.0 Fiber
		0.1 g 25.0 Inert Fiber
		109. mg 990. Phosphorus
		0.0 mg 1000.0 Calcium
		25.0 mg 20.0 Niacin
		25.0 mcg 81.0 Vitamin K
		0.0 mg 4000.0 Potassium
		1.0001 g 100.0 the secret of eternal youth
		0.99 g 100.0 the secret of eternal happiness
		-1.0 x 0.0 x
		
		Output:
		Vitamin A 3500.0 iu 70%
		Cholesterol 400.0 mg 40000%
		Vitamin C 60.0 mg 100%
		Phosphorus 109.0 mg 11%
		Niacin 25.0 mg 125%
		Vitamin K 25.0 mcg 31%
		the secret of eternal youth 1.0 g 1%
		Provides no significant amount of:
		Fiber
		Inert Fiber
		Calcium
		Potassium
	 * @param args
	 */
	
	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		
		float A = 0;
		float R;
		String U;
		String V;
		List<String> noSignificantAmount = new ArrayList<String>();
		List<String> lines = new ArrayList<String>();
		
		//get all lines of input
		while(true) {
			String line = in.nextLine();
			
			//if line is not blank
			if(line.length() > 0) {
				//if a is not negative
				if(line.charAt(0) != '-') {
					lines.add(line);
					continue;
				}
			}
			break;
		}
		
		for(String line : lines) {
			Scanner lineScanner = new Scanner(line);
			
			//get A, U, and R
			A = lineScanner.nextFloat();
			U = lineScanner.next();
			R = lineScanner.nextFloat();
			
			//get V (will be the rest of the line)
			V = "";
			while(lineScanner.hasNext()) {
				V += lineScanner.nextLine();
			}
			
			//remove extra whitespace at the beginning of V if it exists
			if(V.charAt(0) == ' ') {
				V = V.substring(1);
			}
			
			//get the percentage of the daily minimum requirement for the current vitamin
			//multiply by 10 to get percentage
			float decimalPercentage = (A / R);
			
			//round percentage to nearest percentage point
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.CEILING);
			String percentageString = df.format(decimalPercentage*100);
			
			if(Float.valueOf(percentageString) < 1) {
				noSignificantAmount.add(V);
			} else {
				//output
				System.out.print(V + " ");
				System.out.printf("%.1f ", A);
				System.out.print(U + " ");
				System.out.printf("%.0f", Float.valueOf(percentageString));
				System.out.print("%");
				System.out.println();
			}
			
			lineScanner.close();
		}
		
		System.out.println("Provides no significant amount of:");
		for(String s : noSignificantAmount) {
			System.out.println(s);
		}
		
		in.close();
	}
}

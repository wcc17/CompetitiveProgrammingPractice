package com.acm.cuttingsticks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CuttingSticks {

	/**
	 * Sample Input:
100
3
25 50 75
10
4
4 5 7 8
0
	 */
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		
		while(true) {
			int length = in.nextInt();
			if(length == 0) {
				break;
			}
			
			int numCuts = in.nextInt();
			List<Integer> cutsToMake = new ArrayList<Integer>();
			
			for(int i = 0; i < numCuts; i++) {
				cutsToMake.add(in.nextInt());
			}
			
			determineMinimumCost(length, numCuts, cutsToMake);
		}
		
		in.close();
	}
	
	public static void determineMinimumCost(int length, int numCuts, List<Integer> cutsToMake) {
		List<Integer> cutOrder = Arrays.asList(new Integer[numCuts]);
		
		for(int i = 0; i < numCuts; i++) {
			List<Integer> costs = new ArrayList<Integer>();
			for(int j = 0; j < cutsToMake.size(); j++) {
				int lowerBound;
				int upperBound;
				
				if(j == 0) {
					lowerBound = 0;
				} else {
					lowerBound = cutsToMake.get(j-1);
				}
				
				if(j >= (cutsToMake.size() - 1)) {
					upperBound = length;
				} else {
					upperBound = cutsToMake.get(j+1);
				}
				
				int costOfCurrentCut = upperBound - lowerBound;
				costs.add(j, costOfCurrentCut);
			}
			
			//always want to get the largest cut with the smallest cost
			int lowestCost = 9999999;
			int lowestCostIndex = -1;
			for(int j = costs.size()-1; j >= 0; j--) {
				if(costs.get(j) < lowestCost) {
					
					if(!cutOrder.contains(cutsToMake.get(j))) {
						lowestCost = costs.get(j);
						lowestCostIndex = j;
					}
					
					
				}
			}
			
			cutOrder.set((numCuts-1) - i, cutsToMake.get(lowestCostIndex));
			cutsToMake.remove(lowestCostIndex);
		}
		
		System.out.println(determineCost(cutOrder, length));
	}
	
	public static int determineCost(List<Integer> cuts, int length) {
		int cost = 0;
		List<Integer> cutsMade = new ArrayList<Integer>();
		
		//have an initial length
		for(int i = 0; i < cuts.size(); i++) {
			int lowerBound = 0;
			int upperBound = length;
			
			//go through cuts already made and see where the current cut falls in between them
			for(int j = 0; j < cutsMade.size(); j++) {
				if(cutsMade.get(j) < cuts.get(i)) {
					int newLowerBound = cutsMade.get(j);
					if(newLowerBound > lowerBound) {
						lowerBound = newLowerBound;
					}
				} else if(cutsMade.get(j) > cuts.get(i)) {
					int newUpperBound = cutsMade.get(j);
					if(newUpperBound < upperBound) {
						upperBound = newUpperBound;
					}
				}
			}
			
			cost += (upperBound - lowerBound);
			cutsMade.add(cuts.get(i));
		}
		
		return cost;
	}
}

package com.acm.takeland;

import java.util.Scanner;

public class TakeTheLand {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			int numRow = in.nextInt();
			int numCol = in.nextInt();
			
			//end of data?
			if (numRow == 0 || numCol == 0) {
				return;
			}
			
			//read the tree data
			int[][] trees = new int[numRow][numCol];
			
			for(int row = 0; row < numRow; row++) {
				for(int col = 0; col < numCol; col++) {
					trees[row][col] = in.nextInt();
				}
			}
			
			//find how clear it is to the right for each spot
			int[][] clearRight = new int[numRow][numCol];
			for(int row=0; row<numRow; row++) {
				int clearCount = 0; 
				for(int col = numCol - 1; col >= 0; col--) {
					if(trees[row][col] == 1) {
						//tree here
						clearCount = 0;
					} else {
						//no tree here
						clearCount++;
					}
					
					clearRight[row][col] = clearCount;
				}
			}
			
			//find how clear it is down for each spot
			int[][] clearDown = new int[numRow][numCol];
			for(int col=0; col<numCol; col++) {
				int clearCount = 0; 
				for(int row = numRow - 1; row >= 0; row--) {
					if(trees[row][col] == 1) {
						//tree here
						clearCount = 0;
					} else {
						//no tree here
						clearCount++;
					}
					
					clearDown[row][col] = clearCount;
				}
			}
			
			//look for big plots of land
			int maxSize = 0;
			for(int col = 0; col < numCol; col++) {
				//scan this column
				for(int topRow = 0; topRow < numRow; topRow++) {
					//not possible to be bigger
					if(maxSize > clearRight[topRow][col]*clearDown[topRow][col]) {
						continue;
					}
					
					int maxWidth = clearRight[topRow][col];
					maxSize = Math.max(maxSize,  maxWidth);
					
					//continue down
				}
			}
		}
		
//		in.close();
	}
}

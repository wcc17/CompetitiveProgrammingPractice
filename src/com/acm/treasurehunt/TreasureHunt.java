package com.acm.treasurehunt;

import java.util.ArrayList;
import java.util.Scanner;

public class TreasureHunt {
	
	static class Treasure {
		int row;
		int col;
		int pickupCost;
		int carryCost;
		int[][] distances;
	}
	
	static class Square {
		int row;
		int col;
		
		Square (int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	//calculate the distance from a given treasure to everywhere else
	public static int[][] calcDistances(Treasure start, int numRow, int numCol, char[][] maze) {
		int distances[][] = new int[numRow][numCol];
		
		for(int row = 0; row < numRow; row++) {
			for(int col = 0; col < numCol; col++) {
				distances[row][col] = -1;
			}
		}
		
		//start has cost 0
		distances[start.row][start.col] = 0;
		
		ArrayList<Square> queue = new ArrayList<Square>();
		queue.add(new Square(start.row, start.col));
		
		while(queue.size() > 0) {
			//get the current location
			Square s = queue.remove(0);
			
			//how far did we go to arrive here
			int curdist = distances[s.row][s.col];
			
			//try left
			if(s.col > 0) {
				//have a square to the left
				if(maze[s.row][s.col-1] != '#' && distances[s.row][s.col-1] == -1) {
					int leftDist = curdist + 1;
					
					//update distance table
					distances[s.row][s.col-1] = leftDist;
					
					queue.add(new Square(s.row, s.col-1));
				}
			}
			
			//try right
			if(s.col + 1 < numCol) {
				//have a square to the right
				if(maze[s.row][s.col+1] != '#' && distances[s.row][s.col+1] == -1) {
					int rightDist = curdist + 1;
					
					//update distance table
					distances[s.row][s.col+1] = rightDist;
					
					queue.add(new Square(s.row, s.col+1));
				}
			}
			
			// try up
			if (s.row > 0) {
				//have a square up one
				if (maze[s.row-1][s.col] != '#' && distances[s.row-1][s.col] == -1) {
					int upDist = curdist + 1;
					
					//update distance table
					distances[s.row-1][s.col] = upDist;
					
					queue.add(new Square(s.row-1, s.col));
				}
			}
			
			// try down
			if (s.row + 1 < numRow) {
				//have a square down one
				if (maze[s.row+1][s.col] != '#' && distances[s.row+1][s.col] == -1) {
					int downDist = curdist + 1;
					
					//update distance table
					distances[s.row+1][s.col] = downDist;
					
					queue.add(new Square(s.row+1, s.col));
				}
			}
		}
		
		return distances;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while(true) {
			Treasure startTreasure = new Treasure();
			int numRow = in.nextInt();
			int numCol = in.nextInt();
			int endRow = 0;
			int endCol = 0;
			if(numRow == 0) {
				return;
			}
			in.nextLine();
			
			char[][] maze = new char[numRow][numCol];
			
			ArrayList<Treasure> treasures = new ArrayList<Treasure>();
			
			//get the maze
			for (int row = 0; row <numRow; row++) {
				String rowInfo = in.nextLine();
				for (int col = 0; col < numCol; col++) {
					char info = rowInfo.charAt(col);
					maze[row][col] = info;
					
					if(info == '*') {
						//treasure here
						Treasure t = new Treasure();
						t.row = row;
						t.col = col;
						treasures.add(t);
						
					} else if(info == 'S') {
						//start location
						//treasure here
						startTreasure.row = row;
						startTreasure.col = col;
					} else if(info == 'T') {
						//end location
						endRow = row;
						endCol = col;
					}
				}
			}
			
			//get the cost to walk one space
			int walkCost = in.nextInt();
			
			//get pickup and carrying cost for each treasure
			for(int num = 0; num < treasures.size(); num++) {
				int pickupCost = in.nextInt();
				int carryCost = in.nextInt();
				
				Treasure t= treasures.get(num);
				t.carryCost = carryCost;
				t.pickupCost = pickupCost;
				t.distances = calcDistances(t, numRow, numCol, maze);
			}
			
			startTreasure.distances = calcDistances(startTreasure, numRow, numCol, maze);
			
			//any treasures not reachable?
			for(Treasure t : treasures) {
				if(startTreasure.distances[t.row][t.col] == -1) {
					System.out.println("The hunt is impossible.");
					continue;
				}
			}
			
			//end reachable?
			if (startTreasure.distances[endRow][endCol] == -1) {
				System.out.println("This hunt is impossible.");
				continue;
			}
		}
	}
}

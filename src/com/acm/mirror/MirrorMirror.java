package com.acm.mirror;

import java.util.Scanner;

public class MirrorMirror {
	
	static int size;
	
	public static char[][] rotate90(char[][] array) {
		char[][] rotArray = new char[size][size];
		
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				rotArray[col][size-1-row] = array[row][col];
			}
		}
		
		return rotArray;
	}
	
	public static char[][] verticalReflect(char[][] array) {
		char[][] reflectArray = new char[size][size];
		
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				reflectArray[size - row - 1][col] = array[row][col];
			}
		}
		
		return reflectArray;
	}
	
	public static boolean check(char[][] a1, char[][] a2) {
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				if(a1[row][col] != a2[row][col]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		size = in.nextInt();
		in.nextLine();
		
		char[][] start = new char[size][size];
		char[][] end = new char[size][size];
		
		for(int i = 0 ; i < size; i++) {
			String nextLine = in.nextLine();
			
			for(int j = 0; j < size; j++) {
				start[i][j] = nextLine.charAt(j);
				end[i][j] = nextLine.charAt(size+1+j);
			}
		}
		
		//rotate 90
		char[][] rot90 = rotate90(start);
		
		//rotate 180
		char[][] rot180 = rotate90(rot90);
		
		//rotate 270
		char[][] rot270 = rotate90(rot180);
		
		//vertical reflect
		char[][] vertRef = verticalReflect(start);
		
		if((check(start, end))) {
			System.out.println("Pattern was preserved");
		}
		if(check(end, rot90)) {
			System.out.println("Pattern was rotated 90 degrees");
		}
		if(check(end, rot180)) {
			System.out.println("Patten was rotated 180 degrees");
		}
		if(check(end, rot270)) {
			System.out.println("Pattern was rotated 270 degrees");
		}
		if(check(end, vertRef)) {
			System.out.println("Pattern was vertically reflected");
		}
	}

}

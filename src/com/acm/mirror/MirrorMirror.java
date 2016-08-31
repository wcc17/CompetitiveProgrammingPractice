package com.acm.mirror;

import java.util.Scanner;

public class MirrorMirror {
	
	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
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
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				System.out.print(start[i][j]);
			}
			System.out.println();
		}
		
		System.out.println();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				System.out.print(end[i][j]);
			}
			System.out.println();
		}
	}

}

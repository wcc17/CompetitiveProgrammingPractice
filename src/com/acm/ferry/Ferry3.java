package com.acm.ferry;

import java.util.LinkedList;
import java.util.Scanner;

public class Ferry3 {
	
	/**
	 * Sample Input
		2
		2 10 10
		0 left
		10 left
		20 left
		30 left
		40 left
		50 left
		60 left
		70 left
		80 left
		90 left
		2 10 3
		10 right
		25 left
		40 left
		
		Sample Output
		10
		30
		30
		50
		50
		70
		70
		90
		90
		110
		30
		40
		60
	 */
	
	class Car {
		int arriveTime;
		int departTime;
		
		public Car(int arriveTime) {
			this.arriveTime = arriveTime;
		}
	}
	
	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		
		int ferrySize = in.nextInt();
		int crossTime = in.nextInt();
		int carCount = in.nextInt();
		
		int curTime = 0;
		boolean leftBank = true;
		
		LinkedList<Car> cars = new LinkedList<Car>();
		LinkedList<Car> leftQueue = new LinkedList<Car>();
		LinkedList<Car> rightQueue = new LinkedList<Car>();
		
		for(int carIndex = 0; carIndex < carCount; carIndex++) {
			//get info for one car
			int arriveTime = in.nextInt();
			
			//which side
			String side = in.next();
			
			if(("left").equals(side)) {
				leftQueue.add(new Car(arriveTime));
			} else if(("right").equals(side)) {
				rightQueue.add(new Car(arriveTime));
			}
		}
		
		//simulate the ferry
		//starting on the left bank at time 0
		while(!leftQueue.isEmpty() || !rightQueue.isEmpty()) {
			int ferryCarCount = 0;
			
			while( !leftQueue.isEmpty() && leftQueue.peekFirst() <= curTime) {
				ferryCarCount += 1;
				
				Car c= leftQueue.getFirst();
				c.departTime = curTime + crossTime;
			}
		}
		
		while(cars.size() >= 0) {
			Car c = cars.getFirst();
			System.out.println(c.departTime);
		}
	}

}

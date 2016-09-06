	
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acm.ferry;

import java.util.Scanner;
import java.util.LinkedList;

public class Ferry3 {
    // car object
    static class Car {
        public Car( int t ) { arriveTime = t; }
        int arriveTime;
        int departTime;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner( System.in );
        
        int ferrySize = in.nextInt();
        int crossTime = in.nextInt();
        int carCount = in.nextInt();
        
        // set intial ferry state
        int curTime = 0;
        boolean leftBank = true;
        
        // create queues
        LinkedList<Car> allCars = new LinkedList<>();
        LinkedList<Car> leftQueue = new LinkedList<>();
        LinkedList<Car> rightQueue = new LinkedList<>();
        
        
        for( int carIndex=0; carIndex<carCount; carIndex++ ) {
            // get info for one car
            // arrival time
            int arriveTime = in.nextInt();
            // which side?
            String side = in.next();
            
            // create teh car object
            Car newcar = new Car( arriveTime );
            allCars.addLast( newcar );
            
            // add to the appropriate queue
            if ( side.equals("left"))
                leftQueue.addLast( newcar );
            else
                rightQueue.addLast( newcar );
        }
        
        // simulate the ferry
        // starting on the left bank at time 0
        while( leftQueue.size()>0 || rightQueue.size() > 0 ) {
        	curTime = waitNextCar(curTime, leftQueue, rightQueue);
        	int ferryCarCount = 0;
        	
            // get cars from the left side and load the ferry
            while( leftQueue.size() > 0 &&
                    leftQueue.peekFirst().arriveTime<=curTime) {
                // put it on the ferry
                ferryCarCount++;
                // remove it from the queue
                Car c = leftQueue.removeFirst();
                c.departTime = curTime + crossTime;
            }
            curTime += crossTime;
            
            //get cars from the right side and laod the ferry
            while( rightQueue.size() > 0 &&
                    rightQueue.peekFirst().arriveTime<=curTime) {
                // put it on the ferry
                ferryCarCount++;
                // remove it from the queue
                Car c = rightQueue.removeFirst();
                c.departTime = curTime + crossTime;
            }
            curTime += crossTime;
            
        }
        
        // print results
        while( allCars.size() > 0 ) {
            Car c = allCars.removeFirst();
            System.out.println(c.departTime);
        }
    }
    
    //sit until a car arriecs
    protected static int waitNextCar(int curTime, LinkedList<Car> leftQueue, LinkedList<Car> rightQueue) {
    	int nextLeftTime = 9999999;
    	int nextRightTime = 9999999;
    	
    	if(leftQueue.size() > 0) {
    		nextLeftTime = leftQueue.peekFirst().arriveTime;
    	}
    	
    	if(rightQueue.size() > 0) {
    		nextRightTime = rightQueue.peekFirst().arriveTime;
    	}
    	
    	int nextCarTime = Math.min(nextLeftTime,nextRightTime);
    	
    	if(nextCarTime > curTime) {
    		curTime = nextCarTime;
    	}
    	
    	return curTime;
    }
    
}
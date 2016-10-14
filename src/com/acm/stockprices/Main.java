package com.acm.stockprices;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	public static final int BUY = 0;
	public static final int SELL = 1;
	
	static class Order {
		int type;
		int numberShares;
		int price;
	}
	
	//with a bid, we're looking for the highest buy price to sell to
	static class BidComparator implements Comparator<Order> {
		public int compare(Order one, Order two) {
			return two.price - one.price;
		}
	}
	
	//with an ask, we're looking for the lowest ask price to buy from
	static class AskComparator implements Comparator<Order> {
		public int compare(Order one, Order two) {
			return one.price - two.price;
		}
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Order>> testCases = getInput();
		
		//for each test case..
		for(ArrayList<Order> orders : testCases) {
			
			PriorityQueue<Order> bidQueue = new PriorityQueue<Order>(10, new BidComparator());
			PriorityQueue<Order> askQueue = new PriorityQueue<Order>(10, new AskComparator()); 
			
			//for each order in the test case
			int bidPrice = -1;
			int askPrice = -1;
			int stockPrice = -1;
			for(Order order : orders ){
				if(order.type == BUY) {
					bidQueue.offer(order);
				} else if(order.type == SELL) {
					askQueue.offer(order);
				}
				
				//while at least one queue has something in it, see if we can make any sales with the new order in the queues
				while(!bidQueue.isEmpty() && !askQueue.isEmpty()) {
					//if there are no buys left for the asks that we have, try the next order
					if(bidQueue.peek().price < askQueue.peek().price) {
						break;
					}
					
					//get the best bid and the best ask
					Order bid = bidQueue.poll();
					Order ask = askQueue.poll();
					
					if(bid == null || ask == null) {
						break;
					} else {
						stockPrice = ask.price;
						
						//get the one with the most shares and execute the sale
						if(bid.numberShares > ask.numberShares) {
							//ask is done
							bid.numberShares -= ask.numberShares;
							bidQueue.offer(bid);
						} else if(bid.numberShares < ask.numberShares) {
							//bid is done
							ask.numberShares -= bid.numberShares;
							askQueue.offer(ask);
						} else {
							//both are done, don't add either back to respective queues
						}
					}
				}
				
				//get the current best buy and ask prices
				bidPrice = (bidQueue.peek() != null) ? bidQueue.peek().price : -1;
				askPrice = (askQueue.peek() != null) ? askQueue.peek().price : -1;
				
				String ap = (askPrice == -1) ? "-" : String.valueOf(askPrice);
				String bp = (bidPrice == -1) ? "-" : String.valueOf(bidPrice);
				String sp = (stockPrice == -1) ? "-" : String.valueOf(stockPrice);
				System.out.println(ap + " " + bp + " " + sp);
			}
		}
	}
	
	//hiding this in a method, its not that important
	static ArrayList<ArrayList<Order>> getInput() {
		Scanner in = new Scanner(System.in);
		
		int numTestCases;
		int numOrders;
		
		numTestCases = in.nextInt();
		in.nextLine();
		
		ArrayList<ArrayList<Order>> testCases = new ArrayList<ArrayList<Order>>();
		for(int i = 0; i < numTestCases; i++) {
			numOrders = in.nextInt();
			in.nextLine();
			
			ArrayList<Order> orders = new ArrayList<Order>();
			for(int j = 0; j < numOrders; j++) {
				String line = in.nextLine();
				//make a scanner for the current line
				Scanner lineIn = new Scanner(line);
				
				Order order =  new Order();
				
				//get the order type
				String orderType = lineIn.next();
				if(("buy").equals(orderType)) {
					order.type = BUY;
				} else if(("sell").equals(orderType)) {
					order.type = SELL;
				}
				
				//get the number of shares
				order.numberShares = lineIn.nextInt();
				
				//throw out the words "shares" and "at"
				lineIn.next();
				lineIn.next();
				
				//get the share prices
				order.price = lineIn.nextInt();
				
				orders.add(order);
				lineIn.close();
			}
			
			testCases.add(orders);
		}
		
		in.close();
		
		return testCases;
	}
}

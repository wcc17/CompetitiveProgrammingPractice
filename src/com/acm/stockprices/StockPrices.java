package com.acm.stockprices;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class StockPrices {
	
	public static final int BUY = 0;
	public static final int SELL = 1;
	
	public static class Order {
		int orderType;
		int numberShares;
		int price;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int numTestCases;
		int numOrders;
		
		numTestCases = in.nextInt();
		in.nextLine();
		
		for(int i = 0; i < numTestCases; i++) {
			numOrders = in.nextInt();
			in.nextLine();
			
			List<Order> orders = new ArrayList<Order>();
			for(int j = 0; j < numOrders; j++) {
				String line = in.nextLine();
				
				Order order =  new Order();
				
				//make a scanner for the current line
				Scanner lineIn = new Scanner(line);
				
				//get the order type
				String orderType = lineIn.next();
				if(("buy").equals(orderType)) {
					order.orderType = BUY;
				} else if(("sell").equals(orderType)) {
					order.orderType = SELL;
				}
				
				//get the number of shares
				order.numberShares = Integer.parseInt(lineIn.next());
				
				//throw out the words "shares" and "at"
				lineIn.next();
				lineIn.next();
				
				//get the share prices
				order.price = Integer.parseInt(lineIn.next());
				
				orders.add(order);
				lineIn.close();
			}
			
			//now determine output here:
			int askPrice = -1; 
			int bidPrice  = -1;
			int stockPrice = -1;
			
			List<Order> bids = new ArrayList<Order>();
			List<Order> sales = new ArrayList<Order>();
			for(Order order : orders) {
				if(order.orderType == BUY) {
					if(order.price > bidPrice) {
						bidPrice = order.price;
						bids.add(order);
					}
					
					Order bestSale = null;
					for(Order sale : sales) {
						
					}
					
				} else if(order.orderType == SELL) {
					if(order.price < askPrice || askPrice == -1) {
						askPrice = order.price;
						sales.add(order);
					}
					
					if(!bids.isEmpty()) {
						//look to see if someone is buying that this sale price satisfies
						//otherwise, put this order in the sales list
					}
				}
				
				String ask = "-";
				String bid = "-";
				String stock = "-";
				if(askPrice != -1) { ask = String.valueOf(askPrice); }
				if(bidPrice != -1) { bid = String.valueOf(bidPrice); }
				if(stockPrice != -1) { stock = String.valueOf(stockPrice); }
				System.out.println(ask + " " + bid + " " + stock);
			}
			
		}
		
	}

}

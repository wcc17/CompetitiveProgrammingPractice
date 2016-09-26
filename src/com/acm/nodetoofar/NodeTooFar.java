package com.acm.nodetoofar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Node {
	public int id;
	public int minDist;
	public ArrayList<Node> neighbors;
	public boolean seen = false;
	
	Node(int newId) {
		id = newId;
		neighbors = new ArrayList<Node>();
	}
}

public class NodeTooFar {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//map of all nodes
		HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
			
		//get # of edges
		int linkCount = in.nextInt();
		
		//get edges
		for(int i = 0; i < linkCount; i++) {
			int fromId = in.nextInt();
			int toId = in.nextInt();
			Node fromNode;
			Node toNode;
			
			if(nodes.containsKey(fromId)) {
				fromNode = nodes.get(fromId);
			} else {
				fromNode = new Node(fromId);
				nodes.put(fromId, fromNode);
			}
			
			if(nodes.containsKey(toId)) {
				toNode = nodes.get(toId);
			} else {
				toNode = new Node(toId);
				nodes.put(toId, toNode);
			}
			
			//link the nodes
			fromNode.neighbors.add(toNode);
			toNode.neighbors.add(fromNode);
		}
		
		//get queries
		while(true) {
			int startId = in.nextInt();
			int hopCount = in.nextInt();
			
			//end of queries?
			if(startId == 0 && hopCount == 0) {
				break;
			}
			
			//clear distances before starting search
			for(Node node : nodes.values()) {
				node.minDist = -1;
				node.seen = false;
			}
			
			//start breadth first seafch starting at the specified node
			Node startNode = nodes.get(startId);
			startNode.minDist = 0;
			ArrayList<Node> toVisit = new ArrayList<Node>();
			
			toVisit.add(startNode);
			
			//visit one node and process its neighbors
			while(toVisit.size() > 0) {
				//get the first node on the list
				Node curNode = toVisit.remove(0);
				
				if (curNode.minDist >= hopCount) {
					continue;
				}
				
				for(Node neighbor : curNode.neighbors) {
					
					if(neighbor.seen) {
						continue;
					}
						
					neighbor.minDist = curNode.minDist+1;
					toVisit.add(neighbor);
					neighbor.seen = true;
				}
			}
			
			//print nodes we haven't seen
			
		}
	}
}

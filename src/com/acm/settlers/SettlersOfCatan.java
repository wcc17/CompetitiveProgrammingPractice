package com.acm.settlers;

import java.util.Scanner;
import java.util.ArrayList;

/**
3 2 
0 1 
1 2 
15 16 
0 2 
1 2 
2 3 
3 4 
3 5 
4 6 
5 7 
6 8 
7 8 
7 9 
8 10 
9 11 
10 12 
11 12 
10 13 
12 14 
0 0
* @author wcc17
*
*/

class Node {
    int id;
    ArrayList<Edge> links;
    
    Node( int n ) {
        id = n;
        links = new ArrayList<Edge>();
    }
}

class Edge {
    Node node1;
    Node node2;
    boolean used;
}

public class SettlersOfCatan {
    // recursively find longest road starting at the given node
    public static int findLongest( Node curNode, Edge[] allEdges, 
               int curLen ) {
        int nodeMax = curLen;        // longest road from here
        for( Edge e: curNode.links ) {
            // have we already travelled this link?
            if ( e.used )
                continue;
            
            Node neighbor;
            // find the other end of the link
            if ( e.node1 == curNode )
                neighbor = e.node2;
            else
                neighbor = e.node1;
            
            e.used = true;
            int linkMax = findLongest( neighbor, allEdges, curLen+1 );
            e.used = false;
            nodeMax = Math.max( nodeMax, linkMax );
        }
        return nodeMax;
    }
            
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner( System.in );
        
        while( true ) {
            // get the # of nodes and edges
            int nodeCount = in.nextInt();
            int edgeCount = in.nextInt();
            
            // end of data?
            if ( nodeCount == 0 || edgeCount == 0 )
                return;
            
            // create the nodes
            Node[] allNodes = new Node[nodeCount];
            for( int n=0; n<nodeCount; n++ ) {
                Node curNode = new Node(n);
                allNodes[n] = curNode;
            }

            // read edge data
            Edge[] allEdges = new Edge[edgeCount];
            for( int e=0; e<edgeCount; e++ ) {
                int n1 = in.nextInt();
                int n2 = in.nextInt();
                Edge curEdge = new Edge();
                curEdge.node1 = allNodes[n1];
                curEdge.node2 = allNodes[n2];
                allEdges[e] = curEdge;
                // add link to each node
                curEdge.node1.links.add(curEdge);
                curEdge.node2.links.add(curEdge);
            }
            
            // consider roads starting at each node
           int longest = 0;
            for( Node curNode: allNodes ) {
                // mark all edges as not used
                for( Edge e: allEdges )
                    e.used = false;
                
                // find the longest road starting at this node
                int longRoad = findLongest( curNode, allEdges, 0 );
                longest = Math.max( longest, longRoad );
            }
            System.out.println(longest);
        }
    }
    
}
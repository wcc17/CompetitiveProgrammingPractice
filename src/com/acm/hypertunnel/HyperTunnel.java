package com.acm.hypertunnel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HyperTunnel {
	
	static class StarSystem {
		int id;
		List<StarSystem> connectedTo = new ArrayList<StarSystem>();
		
		public StarSystem(int id) {
			this.id = id;
		}
	}
	
	static class Tunnel {
		int startSystem;
		int endSystem;
		int minDaysToDestination; //how many days will it take to get from this star system to the destination
		
		public Tunnel(int startSystem, int endSystem) {
			this.startSystem = startSystem;
			this.endSystem = endSystem;
		}
	}
	
	static class Spaceship {
		public int currentStarSystem;
		public List<Tunnel> tunnelsUsed = new ArrayList<Tunnel>();
		public boolean destinationReached = false;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int numStarSystems = in.nextInt();				//N - number of star systems (2 <= N <= 50)
		int numTunnels = in.nextInt();					//M - the number of tunnels	(1 <= M <= 200)
		int numSuperComputers = in.nextInt();			//K - the number of supercomputers to be delivered (1 <= K <= 50)
		int earthSolarSystemNumber = in.nextInt(); 		//S - the number of the solar system where earth is (1 <= S, T <= N, S != T)
		int eisiemSolarSystemNumber = in.nextInt();		//T	- the number of the solar system where eisiem is
		
		//next M lines (numTunnels) contain two different integer numbers each and describe tunnels
		//for each tunnel the number of star systems it connects are given
		//the tunnel can be traveled in both directions, but only one ship can travel through it each day (one direction)
		//no tunnel connects a star to itself and any two stars are connected by at most one tunnel
		List<Tunnel> tunnels = new ArrayList<Tunnel>();
		for(int i = 0; i < numTunnels; i++) {
			in.nextLine();
			
			int starSystem1 = in.nextInt();
			int starSystem2 = in.nextInt();
			
			tunnels.add(new Tunnel(starSystem1, starSystem2));
		}
		
//		System.err.print(numStarSystems + " ");
//		System.err.print(numTunnels + " ");
//		System.err.print(numSuperComputers + " ");
//		System.err.print(earthSolarSystemNumber + " ");
//		System.err.print(eisiemSolarSystemNumber + " ");
//		System.err.println();
//		for(Tunnel tunnel : tunnels) {
//			System.err.println(tunnel.starSystem1 + " " + tunnel.starSystem2);
//		}
		
		//on the first line of the output for each dataset print L - the fewest number of days needed to deliver K supercomputers from S to T using the tunnels
		//next L lines must describe the process
		//each line must start with C, the number of ships that travel from one system to another this day
		//C pairs of integer numbers must follow, pair A, B, means that the ship number A travels from its current star system to star system B
		
		//initialize star systems 
		StarSystem[] starSystems = new StarSystem[numStarSystems];
		for(int i = 0; i < starSystems.length; i++) {
			//assign ids
			starSystems[i] = new StarSystem(i);
		}
		//get the star systems that are directly connected for each star system in the array
		starSystems = determineConnectedStarSystems(starSystems, tunnels);
		
		for(Tunnel tunnel : tunnels) {
			tunnel.minDaysToDestination = determineDaysToDestination(tunnel, tunnels, starSystems, eisiemSolarSystemNumber, 0);
		}
		System.out.println();
		
		solve(tunnels, earthSolarSystemNumber, earthSolarSystemNumber, eisiemSolarSystemNumber, 0, numSuperComputers); 
		
		//debug determineConnectedStarSystems
//		for(StarSystem starSystem : starSystems) {
//			System.err.print((starSystem.id + 1) + "   ");
//			for(StarSystem s : starSystem.connectedTo) {
//				System.err.print((s.id + 1) + " ");
//			}
//			System.err.println();
//		}
		
		List<Spaceship> spaceships = new ArrayList<Spaceship>();
		
		int days = 0;
		while(true) {
			List<Tunnel> tunnelsUsedToday = new ArrayList<Tunnel>();
			
			//need to get all the possible tunnels we could take
			//
			
			//either spawn a spaceship or move a spaceship
			for(Spaceship spaceship : spaceships) {
				//make a move
				if(!spaceship.destinationReached) {
					for(Tunnel tunnel : tunnels) {
						//find a tunnel from the current star system to the next
						if(tunnel.startSystem == spaceship.currentStarSystem) {
							if(!tunnelsUsedToday.contains(tunnel)) {
								spaceship.currentStarSystem = tunnel.endSystem;
								spaceship.tunnelsUsed.add(tunnel);
								tunnelsUsedToday.add(tunnel);
								
								break;
							}
						}
					}
					
					if(spaceship.currentStarSystem == eisiemSolarSystemNumber) {
						spaceship.destinationReached = true;
					}
				}
			}
			
			if(spaceships.size() < numSuperComputers) {
				//check if moves are left (if there are any tunnels starting at earthSolarSystemNumber), make a move
				
				for(Tunnel tunnel : tunnels) {
					if(tunnel.startSystem == earthSolarSystemNumber) {
						//if it hasn't already been used today
						if(!tunnelsUsedToday.contains(tunnel)) {
							Spaceship spaceship = new Spaceship();
							spaceship.currentStarSystem = tunnel.endSystem;
							spaceship.tunnelsUsed.add(tunnel);
							spaceships.add(spaceship);
							
							tunnelsUsedToday.add(tunnel);
						}
					}
				}
			}
			
			//see if all the spaceships have reached their destination
			boolean finished = true;
			for(Spaceship spaceship : spaceships) {
				if(!spaceship.destinationReached) {
					finished = false;
				}
			}
			
			days += 1;
			
			//if all spaceships have reached their destination, break out of the while loop
			if(finished) {
				break;
			}
		}
		
		in.close();
	}
	
	public static StarSystem[] determineConnectedStarSystems(StarSystem[] starSystems, List<Tunnel> tunnels) {
		
		for(Tunnel tunnel : tunnels) {
			//hve to -1 because of the zero-indexed array
			starSystems[tunnel.startSystem - 1].connectedTo.add(starSystems[tunnel.endSystem - 1]);
		}
		
		return starSystems;
	}
	
	//will determine the minimum amount of days it would take to use this tunnel to get to the destination
	public static int determineDaysToDestination(Tunnel tunnel, List<Tunnel> tunnels, StarSystem[] starSystems, int destination, int days) {
		
		//for each tunnel
		//get current tunnels start and end point
		//find all tunnels whose start points match the current end point
		//
		
		if(tunnel.endSystem == destination) {
			tunnel.minDaysToDestination = 1;
			return tunnel.minDaysToDestination;
		} else {
			int minDays = 999999999;
			for(Tunnel t : tunnels) {
				if(tunnel.endSystem == t.startSystem) {
					int d = 1 + determineDaysToDestination(t, tunnels, starSystems, destination, days);
					
					if(d < minDays) {
						minDays = d;
					}
				}
			}
			
			tunnel.minDaysToDestination = minDays;
		}
		
		
		return tunnel.minDaysToDestination;
	}
	
	public static int solve(List<Tunnel> tunnels, int currentSystem, int start, int destination, int numShips, int numSuperComputers) {

		//get possible moves
		//get the costs for those moves
		
		//make sure a tunnel with the same possible cost as another is not repeated
		List<Tunnel> possibleTunnels = new ArrayList<Tunnel>();
		List<Integer> possibleCosts = new ArrayList<Integer>();
		for(Tunnel tunnel : tunnels) {
			if(tunnel.startSystem == currentSystem) {
				if(!possibleCosts.contains(tunnel.minDaysToDestination) && !possibleTunnels.contains(tunnel)) {
					possibleCosts.add(tunnel.minDaysToDestination);
					possibleTunnels.add(tunnel);
				}
			}
		}
		
		
		ArrayList<ArrayList<Tunnel>> tunnelsToAttempt = new ArrayList<ArrayList<Tunnel>>();
		for(int i = 0; i < possibleTunnels.size(); i++) {
			//need to do the tunnel, then start adding other tunnels one by one
			ArrayList<Tunnel> firstTunnelAttempt = new ArrayList<Tunnel>();
			firstTunnelAttempt.add(possibleTunnels.get(i));
			
			if(!tunnelsToAttempt.contains(firstTunnelAttempt)) {
				tunnelsToAttempt.add(firstTunnelAttempt);
			}
			
			for(int j = 1; j < possibleTunnels.size(); j++) {
				ArrayList<Tunnel> t = new ArrayList<Tunnel>();
//				t.add(firstTunnelAttempt);
				for(int k = 1; k < j; k++) {
					
				}
			}
		}
		
		return 0;
	}
}

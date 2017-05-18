package com.taylorb;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class MusicalChairGame {
	public static ArrayList<Chair> chairs;
	private ArrayList<Player> players = new ArrayList<Player>();
	public static ArrayList<Player> playerswithchairs = new ArrayList<Player>();
	private int contestants;
	private Scanner emcee = new Scanner(System.in);
	
	MusicalChairGame(int contestants) {
		this.contestants = contestants;
		MusicalChairGame.chairs = new ArrayList<Chair>();
		for (int i = 1; i <= contestants; i++) {
			players.add(new Player(i));
		}
	}
	
	public void startGame() {
		System.out.println("Number of Contestants: " + contestants);
		
		for (int i = 1; i < contestants; i++) {
			chairs.add(new Chair(i));
		}
		
		waitToStartRound(chairs.size());
		
		for (int i = 0; i < players.size(); i++) {
			players.get(i).start();
		}
		
		while (true) {
			int nonOccupiedChairs = 0;
			for (int i = 0; i < chairs.size(); i++) {
				if (!chairs.get(i).getOccupied()) {
					nonOccupiedChairs++;
				}
			}
			
			if (nonOccupiedChairs == 0) {
				for (Iterator<Player> i = players.iterator(); i.hasNext();) {
                    try {
                        i.next().wait();
		            }
		        
			        catch(Exception ex) {}
				}												
				
				System.out.println("Number of Chairs: " + chairs.size());
				System.out.println(chairs.toString());					
								
				for (Iterator<Player> iter = players.iterator(); iter.hasNext();) {
                    Player currPlayer = iter.next();
                    if(currPlayer.getHasChair()) {
                        currPlayer.reset();
                    }
                    
                    else {
                        System.out.println(currPlayer.toString() + " lost.");
                        currPlayer.terminate();
						iter.remove();
                    }
                }            
				
				chairs.remove(chairs.size()-1);
				for (int i = 0; i < chairs.size(); i++) {
					chairs.get(i).setOccupied(false);
					chairs.get(i).setPlayer(null);
				}
				
				System.out.println("");		//print new line to separate output between rounds
				playerswithchairs.clear();
				
				if (chairs.size() > 0) {
					waitToStartRound(chairs.size());		
					
					for (Iterator<Player> i = players.iterator(); i.hasNext();) {
                        try {
                            i.next().notifyPlayer();
    		            }
    		        
    			        catch(Exception ex) {}
    				}
				}   
			}
			
			if (chairs.size() == 0) {
				for (int i = 0; i < players.size(); i++) {
					if (players.get(i).isAlive()) {
						players.get(i).terminate();
						System.out.println(players.get(i).toString() + " won the game.");
					}
				}
				
				break;
			}
		}
		
		System.out.println("Game Over!");
	}
	
	public static synchronized void addTo(Player player) {		
		playerswithchairs.add(player);
	}
	
	public void waitToStartRound(int round) {
		System.out.print("Press any key to start round " + round);
		emcee.nextLine();
	}
}
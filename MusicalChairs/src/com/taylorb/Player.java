package com.taylorb;

import java.util.Random;
public class Player extends Thread {
	private int id;	
	private volatile boolean hasChair = false;
	private volatile boolean terminated = false;
	
	Player(int id) {
		this.id = id;
		this.hasChair = false;
	}
	
	public void terminate() {
	    terminated = true;
	}
	
	public void notifyPlayer() {
	    try {
		    Thread.sleep(getRandomTimeWait());
		    synchronized(this) {
		        this.notify();    
		    }
	    }
	    
	    catch(Exception ex) {
	        System.out.println(ex);
	    }
	}
	
	public void run() {
		try {
			Thread.sleep(getRandomTimeWait());
			
			while (!terminated) {	
    		    for (int i = 0; i < MusicalChairGame.chairs.size(); i++) {
    		    	Chair currentChair = MusicalChairGame.chairs.get(i);
    		    	if (currentChair.getChairLock().tryLock()) {		    		
    		    		if (!this.hasChair && !currentChair.getOccupied() && !terminated) {
    		    			this.hasChair = true;
    		    			MusicalChairGame.addTo(this);
    		    			currentChair.setPlayer(this);
    		    			currentChair.setOccupied(true);			    						    						    						    						    					   
    		    		}		 
    		    						    		
    		    		currentChair.getChairLock().unlock();				    		
    		    	}
    		    }			  		    		    		  
    		}
		}
		
		catch (Exception ex) {
		    System.out.println(ex);
		}			
	}
	
	public int getRandomTimeWait() {
		Random rand = new Random();
		int randomTime = rand.nextInt((500-0) + 1); 
		
		return randomTime;
	}
	
	public String toString() {
		return "P" + Integer.toString(this.id);
	}
	
	public boolean getHasChair() {
		return this.hasChair;
	}
	
	public void setHasChair(boolean hasChair) {
		this.hasChair = hasChair;
	}
	
	public void reset() {
		this.hasChair = false;
	}
	
	public int getPlayerId () {
		return this.id;
	}
}
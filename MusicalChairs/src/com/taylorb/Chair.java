package com.taylorb;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Chair {
	private int id;
	private Lock chairLock;
	private Player player;
	private boolean occupied;
	
	public Chair(int id) {
		this.id = id;
		this.chairLock = new ReentrantLock();
		this.occupied = false;
	}
	
	public synchronized Lock getChairLock() {
		return chairLock;		
	}
	
	public synchronized void setPlayer(Player player) {
		this.player = player;
	}
	
	public synchronized Player getPlayer() {
		return player;
	}
	
	public synchronized void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	public synchronized boolean getOccupied() {
		return occupied;
	}
	
	public synchronized String toString() {
		return this.player + " Has Chair " + "C" + Integer.toString(this.id);
	}
	
	public synchronized String getId() {
		return "C" + this.id;
	}
}
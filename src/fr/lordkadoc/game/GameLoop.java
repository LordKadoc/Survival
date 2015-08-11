package fr.lordkadoc.game;

import java.io.Serializable;

import fr.lordkadoc.entities.Player;
import fr.lordkadoc.terrain.Terrain;

public class GameLoop extends Thread implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8642094793566376169L;

	private Terrain terrain;
	
	public static final int LOOPS_PER_SEC = 100;
	
	private boolean stop;
	
	public GameLoop(Terrain terrain){
		this.terrain = terrain;
		this.stop = false;
	}
	
	public void run(){
			
		while(!stop){
			for(Player j : terrain.getJoueurs()){
				j.move(terrain);
			}
			terrain.notifyObservers();
			try {
				Thread.sleep(1000/LOOPS_PER_SEC);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public void stopLoop(){
		this.stop = true;
	}

}

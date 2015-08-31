package fr.lordkadoc.game;

import java.io.Serializable;

import fr.lordkadoc.entities.Player;
import fr.lordkadoc.terrain.Terrain;

public class GameLoop extends Thread implements Serializable{
	
	private Terrain terrain;
	
	public static final int LOOPS_PER_SEC = 25;
	
	private boolean stop;
	
	public GameLoop(Terrain terrain){
		this.terrain = terrain;
		this.stop = false;
	}
	
	public void run(){
			
		while(!stop){
			//long time = System.currentTimeMillis();
			for(Player j : terrain.getJoueurs()){
				j.move(terrain);
			}
			terrain.notifyObservers();
			//System.out.println("loop time : " + (System.currentTimeMillis()-time));
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

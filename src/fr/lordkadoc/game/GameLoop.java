package fr.lordkadoc.game;

import fr.lordkadoc.entities.Player;
import fr.lordkadoc.terrain.Terrain;

public class GameLoop extends Thread {
	
	private Terrain terrain;
	
	private int delai;
	
	private boolean stop;
	
	public GameLoop(Terrain terrain, int delai){
		this.terrain = terrain;
		this.delai = delai;
		this.stop = false;
	}
	
	public void run(){
			
		while(!stop){
			for(Player j : terrain.getJoueurs()){
				j.move(terrain);
			}
			terrain.notifyObservers();
			try {
				Thread.sleep(delai);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
	public void stopLoop(){
		this.stop = true;
	}

}

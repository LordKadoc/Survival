package fr.lordkadoc.terrain;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import fr.lordkadoc.entities.Player;
import fr.lordkadoc.game.GameLoop;

public class Terrain extends Observable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2798003023138992621L;

	private Map<Point,Chunk> chunks;
	
	private GameLoop gameLoop;
	
	private List<Player> players;
	
	public Terrain(){
		this.chunks = new HashMap<Point,Chunk>();
		this.players = new ArrayList<Player>();
		this.gameLoop = new GameLoop(this);
		this.gameLoop.start();
	}
	
	public Chunk getChunk(Point2D.Double coordinates){
		return chunks.get(new Point(Math.floorDiv((int)coordinates.getX(),Chunk.CHUNK_SIZE),Math.floorDiv((int)coordinates.getY(),Chunk.CHUNK_SIZE)));
	}
	
	public Cellule getCellule(Point2D.Double coordinates){
		return getChunk(coordinates).getCellule((int)Math.floor(coordinates.x),(int)Math.floor(coordinates.y));
	}
	
	public Player addPlayer(){
		Player player = new Player("Player"+(int)(Math.random()*99999),new Point2D.Double(95+(int)(Math.random()*10),95+(int)(Math.random()*10)));
		this.players.add(player);
		return player;
	}
	
	public List<Player> getJoueurs(){
		return players;
	}
	
	public Map<Point,Chunk> getChunks(){
		return chunks;
	}
	
	@Override
	public void notifyObservers(){
		this.setChanged();
		super.notifyObservers();
	}
	
}

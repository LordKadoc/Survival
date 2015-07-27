package fr.lordkadoc.entities;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import fr.lordkadoc.terrain.Cellule;
import fr.lordkadoc.terrain.Terrain;


public class Player extends Entity{
	
	private final String name;
	
	/**
	 * Create a new player
	 * 
	 * @param name the name of the player
	 * @param coordinates the location of the player
	 * @param speed the movement speed of the player
	 * @param maxHealth the maximum health the player can have
	 * @param size the size of the player
	 */
	public Player(String name, Point2D.Double coordinates, float speed, int maxHealth, int size){
		super(coordinates,speed,maxHealth,size);
		this.name = name;
	}
	
	/**
	 * Create a new player with some default parameters (speed, health and size)
	 * 
	 * @param name the name of the player
	 * @param coordinates the coordinates of the player
	 */
	public Player(String name, Point2D.Double coordinates){
		this(name, coordinates, 13.37f, 100, 30);
	}
	
	@Override
	public void move(Terrain terrain) {
		
		if(destination == null){
			return;
		}
		
		
		Point2D.Double nextLocation;
		
		Cellule cellAfterMovement = null;
				
		if(coordinates.x < destination.x + speed/100
				&& coordinates.x > destination.x - speed/100 
				&& coordinates.y < destination.y + speed/100 
				&& coordinates.y > destination.y - speed/100){
			nextLocation = destination;
			this.destination = null;
		}else{
			nextLocation = new Point2D.Double(coordinates.x+movement.x,coordinates.y+movement.y);	
		}
		
			
		cellAfterMovement = terrain.getChunkCourant(this).getCellule((int)nextLocation.x, (int)nextLocation.y);
		
		if(cellAfterMovement.estVide()){
			this.coordinates = nextLocation;
		}else{
			this.destination = null;
		}
		
			
	}

	@Override
	public void setDestination(Double destination) {
		double distance = destination.distance(coordinates);
		double distanceX = destination.x - coordinates.x;
		double distanceY = destination.y - coordinates.y;
		this.movement = new Point2D.Double((distanceX/distance/100*speed),(distanceY/distance/100*speed));
		this.destination = new Point2D.Double(destination.x,destination.y);
	}

	@Override
	public List<Double> getHitbox(Point2D.Double location) {
		List<Point2D.Double> list = new ArrayList<Point2D.Double>();
		list.add(new Point2D.Double(location.x-size/2, location.y-size/2));
		list.add(new Point2D.Double(location.x+size/2, location.y-size/2));
		list.add(new Point2D.Double(location.x+size/2, location.y+size/2));
		list.add(new Point2D.Double(location.x-size/2, location.y+size/2));
		return list;
	}
	
	@Override
	public boolean isAlive() {
		return health > 0;
	}
	
	//GETTERS AND SETTERS
	
	/**
	 * 
	 * @return the name of the player
	 */
	public String getName(){
		return name;
	}


	
}

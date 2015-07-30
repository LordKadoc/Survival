package fr.lordkadoc.entities;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import fr.lordkadoc.game.GameLoop;
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
		super(coordinates,null,speed,maxHealth,size);
		this.name = name;
	}
	
	/**
	 * Create a new player with some default parameters (speed, health and size)
	 * 
	 * @param name the name of the player
	 * @param coordinates the coordinates of the player
	 */
	public Player(String name, Point2D.Double coordinates){
		this(name, coordinates, 13.37f, 100, 20);
	}
	
	@Override
	public void move(Terrain terrain) {
			
		if(destination == null){
			return;
		}
			
		int loops = GameLoop.LOOPS_PER_SEC;
		Point2D.Double nextLocation;
				
		if(coordinates.x < destination.x + speed/loops
				&& coordinates.x > destination.x - speed/loops 
				&& coordinates.y < destination.y + speed/loops 
				&& coordinates.y > destination.y - speed/loops){
			nextLocation = destination;
			this.destination = null;
			return;
		}else{
			nextLocation = new Point2D.Double(coordinates.x+movement.x,coordinates.y+movement.y);	
		}
		
		if(testHitbox(nextLocation, terrain)){
			this.coordinates = nextLocation;
			return;
		}
		
		nextLocation = new Point2D.Double(coordinates.x,coordinates.y+speed/loops);
		
		if(testHitbox(nextLocation, terrain)){
			this.coordinates = nextLocation;
			setDestination(destination);
			return;
		}
		
		nextLocation = new Point2D.Double(coordinates.x+speed/loops,coordinates.y);
		
		if(testHitbox(nextLocation, terrain)){
			this.coordinates = nextLocation;
			setDestination(destination);
			return;
		}
	}

	private boolean testHitbox(Double nextLocation, Terrain terrain) {
		for(Point2D.Double point : getHitbox(nextLocation)){
			if(!terrain.getCellule(point).estVide()){
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	public void setDestination(Double destination) {
		double distance = destination.distance(coordinates);
		double distanceX = destination.x - coordinates.x;
		double distanceY = destination.y - coordinates.y;
		this.movement = new Point2D.Double((distanceX/distance/GameLoop.LOOPS_PER_SEC*speed),(distanceY/distance/GameLoop.LOOPS_PER_SEC*speed));
		this.destination = new Point2D.Double(destination.x,destination.y);
	}

	@Override
	public List<Point2D.Double> getHitbox(Point2D.Double location) {
		double s = Cellule.toCellCoordinates((double)size/2);
		List<Point2D.Double> list = new ArrayList<Point2D.Double>();
		list.add(new Point2D.Double(location.x-s, location.y-s));
		list.add(new Point2D.Double(location.x+s, location.y-s));
		list.add(new Point2D.Double(location.x+s, location.y+s));
		list.add(new Point2D.Double(location.x-s, location.y+s));
		return list;
	}
	
	public boolean collision(){
		return true;
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

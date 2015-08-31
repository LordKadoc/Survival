package fr.lordkadoc.entities;

import java.awt.geom.Point2D;
import java.io.Serializable;

import fr.lordkadoc.image.ImageID;

public abstract class Entity implements Movable, Killable, Serializable{
	
	protected Point2D.Double coordinates;
	
	protected Point2D.Double movement;
	
	protected Point2D.Double destination;
	
	protected ImageID image;
	
	protected float speed;
	
	protected int health;
	
	protected int maxHealth;
	
	protected int size;
	
	/**
	 * Create a new movable and killable entity
	 * 
	 * @param coordinates the position of the entity on the map
	 * @param speed the speed at which the entity moves
	 * @param maxHealth the maximum health the entity can have
	 * @param size
	 */
	public Entity(Point2D.Double coordinates, ImageID image, float speed, int maxHealth, int size){
		this.coordinates = coordinates;
		this.movement = new Point2D.Double(0,0);
		this.image = image;
		this.speed = speed;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.size = size;
	}
	
	// GETTERS AND SETTERS
	
	public Point2D.Double getCoordinates(){
		return coordinates;
	}
	
	public Point2D.Double getMovement(){
		return movement;
	}
	
	public Point2D.Double getDestination(){
		return destination;
	}
	
	public ImageID getImage(){
		return image;
	}
	
	public void setImage(ImageID image){
		this.image = image;
	}
	
	public float getSpeed(){
		return speed;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int health){
		this.health = Math.min(this.maxHealth, Math.max(0, health));
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}
	
	public int getSize(){
		return size;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
}

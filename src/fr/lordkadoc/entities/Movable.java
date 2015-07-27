package fr.lordkadoc.entities;

import java.awt.geom.Point2D;
import java.util.List;

import fr.lordkadoc.terrain.Terrain;

public interface Movable {
	
	/**
	 * Move the entity to another location, according to it's destination and speed
	 */
	public abstract void move(Terrain terrain);
	
	/**
	 * Determine the new destination and movement vector of the entity
	 * 
	 * @param destination the new destination of the entity
	 */
	public abstract void setDestination(Point2D.Double destination);
	
	/**
	 * Determine the hitbox of the entity according to a location
	 * 
	 * @param the location from where to create the hitbox
	 * @return the points representing the hitbox of the entity
	 */
	public List<Point2D.Double> getHitbox(Point2D.Double location);

}

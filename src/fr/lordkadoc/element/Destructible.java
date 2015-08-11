package fr.lordkadoc.element;

import java.util.ArrayList;
import java.util.List;

import fr.lordkadoc.image.ImageID;

public abstract class Destructible extends Element {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4740658131544069096L;

	private final int maxHealth;
	
	private final int maxDrops;
	
	private final List<Resource> resources;
	
	private int health;

	public Destructible(ImageID image,int maxHealth, int maxDrops) {
		super(image);
		this.maxHealth = maxHealth;
		this.maxDrops = maxDrops;
		this.health = maxHealth;
		this.resources = new ArrayList<Resource>();
		this.initDrops();
	}
	
	public abstract void initDrops();
	
	public List<Resource> getDrop(){	
		List<Resource> drops = new ArrayList<Resource>();	
		if(!resources.isEmpty()){
			int nbDrops = (int)(Math.random()*maxDrops);
			for(int i=0;i<nbDrops;i++){
				drops.add(resources.get((int)(Math.random()*resources.size())));
			}
		}		
		return drops;
	}
	
	public void addDrop(Resource resource){
		this.resources.add(resource);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
}

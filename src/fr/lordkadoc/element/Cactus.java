package fr.lordkadoc.element;

import fr.lordkadoc.image.DossierImage;

public class Cactus extends Destructible {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2826567737959490823L;

	public Cactus(){
		super(DossierImage.CACTUS_1,50,2);
	}

	@Override
	public void initDrops() {
		this.addDrop(new Resource("cactus"));
		this.addDrop(new Resource("water"));
	}

}

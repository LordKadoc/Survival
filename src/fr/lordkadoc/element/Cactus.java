package fr.lordkadoc.element;

import fr.lordkadoc.image.DossierImage;

public class Cactus extends Destructible {
	
	public Cactus(){
		super(DossierImage.CACTUS_1,50,2);
	}

	@Override
	public void initDrops() {
		this.addDrop(new Resource("cactus"));
		this.addDrop(new Resource("water"));
	}

}

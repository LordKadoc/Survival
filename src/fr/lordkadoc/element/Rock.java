package fr.lordkadoc.element;

import fr.lordkadoc.image.DossierImage;

public class Rock extends Destructible {
	
	public Rock() {
		super(DossierImage.ROCK_1, 1000, 5);
	}

	@Override
	public void initDrops() {
		this.addDrop(new Resource("stone"));
		this.addDrop(new Resource("iron ore"));
	}

}

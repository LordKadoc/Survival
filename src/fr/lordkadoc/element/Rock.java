package fr.lordkadoc.element;

import fr.lordkadoc.image.DossierImage;

public class Rock extends Destructible {

	public Rock() {
		super(DossierImage.ROCK_1, 1000, 5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initDrops() {
		this.addDrop(new Resource("stone"));
		this.addDrop(new Resource("iron ore"));
	}

}

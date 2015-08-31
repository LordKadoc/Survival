package fr.lordkadoc.element;

import fr.lordkadoc.image.DossierImage;

public class Tree extends Destructible {

	public Tree() {
		super(DossierImage.TREE_1, 100, 2);
	}

	@Override
	public void initDrops() {
		this.addDrop(new Resource("log"));
		this.addDrop(new Resource("leaves"));
		this.addDrop(new Resource("branch"));
	}

}

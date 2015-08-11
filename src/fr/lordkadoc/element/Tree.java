package fr.lordkadoc.element;

import fr.lordkadoc.image.DossierImage;

public class Tree extends Destructible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5388980910259355517L;

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

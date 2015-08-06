package fr.lordkadoc.element;

import fr.lordkadoc.image.ImageID;

public class Element {
	
	private ImageID image;
	
	public Element(ImageID image){
		this.image = image;
	}
	
	public ImageID getImageID(){
		return image;
	}

}

package fr.lordkadoc.element;

import java.io.Serializable;

import fr.lordkadoc.image.ImageID;

public class Element implements Serializable{

	private ImageID image;
	
	public Element(ImageID image){
		this.image = image;
	}
	
	public ImageID getImageID(){
		return image;
	}

}

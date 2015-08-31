package fr.lordkadoc.cell;

import java.io.Serializable;

import fr.lordkadoc.image.DossierImage;
import fr.lordkadoc.image.ImageID;

public class Ground implements Serializable{
	
	private ImageID image;
	
	private boolean water;
	
	public Ground(ImageID image){
		this.image = image;
		this.water = false;
	}

	public Ground(ImageID image, boolean water){
		this.image = image;
		this.water = water;
	}
	
	public boolean isWater(){
		return water;
	}
	
	public void setWater(boolean water){
		this.water = water;
	}
	
	public ImageID getImage(){
		return water ? DossierImage.LAKE_1 : image;
	}
	
	public void setImage(ImageID image){
		this.image = image;
	}
	
}

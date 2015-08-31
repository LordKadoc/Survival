package fr.lordkadoc.image;

import java.io.Serializable;

public class ImageID implements Serializable{
	
	private String type;
	
	private int id;
	
	public ImageID(String type, int id){
		this.type = type;
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object imageID){
		return ((ImageID)imageID).id == id && ((ImageID)imageID).type.equals(type);
	}
	
	@Override
	public int hashCode() {
		return id + type.hashCode();
	};
	
	@Override
	public String toString(){
		return "[ImageID : " + type + "," + id + "]";
	}

}

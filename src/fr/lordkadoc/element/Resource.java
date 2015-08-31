package fr.lordkadoc.element;

import java.io.Serializable;

public class Resource implements Serializable{
	
	private final String name;
	
	public Resource(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

}

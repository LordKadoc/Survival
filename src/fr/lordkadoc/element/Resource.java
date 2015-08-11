package fr.lordkadoc.element;

import java.io.Serializable;

public class Resource implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8311917788294368620L;
	private final String name;
	
	public Resource(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

}

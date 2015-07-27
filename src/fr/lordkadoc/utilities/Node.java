package fr.lordkadoc.utilities;

import java.util.HashMap;
import java.util.Map;

import fr.lordkadoc.terrain.Cellule;

public class Node {
	
	private Map<String,Integer> intValues;
	
	private Cellule cellule;
	
	private boolean checked;
	
	private int x,y;
	
	public Node(Cellule cellule, int x, int y){
		this.cellule = cellule;
		this.intValues = new HashMap<String,Integer>();
		this.checked = false;
		this.x = x;
		this.y = y;
	}
	
	public int getValue(String key){
		return intValues.get(key);
	}
	
	public void setValue(String key, int value){
		this.intValues.put(key, value);
	}
	
	public boolean isChecked(){
		return checked;
	}
	
	public void check(){
		checked = true;
	}
	
	public Cellule getCellule(){
		return cellule;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}

package fr.lordkadoc.terrain;

import fr.lordkadoc.biome.Biome;
import fr.lordkadoc.element.Element;

public class Cellule {
	
	public final static int CELL_SIZE = 32;
	
	private final int x, y;
	
	private int sol_id;
	
	private Element element;
	
	public Cellule(int x, int y){
		this.x = x;
		this.y = y;
		this.element = null;
		this.sol_id = -1;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Element getElement() {
		return element;
	}

	public void ajouterElement(Element element) {
		this.element = element;
	}
	
	public void retirerElement(){
		this.element = null;
	}
	
	public boolean estVide(){
		return element == null;
	}
	
	public int getSol_id() {
		return sol_id;
	}

	public void setSol_id(int sol_id) {
		this.sol_id = sol_id;
	}

	@Override
	public String toString(){
		return estVide() ? " " : "X";
	}
	
}

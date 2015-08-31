package fr.lordkadoc.terrain;

import java.io.Serializable;

import fr.lordkadoc.cell.Ground;
import fr.lordkadoc.element.Element;
import fr.lordkadoc.image.DossierImage;

public class Cellule implements Serializable{
	
	public final static int CELL_SIZE = 32;
	
	private final int x, y;
	
	private Element element;
	
	private Ground ground;
	
	public Cellule(int x, int y){
		this.x = x;
		this.y = y;
		this.element = null;
		this.ground = new Ground(DossierImage.PLAIN_1);
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
	
	public Ground getGround(){
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}
	
	@Override
	public String toString(){
		return x + " / " + y;
	}
	
	public static double toCellCoordinates(double value){
		return value/CELL_SIZE;
	}

}

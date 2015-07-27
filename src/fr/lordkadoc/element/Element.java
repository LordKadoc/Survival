package fr.lordkadoc.element;

public class Element {
	
	private final ElementID id;
	
	public Element(ElementID id){
		this.id = id;
	}
	
	public ElementID getId(){
		return id;
	}

}

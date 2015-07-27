package fr.lordkadoc.terrain;


public class Chunk {
	
	public final static int CHUNK_SIZE = 128;
	
	private final int x, y;
	
	private boolean charge;
	
	private Cellule[][] cellules;
	
	public Chunk(int x, int y){
		this.x = x;
		this.y = y;
		this.charge = false;
	}
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Cellule[][] getCellules(){
		return cellules;
	}

	public boolean isCharge() {
		return charge;
	}
	
	public void charger(){
		this.cellules = new Cellule[CHUNK_SIZE][CHUNK_SIZE];
		for(int i=0;i<cellules.length;i++){
			for(int j=0;j<cellules[i].length;j++){
				cellules[i][j] = new Cellule(i+x*Chunk.CHUNK_SIZE,j+y*Chunk.CHUNK_SIZE);
			}
		}
		this.charge = true;
	}
	
	@Override
	public String toString(){
		return "Chunk : " + x + " / " + y;
	}


	public Cellule getCellule(int l, int h) {
		try{	
			l = l%CHUNK_SIZE;
			h = h%CHUNK_SIZE;
			if(l < 0){
				l+=CHUNK_SIZE;
			}
			if(h < 0){
				h+=CHUNK_SIZE;
			}
			return cellules[l][h];
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}

}

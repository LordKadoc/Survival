package fr.lordkadoc.biome;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import fr.lordkadoc.terrain.Cellule;
import fr.lordkadoc.terrain.Chunk;
import fr.lordkadoc.utilities.Node;

public abstract class Biome {
	
	protected Random random;
	protected final long seed;
	
	Biome(long seed){
		this.seed=seed;
	}
	
	public abstract void generer(Chunk chunk);
	
	public abstract void genererSol(Chunk chunk);
	
	public abstract void genererElements(Chunk chunk);
	
	public void genererBiomeLac(int centreX, int centreY,Chunk chunk){
		
		Cellule[][] cellules = chunk.getCellules();
		
		Node courant;
		Node[][] tableauNodes = new Node[cellules.length][cellules[0].length];
		LinkedList<Node> nodes = new LinkedList<Node>();
		List<Node> voisins;
		
		for(int i=0;i<tableauNodes.length;i++){
			for(int j=0;j<tableauNodes[i].length;j++){
				tableauNodes[i][j] = new Node(cellules[i][j],i,j);
			}
		}
		
		Node centre = tableauNodes[centreX][centreY];
		int value = random.nextInt(100)+100;	
		centre.setValue("height", value);
		
		nodes.offer(centre);
		
		while(!nodes.isEmpty()){
			courant = nodes.poll();
			courant.getCellule().setSol_id(0);
			courant.check();
			voisins = getVoisins(courant.getX(),courant.getY(),tableauNodes);
			for(Node n : voisins){
				n.setValue("height", courant.getValue("height")-20+random.nextInt(25));
				if(n.getValue("height") > 0 && !nodes.contains(n) && !n.isChecked()){
					nodes.offer(n);
				}
			}
		}
			
	}
	
	public List<Node> getVoisins(int x, int y, Node[][] nodes){
		List<Node> voisins = new ArrayList<Node>();
		if(x > 0){
			voisins.add(nodes[x-1][y]);
		}
		if(x < Chunk.CHUNK_SIZE-1){
			voisins.add(nodes[x+1][y]);
		}
		if(y > 0){
			voisins.add(nodes[x][y-1]);
		}
		if(y < Chunk.CHUNK_SIZE-1){
			voisins.add(nodes[x][y+1]);
		}
		return voisins;
	}
	
}

package fr.lordkadoc.biome;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import fr.lordkadoc.image.ImageID;
import fr.lordkadoc.terrain.Cellule;
import fr.lordkadoc.terrain.Chunk;
import fr.lordkadoc.utilities.Node;

public abstract class Biome {
	
	protected Random random;
	
	protected final long seed;
	
	protected final boolean generateLac;
	
	protected final ImageID imageGround;
	
	public Biome(long seed, ImageID imageGround){
		this(seed,imageGround,true);
	}

	public Biome(long seed, ImageID imageGround, boolean generateLac){
		this.seed=seed;
		this.imageGround = imageGround;
		this.generateLac = generateLac;
	}
	
	public void generate(Chunk chunk){
		this.random=new Random(seed+chunk.getX()*1000+chunk.getY()*1000000);
		chunk.charger();
	
		if(generateLac && random.nextInt(100)<30){
			int centreX = random.nextInt(Chunk.CHUNK_SIZE-80)+40;
			int centreY = random.nextInt(Chunk.CHUNK_SIZE-80)+40;
			generateLac(centreX, centreY, chunk);
		}
		generateGround(chunk);
		generateElements(chunk);
	}
	
	public void generateLac(int centreX, int centreY,Chunk chunk){
		
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
			courant.getCellule().getGround().setWater(true);
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
	
	public void generateGround(Chunk chunk){
		
		Cellule[][] cellules = chunk.getCellules();
		Cellule cellule;
		
		ImageID biomeD = GenerateurBiome.getImageGround(new Random(seed + (chunk.getX()+1) *1000  +  chunk.getY()     *1000000).nextInt(GenerateurBiome.length()));
		ImageID biomeG = GenerateurBiome.getImageGround(new Random(seed + (chunk.getX()-1) *1000  +  chunk.getY()     *1000000).nextInt(GenerateurBiome.length()));
		ImageID biomeH = GenerateurBiome.getImageGround(new Random(seed +  chunk.getX()    *1000  + (chunk.getY()-1)  *1000000).nextInt(GenerateurBiome.length()));
		ImageID biomeB = GenerateurBiome.getImageGround(new Random(seed +  chunk.getX()    *1000  + (chunk.getY()+1)  *1000000).nextInt(GenerateurBiome.length()));
		
		for(int i=0;i<cellules.length;i++){
			for(int j=0;j<cellules[i].length;j++){
				cellule = cellules[i][j];
				if(!cellule.getGround().isWater()){
					cellule.getGround().setImage(imageGround);
					if(i <= 1 && random.nextInt(3) == 0){
						cellule.getGround().setImage(biomeG);
					}else if(j <= 1 && random.nextInt(3) == 0){
						cellule.getGround().setImage(biomeH);
					}else if(i >= Chunk.CHUNK_SIZE-2 && random.nextInt(3) == 0){
						cellule.getGround().setImage(biomeD);
					}else if(j >= Chunk.CHUNK_SIZE-2 && random.nextInt(3) == 0){
						cellule.getGround().setImage(biomeB);
					}
				}
			}
		}
		
	}
	
	public abstract void generateElements(Chunk chunk);	
	
	public ImageID getImageGround(){
		return imageGround;
	}
	
	public static List<Node> getVoisins(int x, int y, Node[][] nodes){
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

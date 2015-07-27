package fr.lordkadoc.terrain;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.JFrame;

import fr.lordkadoc.biome.GenerateurBiome;
import fr.lordkadoc.entities.Player;
import fr.lordkadoc.game.GameLoop;
import fr.lordkadoc.vue.TerrainVue;

public class Terrain extends Observable{
	
	private Map<Point,Chunk> chunks;
	
	
	private GameLoop gameLoop;
	
	private List<Player> players;
	
	public Terrain(int largeur, int longueur){
		this.chunks = new HashMap<Point,Chunk>();
		this.players = new ArrayList<Player>();
		this.gameLoop = new GameLoop(this,10);
		this.gameLoop.start();
		this.initialiser();
	}

	private void initialiser() {
		Chunk chunk = new Chunk(0,0);
		chunk.charger();
		this.chunks.put(new Point(0,0),chunk);
		GenerateurBiome.genererChunk(chunk);
		
	}
	
	public List<Cellule> getChampVision(Player player, double tailleEcranX, double tailleEcranY){
		
		List<Cellule> cells = new ArrayList<Cellule>();	
		Chunk chunk;
		
		
		int rayonX = (int)Math.ceil(tailleEcranX/2/Cellule.CELL_SIZE);
		int rayonY = (int)Math.ceil(tailleEcranY/2/Cellule.CELL_SIZE);
		
		double x = player.getCoordinates().getX();
		double y = player.getCoordinates().getY();

		int chunkX,chunkY;
		
		
		for(int i=(int)Math.floor(x)-rayonX;i<(int)Math.ceil(x)+rayonX;i++){
			for(int j=(int)Math.floor(y)-rayonY;j<(int)Math.ceil(y)+rayonY;j++){
				
				chunkX = Math.floorDiv(i, Chunk.CHUNK_SIZE);
				chunkY = Math.floorDiv(j, Chunk.CHUNK_SIZE);
				chunk = chunks.get(new Point(chunkX,chunkY));
				
				
				if(chunk == null){	
					chunk = new Chunk(chunkX,chunkY);
					chunk.charger();
					chunks.put(new Point(chunkX,chunkY), chunk);
					GenerateurBiome.genererChunk(chunk);
				}
				
				cells.add(chunk.getCellule(i, j));
			}
		}
							
		return cells;
	}
	
	public Chunk getChunkCourant(Player joueur){
		return chunks.get(new Point(Math.floorDiv((int)joueur.getCoordinates().getX(),Chunk.CHUNK_SIZE),Math.floorDiv((int)joueur.getCoordinates().getY(),Chunk.CHUNK_SIZE)));
	}
	
	public void ajouterJoueur(Player joueur){
		this.players.add(joueur);
	}
	
	public List<Player> getJoueurs(){
		return players;
	}
	
	@Override
	public void notifyObservers(){
		this.setChanged();
		super.notifyObservers();
	}
	
	
	public static void main(String[] args){
		
		Terrain terrain = new Terrain(100,100);
		Player lucas = new Player("Lucas", new Point2D.Double(50,50));
		TerrainVue vue = new TerrainVue(terrain,lucas);
		
		terrain.ajouterJoueur(lucas);
			
		JFrame f = new JFrame("Test map");
		f.setResizable(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(vue);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		terrain.notifyObservers();
				
	}
	
}

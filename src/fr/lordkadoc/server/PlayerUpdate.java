package fr.lordkadoc.server;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.lordkadoc.biome.GenerateurBiome;
import fr.lordkadoc.entities.Entity;
import fr.lordkadoc.entities.Player;
import fr.lordkadoc.terrain.Cellule;
import fr.lordkadoc.terrain.Chunk;
import fr.lordkadoc.terrain.Terrain;

public class PlayerUpdate implements Serializable{
	
	private List<Player> visiblePlayers;
	
	private List<Entity> visibleEntities;
	
	private List<Cellule> visibleCells;
	
	private Player player;
	
	private int vueRadius;
	
	public PlayerUpdate(Terrain terrain, Player player, int vueRadius){
		
		this.player = player;	
		this.vueRadius = vueRadius;
		this.visiblePlayers = new ArrayList<Player>();
		this.visibleEntities = new ArrayList<Entity>();
		this.visibleCells = new ArrayList<Cellule>();
		
		this.setVisiblePlayers(terrain);
		this.setVisibleEntities(terrain);
		this.setVisibleCells(terrain);
		
	}
	
	private void setVisiblePlayers(Terrain terrain){
		double distance;
		for(Player p : terrain.getJoueurs()){
			distance = p.getCoordinates().distance(player.getCoordinates())-p.getSize()/2; // Replace with manhattan distance !!!
			if(Math.abs(distance)<vueRadius){ 
				visiblePlayers.add(p);
			}
		}
	}
	
	private void setVisibleEntities(Terrain terrain){
		//Nothing TODO right now
	}
	
	private void setVisibleCells(Terrain terrain){
		
		int rayonX = (int)Math.ceil(vueRadius/Cellule.CELL_SIZE);
		int rayonY = (int)Math.ceil(vueRadius/Cellule.CELL_SIZE);
		int chunkX,chunkY;
		
		double x = player.getCoordinates().getX();
		double y = player.getCoordinates().getY();
		
		Chunk chunk;
		
		for(int i=(int)Math.floor(x)-rayonX;i<(int)Math.ceil(x)+rayonX;i++){
			for(int j=(int)Math.floor(y)-rayonY;j<(int)Math.ceil(y)+rayonY;j++){
				
				chunkX = Math.floorDiv(i, Chunk.CHUNK_SIZE);
				chunkY = Math.floorDiv(j, Chunk.CHUNK_SIZE);
				chunk = terrain.getChunks().get(new Point(chunkX,chunkY));
				
				
				if(chunk == null){	
					chunk = new Chunk(chunkX,chunkY);
					chunk.charger();
					terrain.getChunks().put(new Point(chunkX,chunkY), chunk);
					GenerateurBiome.genererChunk(chunk);
				}
				
				visibleCells.add(chunk.getCellule(i, j));
			}
		}
		
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public List<Player> getVisiblePlayers(){
		return visiblePlayers;
	}
	
	public List<Entity> getVisibleEntities(){
		return visibleEntities;
	}
	
	public List<Cellule> getVisibleCells(){
		return visibleCells;
	}

}

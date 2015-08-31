package fr.lordkadoc.launcher;

import javax.swing.JFrame;

import fr.lordkadoc.server.Client;
import fr.lordkadoc.vue.TerrainVue;

public class Launcher extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5613915603534676152L;
	
	private ConnectionPanel connectionPanel;
	
	private TerrainVue terrainVue;
	
	public Launcher(){
		this.setTitle("Launcher for survival game");
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.connectionPanel = new ConnectionPanel();
	}

	public void start(){
		this.setContentPane(connectionPanel);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void showGame(Client client){
		this.terrainVue = new TerrainVue(client);
		this.setContentPane(terrainVue);
	}
	
	public TerrainVue getTerrainVue(){
		return terrainVue;
	}
	
}

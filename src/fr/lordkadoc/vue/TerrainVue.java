package fr.lordkadoc.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.lordkadoc.entities.Player;
import fr.lordkadoc.terrain.Cellule;
import fr.lordkadoc.terrain.Terrain;

public class TerrainVue extends JPanel implements Observer, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3282218454170869694L;
		
	private Terrain terrain;
	
	private Player joueur;
	
		
	public TerrainVue(Terrain terrain, Player joueur){
		terrain.addObserver(this);
		this.terrain = terrain;
		this.joueur = joueur;
		this.init();
	}

	private void init() {
		this.setPreferredSize(new Dimension(640,640));
		this.addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		int camX, camY;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		List<Cellule> cells = terrain.getChampVision(joueur, this.getWidth(), this.getHeight());
		
		camX = (int)(joueur.getCoordinates().getX()*Cellule.CELL_SIZE-this.getWidth()/2);
		camY = (int)(joueur.getCoordinates().getY()*Cellule.CELL_SIZE-this.getHeight()/2);
		
		g.translate(-camX, -camY);
		
		for(Cellule cell : cells){
			
			
			switch(cell.getSol_id()){
			
			case 0:
				g.setColor(new Color(0,50,200));
				break;
			case 1:
				g.setColor(new Color(0,200,50));
				break;
			case 2:
				g.setColor(new Color(239,221,111));
				break;
			case 3:
				g.setColor(Color.WHITE);
				break;		
			case 4:
				g.setColor(new Color(88, 42, 0));
				break;
			case 5:
				g.setColor(new Color(0,100,50));
				break;
			default:
				g.setColor(Color.GRAY);
				break;
			}
			
			g.fillRect(cell.getX()*Cellule.CELL_SIZE, cell.getY()*Cellule.CELL_SIZE, Cellule.CELL_SIZE, Cellule.CELL_SIZE);
			
			if(!cell.estVide()){
				
				switch(cell.getElement().getId()){
				case TREE_ID:	
					g.setColor(Color.GREEN);
					break;
				case CACTUS_ID:	
					g.setColor(Color.green);
					break;

				case ROCK_ID:	
					g.setColor(Color.GRAY);
					break;
				default:
					break;	
				}
				
				g.fillOval(cell.getX()*Cellule.CELL_SIZE+1, cell.getY()*Cellule.CELL_SIZE+1, Cellule.CELL_SIZE-2, Cellule.CELL_SIZE-2);
			}
		}
		
		g.setColor(Color.RED);
		g.fillOval((int)(joueur.getCoordinates().getX()*Cellule.CELL_SIZE)-joueur.getSize()/2,(int)(joueur.getCoordinates().getY()*Cellule.CELL_SIZE)-joueur.getSize()/2,joueur.getSize(),joueur.getSize());
		
		g.translate(camX, camY);
		
		g.setColor(Color.BLACK);
		g.drawString(terrain.getChunkCourant(joueur).toString(), 20, 20);
		g.drawString("joueur : " + (int)joueur.getCoordinates().getX() + "/" + (int)joueur.getCoordinates().getY(), 20, 50);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			double x = e.getX()-this.getWidth()/2;
			double y = e.getY()-this.getHeight()/2;
			x/=Cellule.CELL_SIZE;
			y/=Cellule.CELL_SIZE;
			x+=joueur.getCoordinates().getX();
			y+=joueur.getCoordinates().getY();
			joueur.setDestination(new Point2D.Double(x,y));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.requestFocusInWindow();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

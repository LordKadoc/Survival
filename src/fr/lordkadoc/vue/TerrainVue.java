package fr.lordkadoc.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fr.lordkadoc.entities.Player;
import fr.lordkadoc.image.DossierImage;
import fr.lordkadoc.terrain.Cellule;
import fr.lordkadoc.terrain.Terrain;

public class TerrainVue extends JPanel implements Observer, MouseListener, MouseMotionListener{

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
		this.addMouseMotionListener(this);
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
			
			g.drawImage(DossierImage.images.get(cell.getGround().getImage()), cell.getX()*Cellule.CELL_SIZE, cell.getY()*Cellule.CELL_SIZE, this);
			
			if(!cell.estVide()){
				g.drawImage(DossierImage.images.get(cell.getElement().getImageID()), cell.getX()*Cellule.CELL_SIZE, cell.getY()*Cellule.CELL_SIZE, this);
			}
			
			
		}
		
		g.drawImage(DossierImage.images.get(joueur.getImage()), (int)(joueur.getCoordinates().getX()*Cellule.CELL_SIZE)-joueur.getSize()/2,(int)(joueur.getCoordinates().getY()*Cellule.CELL_SIZE)-joueur.getSize()/2, this);
		g.translate(camX, camY);
		
		g.setColor(Color.BLACK);
		g.drawString(terrain.getChunk(joueur.getCoordinates()).toString(), 20, 20);
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
		if(SwingUtilities.isRightMouseButton(e)){
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

	@Override
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)){
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
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

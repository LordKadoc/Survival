package fr.lordkadoc.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.lordkadoc.entities.Player;
import fr.lordkadoc.image.DossierImage;
import fr.lordkadoc.server.PlayerUpdate;
import fr.lordkadoc.terrain.Cellule;

public class TerrainVue extends JPanel implements Observer, MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3282218454170869694L;
	
	private PlayerUpdate update;
				
	public TerrainVue(){
		this.init();
	}

	private void init() {
		this.setPreferredSize(new Dimension(600,600));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		if(update == null){
			return;
		}
			
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		int camX = (int)(update.getPlayer().getCoordinates().getX()*Cellule.CELL_SIZE-this.getWidth()/2);
		int camY = (int)(update.getPlayer().getCoordinates().getY()*Cellule.CELL_SIZE-this.getHeight()/2);
		
		g.translate(-camX, -camY);
		
		for(Cellule cell : update.getVisibleCells()){		
			g.drawImage(DossierImage.images.get(cell.getGround().getImage()), cell.getX()*Cellule.CELL_SIZE, cell.getY()*Cellule.CELL_SIZE, this);	
			if(!cell.estVide()){
				g.drawImage(DossierImage.images.get(cell.getElement().getImageID()), cell.getX()*Cellule.CELL_SIZE, cell.getY()*Cellule.CELL_SIZE, this);
			}		
		}
		
		for(Player player : update.getVisiblePlayers()){
			g.drawImage(DossierImage.images.get(player.getImage()), (int)(player.getCoordinates().getX()*Cellule.CELL_SIZE)-player.getSize()/2,(int)(player.getCoordinates().getY()*Cellule.CELL_SIZE)-player.getSize()/2, this);
		}
		
		g.translate(camX, camY);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		this.update = (PlayerUpdate)arg;
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
		/*if(SwingUtilities.isRightMouseButton(e)){
			double x = e.getX()-this.getWidth()/2;
			double y = e.getY()-this.getHeight()/2;
			x/=Cellule.CELL_SIZE;
			y/=Cellule.CELL_SIZE;
			x+=joueur.getCoordinates().getX();
			y+=joueur.getCoordinates().getY();
			joueur.setDestination(new Point2D.Double(x,y));
		}*/
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
		/*if(SwingUtilities.isRightMouseButton(e)){
			double x = e.getX()-this.getWidth()/2;
			double y = e.getY()-this.getHeight()/2;
			x/=Cellule.CELL_SIZE;
			y/=Cellule.CELL_SIZE;
			x+=joueur.getCoordinates().getX();
			y+=joueur.getCoordinates().getY();
			joueur.setDestination(new Point2D.Double(x,y));
		}*/
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

}

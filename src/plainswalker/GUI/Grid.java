//Graphic grid representation of the world

package plainswalker.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Scrollable;

import plainswalker.GUI.Interface.placerMode;
import plainswalker.simulation.Tile;

public class Grid extends JPanel implements MouseListener, MouseMotionListener, Scrollable{

	private Interface gui;
	private static final long serialVersionUID = 1L;
	protected int length, width;
	private Tile[][] tiles;
	private ArrayList<HerdAnimalPlacer> herdAnimals;
	
	//Set up grid parameters
	public Grid(Interface inter, int l, int w){
		
		gui = inter;
		herdAnimals = new ArrayList<HerdAnimalPlacer>();
		length = l;
		width = w;
		tiles = new Tile[length][width];
		for(int i = 0; i < length; ++i)
			for(int j = 0; j < width; ++j)
				tiles[i][j] = new Tile(j, i, 0f);
		
		this.setLayout(null);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(30*w,30*l));
		setVisible(true);
		
	}
	
	//Draw gridlines
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		((Graphics2D) g).setPaint(Color.BLACK);
		for(int i = 0; i < length; ++i)
			for(int j = 0; j < width; ++j){
				g.drawLine(0, i*30, width*30, i*30);
				g.drawLine(j*30, 0, j*30, length*30);
				
			}
		
	}
	
	//Place an object on the grid
	public void mouseClicked(MouseEvent e) {
		
		//Place Herd Animal
		if(gui.curMode == placerMode.HERD_ANIMAL){
		
			setVisible(false);
			System.out.println("Animal Placed");
			HerdAnimalPlacer h = new HerdAnimalPlacer(new Point(e.getX(), e.getY()));
			herdAnimals.add(h);
			add(h);
			validate();
			setVisible(true);
			System.out.println("No. Animal: " + this.getComponentCount());
		
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		/*oldView = gui.gridFrame.getViewport().getViewPosition();
		oldX = e.getX();
		oldY = e.getY();*/
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		/*int newX = (int)(oldX-e.getX());
		int newY = (int)(oldY-e.getY());
		Point newView = new Point(oldView.x-newX, oldView.y-newY);
		/*if(newView.x < 0)
			newView.x = 0;
		else if(newView.x > 30*width)
			newView.x = 30*width-
		gui.gridFrame.getViewport().setViewPosition(newView);
		repaint();*/
		
	}

	//Display current location information
	public void mouseMoved(MouseEvent e) {
		
		gui.tileData.setText("x: " + e.getX() + " y: " + e.getY() + " h: " + tiles[e.getY()/30][e.getX()/30].getHeight());
		
	}

	//Allows scrolling of the grid
	
	public Dimension getPreferredScrollableViewportSize() {
		return new Dimension(width, length);
	}

	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 1;
	}

	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 30;
	}

	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

}

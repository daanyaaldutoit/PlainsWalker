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

import javax.swing.JPanel;
import javax.swing.Scrollable;

import plainswalker.environment.Tile;

public class Grid extends JPanel implements MouseListener, MouseMotionListener, Scrollable{

	Interface gui;
	private static final long serialVersionUID = 1L;
	protected int length, width;
	private Tile[][] tiles;
	private Point oldView;
	private float oldX, oldY;
	
	public Grid(Interface inter, int l, int w){
		
		gui = inter;
		length = l;
		width = w;
		tiles = new Tile[length][width];
		for(int i = 0; i < length; ++i)
			for(int j = 0; j < width; ++j)
				tiles[i][j] = new Tile(j, i, j + ", " + i);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(30*w,30*l));
		setVisible(true);
		
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		((Graphics2D) g).setPaint(Color.BLACK);
		for(int i = 0; i < length; ++i)
			for(int j = 0; j < width; ++j){
				g.drawLine(0, i*30, width*30, i*30);
				g.drawLine(j*30, 0, j*30, length*30);
				
			}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		System.out.println(e.getX()/30 + ", " + e.getY()/30);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		oldView = gui.gridFrame.getViewport().getViewPosition();
		oldX = e.getX();
		oldY = e.getY();
		
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
		
		int newX = (int)(oldX-e.getX());
		int newY = (int)(oldY-e.getY());
		Point newView = new Point(oldView.x-newX, oldView.y-newY);
		/*if(newView.x < 0)
			newView.x = 0;
		else if(newView.x > 30*width)
			newView.x = 30*width-*/
		gui.gridFrame.getViewport().setViewPosition(newView);
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		
		return new Dimension(width, length);
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		
		return 1;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 30;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}

}

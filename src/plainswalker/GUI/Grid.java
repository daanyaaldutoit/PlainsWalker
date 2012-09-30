//Graphic grid representation of the world

package plainswalker.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Scrollable;

import plainswalker.controller.GridListener;
import plainswalker.simulation.HeightMap;

public class Grid extends JPanel implements Scrollable{
	
	private static final long serialVersionUID = 1L;
	public static final int blockSize = 15;
	
	protected int length, width;
	
	protected Rectangle2D selection;
	protected BufferedImage heightMap;
	
	protected ArrayList<HerdAnimalMarker>[] herdMarks = new ArrayList[9];
	protected LinkedList<WaypointMarker>[] wayMarks = new LinkedList[9];
	protected ArrayList<PredatorMarker>[] predMarks = new ArrayList[9];
	
	private AffineTransform scale = new AffineTransform();
	
	//Set up grid parameters
	public Grid(HeightMap h){
		
		scale.scale(3, 3);
		length = h.getLength();
		width = h.getWidth();
		heightMap = h.getBufferedImage(width*5, 5*length);
		this.setLayout(null);
		
		setPreferredSize(new Dimension(blockSize*width,blockSize*length));
		
		for(int i = 0; i < 9; ++i){
			herdMarks[i] = new ArrayList<HerdAnimalMarker>();
			wayMarks[i] = new LinkedList<WaypointMarker>();
			predMarks[i] = new ArrayList<PredatorMarker>();
		}
		
		setVisible(true);
		
	}
	
	//Draw gridlines
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		g2D.drawImage(heightMap, scale, null);
		
		//Draw links between waypoints
		g2D.setColor(Color.BLUE);
		for(int i = 0; i < 9; ++i){
							
			for(int j = 0; j < wayMarks[i].size()-1; ++j){
				g2D.drawLine(wayMarks[i].get(j).getX(), wayMarks[i].get(j).getY(), wayMarks[i].get(j+1).getX(), wayMarks[i].get(j+1).getY());
						
			}		
							
		}
		
		//Draw selection recangle
		if(selection != null)
			g2D.draw(selection);
		
	}

	//Allows scrolling of the grid
	//-------------------------------------------------------------------------------------
	
	public Dimension getPreferredScrollableViewportSize() {
		return new Dimension(width, length);
	}

	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 1;
	}

	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return blockSize;
	}

	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	public boolean getScrollableTracksViewportHeight() {
		return false;
	}
	
	//-------------------------------------------------------------------------------
	
	//Attach mouse controller to grid
	public void setListener(GridListener lis){
		
		addMouseListener(lis);
		addMouseMotionListener(lis);
		
	}

	//Change visiblity of avoidance radii
	public void toggleShowAvoid() {
		
		for(int i = 0; i < 9; ++i){
			
			for(HerdAnimalMarker hm : herdMarks[i])
				hm.showARadius = !hm.showARadius;
			
			for(PredatorMarker pm: predMarks[i])
				pm.showARadius = !pm.showARadius;
				
		}
		
		validate();
		repaint();
		
	}

	//Change visiblity of neighbours radii
	public void toggleShowNeighbours() {
		
		for(int i = 0; i < 9; ++i){
			
			for(HerdAnimalMarker hm : herdMarks[i])
				hm.showNRadius = !hm.showNRadius;
				
		}
		
		validate();
		repaint();
		
	}
	
	public void setSelectionRectangle(Rectangle2D s){
		
		selection = s;
		getParent().validate();
		getParent().repaint();
		
	}
	
	public void selectMarkers(){
		
		for(int i = 0; i < 9; ++i){
			
			for(HerdAnimalMarker hm : herdMarks[i]){
			
				Point newLoc = new Point(hm.getX()+HerdAnimalMarker.SIZE.width/2, hm.getY()+HerdAnimalMarker.SIZE.height/2);
				
				if(selection != null && selection.contains(newLoc))
					hm.selected = true;
				else
					hm.selected = false;
				
			}
				
		}
		
	}

}

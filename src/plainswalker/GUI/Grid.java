//Graphic grid representation of the world

package plainswalker.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.JViewport;

import plainswalker.controller.GridListener;
import plainswalker.simulation.Simulation;

public class Grid extends JPanel implements Scrollable{
	
	private static final long serialVersionUID = 1L;
	protected static final int blockSize = 30;
	
	protected int length, width;
	private BufferedImage buffer;
	protected ArrayList<HerdAnimalMarker>[] herdMarks = new ArrayList[10];
	protected LinkedList<WaypointMarker>[] wayMarks = new LinkedList[10];
	
	//Set up grid parameters
	public Grid(int l, int w){
		
		length = l;
		width = w;
		this.setLayout(null);
		
		setPreferredSize(new Dimension(blockSize*w,blockSize*l));
		
		for(int i = 0; i < 10; ++i){
			herdMarks[i] = new ArrayList<HerdAnimalMarker>();
			wayMarks[i] = new LinkedList<WaypointMarker>();
		}
		
		setVisible(true);
		
	}
	
	//Draw gridlines
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		/*Rectangle vb = ((JViewport) getParent()).getViewRect();
		for(int i = vb.y; i < vb.y+vb.height; i+= blockSize)
			for(int j = vb.x; j < vb.x+vb.width; j+= blockSize){
				
				this.getParent().getGraphics().drawLine(vb.x, i-(i%blockSize), vb.x+vb.width, i-(i%blockSize));			//horizontal lines
				this.getParent().getGraphics().drawLine(j-(j%blockSize), vb.y, j-(j%blockSize), vb.y+vb.height);		//vertical lines
				
			}*/
		
		//Draw grid to buffer if necessary
		/*if(buffer == null){
			
			int bufferWidth = getWidth()/4;
			int bufferHeight = getHeight()/4;
			buffer = new BufferedImage(bufferWidth, bufferHeight, BufferedImage.TYPE_BYTE_GRAY);//(BufferedImage) createImage(getWidth(), getHeight());
			Graphics2D imDraw = buffer.createGraphics();
			imDraw.setBackground(Color.WHITE);
			imDraw.clearRect(0, 0, bufferWidth, bufferHeight);
			imDraw.setColor(Color.BLACK);
			for(int i = 0; i < length/4; ++i)
				for(int j = 0; j < width/4; ++j){
					imDraw.drawLine(0, i*blockSize, width*blockSize, i*blockSize);
					imDraw.drawLine(j*blockSize, 0, j*blockSize, length*blockSize);
					}
			
		}
		
		//Draw grid in pieces
		for(int i = 0; i < getHeight(); i+=buffer.getHeight())
			for(int j = 0; j < getWidth(); j+=buffer.getWidth())
				g.drawImage(buffer, j, i, null);*/
		
		
		//Draw links between waypoints
		g2D.setColor(Color.BLUE);
		for(int i = 0; i < 10; ++i){
							
			for(int j = 0; j < wayMarks[0].size()-1; ++j){
				g2D.drawLine(wayMarks[0].get(j).getX(), wayMarks[0].get(j).getY(), wayMarks[0].get(j+1).getX(), wayMarks[0].get(j+1).getY());
						
			}		
							
		}
		
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

}

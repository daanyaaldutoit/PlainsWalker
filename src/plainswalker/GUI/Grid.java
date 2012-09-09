//Graphic grid representation of the world

package plainswalker.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Scrollable;

import plainswalker.controller.GridListener;

public class Grid extends JPanel implements Scrollable{
	
	private static final long serialVersionUID = 1L;
	private static final int blockSize = 30;
	
	protected int length, width;
	private BufferedImage buffer;
	protected ArrayList<HerdAnimalMarker>[] herdMarks = new ArrayList[10];
	
	//Set up grid parameters
	public Grid(int l, int w){
		
		length = l;
		width = w;
		this.setLayout(null);
		
		setPreferredSize(new Dimension(blockSize*w,blockSize*l));
		
		for(int i = 0; i < 10; ++i)
			herdMarks[i] = new ArrayList<HerdAnimalMarker>();
		
		setVisible(true);
		
	}
	
	//Draw gridlines
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		//Graphics2D g2D = (Graphics2D)g;
		
		if(buffer == null){
			
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
		
		for(int i = 0; i < getHeight(); i+=buffer.getHeight())
			for(int j = 0; j < getWidth(); j+=buffer.getWidth())
				g.drawImage(buffer, j, i, null);
		
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
		return blockSize;
	}

	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	public boolean getScrollableTracksViewportHeight() {
		return false;
	}
	
	public void setListener(GridListener lis){
		
		addMouseListener(lis);
		addMouseMotionListener(lis);
		
	}

}

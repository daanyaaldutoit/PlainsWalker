package plainswalker.GUI;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GridViewport extends JViewport {

	private static final long serialVersionUID = 1L;
	protected boolean showGrid = true;
	
	private ChangeListener change = new ChangeListener(){

		@Override
		public void stateChanged(ChangeEvent arg0) {
			repaint();
			
		}
		
	};
	
	public GridViewport(){
		
		super();
		
		this.addChangeListener(change);
		
	}
	
	public void paint(Graphics g){
		
		super.paint(g);
		
		if(showGrid){
		
			Rectangle view = this.getViewRect();
		
			int yMod = (view.y)%Grid.blockSize;
			int xMod = (view.x)%Grid.blockSize;
		
			for(int i = 0; i < view.height+Grid.blockSize; i+=Grid.blockSize)
				for(int j = 0; j < view.width+Grid.blockSize; j+=Grid.blockSize){
				
					g.drawLine(0, i-yMod, view.width, i-yMod);	//horiz lines
					g.drawLine(j-xMod, 0, j-xMod, view.height);	//vert lines
				
				}
		
		}
		
	}
	
}

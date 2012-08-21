package plainswalker.environment;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile implements MouseListener{
	
	private int xCo, yCo;
	
	public Tile(int x, int y, String label){
		
		xCo = x;
		yCo = y;
		//addMouseListener(this);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(xCo + " , " + yCo);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

}

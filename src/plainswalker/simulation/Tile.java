//A discretised portion of the simulation world
//Daanyaal du Toit
//09 September 2012

package plainswalker.simulation;

import java.io.Serializable;

public class Tile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int xCo, yCo;
	protected boolean passable = true;
	
	public Tile(int x, int y){
		
		xCo = x;
		yCo = y;
		
	}
	
	public int getX(){return xCo;}
	
	public int getY(){return yCo;}
	
	public boolean getPassable(){
		
		return passable;
		
	}

}

//A discretised portion of the simulation world
//Daanyaal du Toit
//09 September 2012

package plainswalker.simulation;

import java.io.Serializable;

public class Tile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int xCo, yCo;
	protected float height;
	protected boolean passable;
	
	public Tile(int x, int y, float h){
		
		xCo = x;
		yCo = y;
		height = h;
		
	}
	
	public float getHeight(){
		
		return height;
		
	}

}

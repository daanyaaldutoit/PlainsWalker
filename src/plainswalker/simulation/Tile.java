//A descetised portion of the simulation world
//Daanyaal du Toit
//09 September 2012

package plainswalker.simulation;

public class Tile{
	
	private int xCo, yCo;
	protected float height;
	
	public Tile(int x, int y, float h){
		
		xCo = x;
		yCo = y;
		height = h;
		
	}
	
	public float getHeight(){
		
		return height;
		
	}

}

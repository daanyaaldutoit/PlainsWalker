//Handles the realtime change of environment

package plainswalker.simulation;

import java.util.ArrayList;

import plainswalker.GUI.Interface;

public class Simulation{

	private Tile[][] tiles;
	final float TIMESTEP = 0.001f;
	protected ArrayList<Animal> anims = new ArrayList<Animal>();
	//protected ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
	private Simulation prevState;
	private Interface gui;
	
	private class Updator implements Runnable{

		private Simulation sim;
		int numStepsTaken = 0;
		
		public Updator(Simulation s){
			
			sim = s;
			
		}
		
		public void run() {
			
			while(numStepsTaken < 1000){
				
				for(Animal a : anims){
					a.update(TIMESTEP, sim);
				}
				gui.getMain().repaint();
				
			}
			
		}
		
	}
	
	public Simulation(Interface inter, int l, int w){
		
		gui = inter;
		tiles = new Tile[l][w];
		for(int i = 0; i < l; ++i)
			for(int j = 0; j < w; ++j)
				tiles[i][j] = new Tile(j, i, 0f);
		
	}
	
	public float getHeight(int x, int y){
		
		return tiles[y/30][x/30].getHeight();
		
	}
	
	public void start(){
		
		prevState = this;
		Thread update = new Thread(new Updator(this));
		update.start();
		
	}
	
	public void addAnimal(Animal a){
		
		anims.add(a);
		
	}
	
}

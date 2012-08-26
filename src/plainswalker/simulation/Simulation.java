//Handles the realtime change of environment

package plainswalker.simulation;

import java.util.ArrayList;

import plainswalker.GUI.Interface;

public class Simulation{

	private Tile[][] tiles;
	final float TIMESTEP = 0.001f;
	protected ArrayList<Herd> herds = new ArrayList<Herd>();	//tenth herd is list of unassigned animals
	//protected ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
	private Simulation prevState;
	private Interface gui;
	
	//allows realtime changes to environment
	private class Updator implements Runnable{

		private Simulation sim;
		int numStepsTaken = 0;
		
		public Updator(Simulation s){
			
			sim = s;
			
		}
		
		public void run() {
			
			while(numStepsTaken < 10000000){
				
				for(Animal a : herds.get(0).anims){
					a.update(TIMESTEP, sim);
				}	
			
				++numStepsTaken;
				
			}
			
		}
		
	}
	
	public Simulation(Interface inter, int l, int w){
		
		gui = inter;
		tiles = new Tile[l][w];
		for(int i = 0; i < l; ++i)
			for(int j = 0; j < w; ++j)
				tiles[i][j] = new Tile(j, i, 0f);
		
		for(int i = 0; i < 10; ++i)
			herds.add(new Herd());
		
	}
	
	//return height value at tile
	public float getHeight(int x, int y){
		
		return tiles[y/30][x/30].getHeight();
		
	}
	
	//Save state and begin processing
	public void start(){
		
		prevState = this;
		Thread update = new Thread(new Updator(this));
		update.start();
		
	}
	
	//Add animal to a herd
	public void addAnimal(HerdAnimal a, int index){
		
		herds.get(index).anims.add(a);
		
	}
	
}

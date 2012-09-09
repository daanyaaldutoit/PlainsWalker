//Handles the realtime change of environment

package plainswalker.simulation;

import java.util.Observable;

public class Simulation extends Observable{

	private Tile[][] tiles;
	final float TIMESTEP = 0.001f;
	protected Herd[] herds = new Herd[10];	//tenth herd is list of unassigned animals
	//protected ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
	private Thread updator;
	
	//allows realtime changes to environment
	private class Updator implements Runnable{

		private Simulation sim;
		//int numStepsTaken = 0;
		
		public Updator(Simulation s){
			
			sim = s;
			
		}
		
		public void run() {
			
				while(true){
				for(Animal a : herds[0].anims){
					a.update(TIMESTEP, sim);
				}
				setChanged();
				notifyObservers(herds);
				}
		}
		
	}
	
	public Simulation(int l, int w){
		
		tiles = new Tile[l][w];
		for(int i = 0; i < l; ++i)
			for(int j = 0; j < w; ++j)
				tiles[i][j] = new Tile(j, i, 0f);
		
		for(int i = 0; i < 10; ++i)
			herds[i] = new Herd();
		
	}
	
	//return height value at tile
	public float getHeight(int x, int y){
		
		return tiles[y/30][x/30].getHeight();
		
	}
	
	//Save state and begin processing
	public void start(){
			
		updator = new Thread(new Updator(this));
		updator.start();
		
	}
	
	//Add animal to a herd
	public void addAnimal(HerdAnimal a, int index){
		
		herds[index].anims.add(a);
		setChanged();
		notifyObservers(a);
		
	}
	
	public void pause() {
		
		updator.interrupt();
		
	}

	public void stop() {
		
		updator.interrupt();
		//tiles = prevState.tiles;
		//Thread update = new Thread(new Updator(this));
		//update.start();
		
	}
	
}

//Handles the realtime change of environment

package plainswalker.simulation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Observable;

public class Simulation extends Observable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Tile[][] tiles;
	final static float TIMESTEP = 0.001f;
	protected Herd[] herds = new Herd[10];	//tenth herd is list of unassigned animals
	protected LinkedList<Vector3D>[] routes = new LinkedList[10];
	protected Pack[] packs = new Pack[10];
	private transient Thread updator;
	
	//allows realtime changes to environment
	private class Updator implements Runnable{

		private Simulation sim;
		
		public Updator(Simulation s){
			
			sim = s;
			
		}
		
		public void run() {
			
				//test
				herds[0].route = routes[0];
				
				while(true){
				for(Herd h : herds){
					h.goToWaypoint(sim);
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
		
		for(int i = 0; i < 10; ++i){
			herds[i] = new Herd();
			routes[i] = new LinkedList<Vector3D>();
			packs[i] = new Pack();
		}
		
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
	
	//Add waypoint to route
	public void addWaypoint(Vector3D way, int index){
		
		routes[index].add(way);
		setChanged();
		notifyObservers(way);
		
	}
	
	//Add predator to world
	public void addPredator(Predator p, int index){
		
		packs[index].preds.add(p);
		setChanged();
		notifyObservers(p);
		
	}
	
	public void pause() {
		
		updator.interrupt();
		
	}

	public void stop() {
		
		updator.interrupt();
		
	}

	//Getters for simulation elements
	//----------------------------------------------------------------------
	
	public Herd[] getHerds() {
		return herds;
	}

	public LinkedList<Vector3D>[] getRoutes() {
		return routes;
	}

	public Pack[] getPacks() {
		return packs;
	}
	
	//------------------------------------------------------------------------
	
	/*private void writeObject(ObjectOutputStream stream)
	        throws IOException{
		
		for(int i = 0; i < 10; ++i)
			stream.writeObject(herds[i]);
		
	}
	
	private void readObject(ObjectInputStream stream)
	        throws IOException, ClassNotFoundException{
		
		for(int i = 0; i < 10; ++i)
			herds[i] = (Herd)stream.readObject();
		
	}*/
	
}

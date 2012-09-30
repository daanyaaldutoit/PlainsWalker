//Handles the realtime change of environment

package plainswalker.simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import plainswalker.GUI.Grid;

public class Simulation extends Observable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Tile[][] tiles;
	protected Terrain terrain;
	final static float TIMESTEP = 0.005f;	//timestep in seconds
	protected Herd[] herds = new Herd[9];	//tenth herd is list of unassigned animals
	protected LinkedList<Waypoint>[] routes = new LinkedList[9];
	protected Pack[] packs = new Pack[9];
	private transient Thread updator;
	
	//Animation variables
	protected transient ArrayList<ArrayList<Frame>> frameStorage;
	protected transient ArrayList<Frame> frames;
	protected transient int curFrame = 0;
	protected transient boolean paused = false;
	
	int toFrame = 0;
	
	//allows realtime changes to environment
	private class Updator implements Runnable{

		private Simulation sim;
		
		public Updator(Simulation s){
			
			sim = s;
			frameStorage = new ArrayList<ArrayList<Frame>>();
			frameStorage.add(new ArrayList<Frame>());
			curFrame = 0;
			
		}
		
		public void run() {
				
				while(!Thread.interrupted()){
					
						if(frameStorage.get(frameStorage.size()-1).size() >= 20000){
							frameStorage.add(new ArrayList<Frame>());
						}
							
						for(Herd h : herds){
							h.goToWaypoint(sim);
				
						}
						
						for(Pack p: packs)
							p.goToWaypoint(sim);
				
						if(++toFrame == 10){
							frameStorage.get(frameStorage.size()-1).add(new Frame(herds, packs));
							toFrame = 0;
						}
						
						if(curFrame < frameStorage.get(frameStorage.size()-1).size() && !paused){
						
							setChanged();
							notifyObservers(frameStorage.get(frameStorage.size()-1).get(curFrame++));
							if(curFrame == 20000-1)
								curFrame = 0;
						
						}
		
				}
				
				setChanged();
				notifyObservers(frameStorage.get(0).get(0));
				
				for(int i = 0; i < 9; ++i){
					herds[i] = frameStorage.get(0).get(0).herds[i];
					packs[i] = frameStorage.get(0).get(0).packs[i];
				
				}
		
		}
		
	}
	
	public Simulation(HeightMap h){
		
		try {
			terrain = new Terrain(h, h.width*5, h.length*5);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tiles = new Tile[h.length][h.width];
		for(int i = 0; i < h.length; ++i)
			for(int j = 0; j < h.width; ++j)
				tiles[i][j] = new Tile(j, i, 0);
		
		for(int i = 0; i < 9; ++i){
			herds[i] = new Herd();
			routes[i] = new LinkedList<Waypoint>();
			packs[i] = new Pack();
		}
		
	}

	//return height value at tile
	public double getHeight(int x, int y){
		
		return terrain.getHeight(x/Grid.blockSize, y/Grid.blockSize);
		
	}
	
	//Adding options
	//-----------------------------------------------------------------------------------------
	
	//Add animal to a herd
	public void addAnimal(HerdAnimal a){
		
		herds[a.herdIndex].anims.add(a);
		setChanged();
		notifyObservers(a);
		
	}
	
	//Add waypoint to route
	public void addWaypoint(Waypoint way){
		
		routes[way.wayIndex].add(way);
		setChanged();
		notifyObservers(way);
		
	}
	
	//Add predator to world
	public void addPredator(Predator p){
		
		packs[p.packIndex].preds.add(p);
		setChanged();
		notifyObservers(p);
		
	}
	
	//------------------------------------------------------------------------------------------
	
	//Video options
	//-------------------------------------------------------------------------------------------
	
	//Save state and begin processing
	public void start(){
		
		if(paused == false){
		
			updator = new Thread(new Updator(this));
			//Timer t = new Timer(updator.getName());
			//t.scheduleAtFixedRate(new Updator(this), 0, (long) (TIMESTEP*1000));
			updator.start();
		
		}
		else
			paused = false;
		
	}
	
	public void pause() {
		
		paused = true;
		
	}

	public void stop() {
		
		updator.interrupt();
		updator = null;
		paused = false;
		
	}
	
	//----------------------------------------------------------------------------------------------

	//Getters for simulation elements
	//----------------------------------------------------------------------------------------------
	
	public Herd[] getHerds() {
		return herds;
	}

	public LinkedList<Waypoint>[] getRoutes() {
		return routes;
	}

	public Pack[] getPacks() {
		return packs;
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
	
	public Terrain getTerrain(){return terrain;}

	public void assignHerdRoute(int hIndex, int rIndex){
		
		herds[hIndex].assignRoute(routes[rIndex]);
		
	}
	
	public void assignPackRoute(int pIndex, int rIndex){
		
		packs[pIndex].assignRoute(routes[rIndex]);
		
	}
	
	public void setPassable(int x, int y, boolean pass) {
		
		tiles[y][x].passable = pass;
		setChanged();
		notifyObservers(tiles[y][x]);
		
	}
	
}

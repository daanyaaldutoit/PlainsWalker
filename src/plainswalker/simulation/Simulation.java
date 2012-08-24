//Handles the realtime change of environment

package plainswalker.simulation;

import java.util.ArrayList;

import plainswalker.GUI.Interface;

public class Simulation {

	private Tile[][] tiles;
	final float TIMESTEP = 1f;
	protected ArrayList<Animal> anims = new ArrayList<Animal>();
	private Simulation prevState;
	private Interface gui;
	
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
		for(int i = 0; i < 100; ++i)
			timeStep();
		
	}
	
	public void timeStep(){
		
		//gui.getMain().setVisible(false);
		
		for(Animal a : anims){
			a.update(TIMESTEP, this);
		}
		
		gui.getMain().validate();
		//gui.getMain().setVisible(true);
		
	}
	
	public void addAnimal(Animal a){
		
		anims.add(a);
		
	}
	
}

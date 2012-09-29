package plainswalker.simulation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Herd implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected transient Vector3D center;
	protected ArrayList<HerdAnimal> anims = new ArrayList<HerdAnimal>();
	protected LinkedList<Vector3D> route = new LinkedList<Vector3D>();
	
	public void goToWaypoint(Simulation s){
		
		center = new Vector3D();
		for(HerdAnimal a : anims){
				
				a.update(Simulation.TIMESTEP, s);
				center = center.plus(a.position);
				
		}
		
		center = center.multiply(1/(float)anims.size());
		if(route.size() > 1 && center.distance(route.getFirst()) < 20)
			route.poll();
		
	}
	
	public ArrayList<HerdAnimal> getAnims(){return anims;}
	
	public void assignRoute(LinkedList<Vector3D> r){
		
		route = new LinkedList<Vector3D>();
		for(Vector3D v: r)
			route.add(v);
		
	}
	
}

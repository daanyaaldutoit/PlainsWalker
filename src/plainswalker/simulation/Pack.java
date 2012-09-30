package plainswalker.simulation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Pack implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected transient Vector3D center;
	protected ArrayList<Predator> preds = new ArrayList<Predator>();
	protected LinkedList<Waypoint> route = new LinkedList<Waypoint>();

	public void goToWaypoint(Simulation s){
		
		center = new Vector3D();
		for(Predator p : preds){
				
				p.update(Simulation.TIMESTEP, s);
				center = center.plus(p.position);
				
		}
		
		center = center.multiply(1/(float)preds.size());
		if(route.size() > 1 && center.distance(route.getFirst()) < 20)
			route.poll();
		
	}
	
	public ArrayList<Predator> getPreds() {
		return preds;
	}

	public void assignRoute(LinkedList<Waypoint> routes){
		
		route = new LinkedList<Waypoint>();
		for(Waypoint w: routes)
			route.add(w);
		
	}

}

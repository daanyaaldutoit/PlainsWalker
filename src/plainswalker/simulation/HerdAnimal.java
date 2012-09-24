//Particle class modeling a herd animal

package plainswalker.simulation;

import java.util.ArrayList;

public class HerdAnimal extends Animal{
	
	private static final long serialVersionUID = 1L;
	
	public static int avoidRad = 50;
	public static int neighbourRad = 100;
	protected int herdIndex;
	private float accumulator = 1f;
	private float maxVel = 1.5f;
	
	public HerdAnimal(Vector3D p){
		
		position = p;
		velocity = new Vector3D();
		accelaration = new Vector3D();
		herdIndex = 0;
		
	}

	public void update(float dt, Simulation s) {
		
		accelaration = getAccel(s);
		velocity = velocity.plus(accelaration.multiply(dt));
		if(velocity.mag() > maxVel)
			velocity = velocity.multiply(maxVel/velocity.mag());
		position = position.plus(velocity.multiply(dt));
		
	}

	//Calculates weights of herding behavioural accelarations
	private Vector3D getAccel(Simulation s){
		
		Vector3D ac = new Vector3D();
		
		//Process neighbours into categories
		ArrayList<Animal> toAvoid = new ArrayList<Animal>();
		ArrayList<HerdAnimal> neighbours = new ArrayList<HerdAnimal>();
		processNeighbours(toAvoid, neighbours, s);
		
		//Get acceleration components
		Vector3D avoid = getAvoidDir(toAvoid);
		Vector3D centering = getCentDir(neighbours);
		Vector3D matching = getMatchDir(neighbours);
		Vector3D route = new Vector3D();
		
		if(s.herds[herdIndex].route.size() > 0)
			route = goTo(s.herds[herdIndex].route.getFirst());
		
		//Adjust by component strength between 0 and 1
		avoid = avoid.multiply(0.9f);
		centering = centering.multiply(0.8f);
		matching = matching.multiply(0.1f);
		route = route.multiply(0.6f);
		
		//Build acceleration with following priority order: route, avoid, matching, centering
		float magAccumulator = 0;
		
		ac = avoid;
		magAccumulator += avoid.mag();
		
		if(magAccumulator+route.mag() < accumulator){
			ac = ac.plus(route);
			magAccumulator += route.mag();
		}
		else{
			ac = ac.plus(route.multiply(1-(ac.mag()/accumulator)));
			magAccumulator = accumulator;
		}
		
		
		if(magAccumulator+matching.mag() < accumulator){
			ac = ac.plus(matching);
			magAccumulator += matching.mag();
		}
		else{
			ac = ac.plus(matching.multiply(1-(ac.mag()/accumulator)));
			magAccumulator = accumulator;
		}
		
		
		if(magAccumulator + centering.mag() < accumulator){
			ac = ac.plus(centering);
			magAccumulator += centering.mag();
		}
		else{
			ac = ac.plus(centering.multiply(1-(ac.mag()/accumulator)));
			magAccumulator = accumulator;
		}
		
		return ac;
		
	}

	//Populate avoid and neighbour lists
	protected void processNeighbours(ArrayList<Animal> avoid,
			ArrayList<HerdAnimal> neighbours, Simulation s) {
		
		for(Animal a: s.herds[herdIndex].anims)
			if(!a.equals(this)){
				
				if(shouldAvoid(a)){//avoidance	
					
					avoid.add(a);
					
					}
				if(position.distance(a.position) <= neighbourRad){//neighbours
					
					neighbours.add((HerdAnimal) a);
					
				}
				
			}
		
		for(Animal a: s.packs[0].preds){
			
			if(shouldAvoid(a))
				avoid.add(a);
			
		}
		
	}

	public int getARad() {
		return avoidRad;
	}
	
	protected boolean shouldAvoid(Animal a){
		
		return position.distance(a.position) <= a.getARad();
		
	}
	
	//Get each accelaration component as a unit vector
	//-----------------------------------------------------------------------------------
	
	//Direction that avoids all animals that are too close
	protected Vector3D getAvoidDir(ArrayList<Animal> avoid){
		
		Vector3D aDir = new Vector3D();
		
		for(Animal a: avoid){
			
			Vector3D dir = position.minus(a.position);
			aDir = aDir.plus(dir.multiply(1/(dir.mag()*dir.mag())));
			
		}
		
		return aDir.normalize();
		
	}
	
	//Direction to the center of the animals in this cluster
	protected Vector3D getCentDir(ArrayList<HerdAnimal> neigh){
		
		Vector3D cDir = new Vector3D();
		
		for(HerdAnimal h: neigh){
			
			Vector3D toCenter = h.position.minus(position);
			cDir = cDir.plus(toCenter);
			
		}
		
		return cDir.multiply(neigh.size()).normalize();
		
	}
	
	//Direction tomatch the velocity of the animals in this cluster
	protected Vector3D getMatchDir(ArrayList<HerdAnimal> neigh){
		
		Vector3D mDir = new Vector3D();
		
		for(HerdAnimal h: neigh){
			
			Vector3D toMatch = h.velocity.minus(velocity);
			mDir = mDir.plus(toMatch);
			
		}
		
		return mDir.multiply(neigh.size()).normalize();
		
	}
	
	//Direction to next waypoint
	protected Vector3D goTo(Vector3D way) {
			
		Vector3D toWaypoint = way.minus(position);
		return toWaypoint.normalize();
			
	}
	
	//----------------------------------------------------------------------------

	//Serialization methods
	//----------------------------------------------------------------------------
	
	/*private void writeObject(ObjectOutputStream stream)
	        throws IOException{
		
		stream.writeObject(position);
		
	}
	
	private void readObject(ObjectInputStream stream)
	        throws IOException, ClassNotFoundException{
		
		position = (Vector3D)stream.readObject();
		
	}*/
	
	//----------------------------------------------------------------------------
	
}

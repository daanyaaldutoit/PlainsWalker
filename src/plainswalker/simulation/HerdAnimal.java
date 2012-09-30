//Particle class modeling a herd animal

package plainswalker.simulation;

import java.util.ArrayList;
import java.util.LinkedList;

import plainswalker.GUI.Grid;

public class HerdAnimal extends Animal{
	
	private static final long serialVersionUID = 1L;
	
	public static int avoidRad = 50;
	public static int neighbourRad = 100;
	protected int herdIndex;
	private float accumulator = 1f;
	private float maxVel = 1.5f;
	protected transient LinkedList<AStar.Node> aStarRoute;
	
	//Instatiate with a pos and herd number
	public HerdAnimal(Vector3D p, int index){
		
		position = p;
		velocity = new Vector3D();
		accelaration = new Vector3D();
		herdIndex = index;
		
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
			route = goTo(s);
		
		//Adjust by component strength between 0 and 1
		avoid = avoid.multiply(0.9f);
		centering = centering.multiply(0.8f);
		matching = matching.multiply(0.1f);
		route = route.multiply(0.5f);
		
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
		
		for(int i = 0; i < 9; ++i){
		for(Animal a: s.herds[i].anims)
			if(!a.equals(this)){
				
				if(shouldAvoid(a)){//avoidance	
					
					avoid.add(a);
					
					}
				if(i == herdIndex && position.distance(a.position) <= neighbourRad){//neighbours
					
					neighbours.add((HerdAnimal) a);
					
				}
				
			}
		
		for(Animal a: s.packs[i].preds){
			
			if(shouldAvoid(a))
				avoid.add(a);
			
		}
		
		}
		
	}

	public int getARad() {return avoidRad;}
	
	public int getIndex(){return herdIndex;}
	
	protected boolean shouldAvoid(Animal a){
		
		return position.distance(a.position) <= a.getARad();
		
	}
	
	//Get each accelaration component as a unit vector
	//-----------------------------------------------------------------------------------
	
	//Direction that avoids all animals that are too close
	protected Vector3D getAvoidDir(ArrayList<Animal> avoid){
		
		Vector3D aDir = new Vector3D();
		
		for(Animal a: avoid){
			
			int weight = 1;
			Vector3D dir = position.minus(a.position);
			if(a instanceof Predator)
				weight = 10;
			aDir = aDir.plus(dir.multiply(weight/(dir.mag()*dir.mag())));
			
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
	
	//Direction to next astar point
	protected Vector3D goTo(Simulation s){
		
		//attempt to fetch next point
		if(!aStarRoute.isEmpty()){
		
			Vector3D aStarCur = new Vector3D(aStarRoute.peek().x*Grid.blockSize, aStarRoute.peek().y*Grid.blockSize, 0);
			
			//if at destination, pop
			if(aStarCur.distance(position) <= 15)
				aStarRoute.pop();
			else if(aStarRoute.size() > 1 && aStarCur.distance(position) > 35)
				aStarRoute = s.astar.getPath((int)position.x/Grid.blockSize, (int)position.y/Grid.blockSize
						, (int)s.herds[herdIndex].route.peek().x/Grid.blockSize, (int)s.herds[herdIndex].route.peek().y/Grid.blockSize);
			
		}
		
		//move to point
		if(aStarRoute != null && !aStarRoute.isEmpty()){
			
			Vector3D toWaypoint = new Vector3D(aStarRoute.peek().x*Grid.blockSize, aStarRoute.peek().y*Grid.blockSize, 0).minus(position);
			return toWaypoint.normalize();
			
		}
		
		return new Vector3D();
			
	}
	
}

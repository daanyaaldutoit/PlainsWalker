//Particle class modeling a herd animal

package plainswalker.simulation;

public class HerdAnimal extends Animal{
	
	public static int avoidRad = 100;
	public static int neighbourRad = 250;
	protected int herdIndex;
	private float accumulator = 0.01f;
	private float maxVelocity = 1;
	
	public HerdAnimal(Vector3D p){
		
		position = p;
		velocity = new Vector3D();
		accelaration = new Vector3D();
		herdIndex = 0;
		
	}

	public void update(float dt, Simulation s) {
		
		accelaration = getAccel(s);
		goTo(new Vector3D(800, 800, 0));
		
		if(accelaration.mag() > accumulator)		//if over max accel
			accelaration = accelaration.multiply(accumulator/accelaration.mag());
		
		velocity = velocity.plus( accelaration.multiply(dt) );
		
		if(velocity.mag() > maxVelocity)
			velocity = velocity.multiply(maxVelocity/velocity.mag());
		
		position = position.plus( velocity.multiply(dt));
		
	}
	
	//Adjust accel towards waypoint
	private void goTo(Vector3D way) {
		
		Vector3D toWaypoint = way.minus(position);
		Vector3D uToWaypoint = toWaypoint.multiply(1/toWaypoint.mag());
		accelaration = accelaration.plus(uToWaypoint.multiply((float)way.distance(position)/50));
		
	}

	//Calculates weights of herding behavioural accelarations
	private Vector3D getAccel(Simulation s){
		
		Vector3D clusCenter = new Vector3D();
		int numInClus = 0;
		Vector3D clusVelocity = new Vector3D();
		Vector3D ac = new Vector3D();
		for(Animal a: s.herds[herdIndex].anims)
			if(!a.equals(this)){
				
				if(position.distance(a.position) <= a.getARad()){//avoidance	
					
					Vector3D dir = position.minus(a.position);	
					Vector3D uDir = dir.multiply(1/dir.mag());
					ac = ac.plus(uDir.multiply(600/(float)position.distanceSq(a.position)));
					
					}
				if(position.distance(a.position) <= neighbourRad){
					
					clusCenter = clusCenter.plus(a.position);
					clusVelocity = clusVelocity.plus(a.velocity);
					++numInClus;
					
				}
				
			}
			
		if(numInClus > 0){
		
			clusCenter = clusCenter.multiply(1/numInClus);
			clusVelocity = clusVelocity.multiply(1/numInClus);
			
			ac = ac.plus(clusVelocity.minus(velocity));
			
			Vector3D toCenter = clusCenter.minus(position);
			Vector3D uToCenter = toCenter.multiply(1/toCenter.mag());
			ac = ac.plus(uToCenter.multiply((float)position.distance(clusCenter)/400));
			
		}
		
		return ac;
		
	}

	@Override
	public int getARad() {
		return avoidRad;
	}

}

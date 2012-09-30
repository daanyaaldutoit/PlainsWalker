//Particle class modelling a Predator
//Daanyaal du Toit
//16 September 2012

package plainswalker.simulation;

public class Predator extends Animal{

	private static final long serialVersionUID = 1L;
	
	public static int avoidRad = 100;
	protected int packIndex;
	private float accumulator = 1f;
	private float maxVel = 1.5f;
	
	public Predator(Vector3D p, int index){
		
		position = p;
		velocity = new Vector3D();
		accelaration = new Vector3D();
		packIndex = index;
		
	}

	@Override
	public void update(float dt, Simulation s) {
		
		if(s.packs[packIndex].route.size() > 0)
			accelaration  = goTo(s.packs[packIndex].route.getFirst());
		velocity = velocity.plus(accelaration.multiply(dt));
		if(velocity.mag() > maxVel)
			velocity = velocity.multiply(maxVel/velocity.mag());
		position = position.plus(velocity.multiply(dt));

	}

	protected Vector3D goTo(Vector3D way) {
		
		Vector3D toWaypoint = way.minus(position);
		return toWaypoint.normalize();
			
	}
	
	@Override
	public int getARad() {
		return avoidRad;
	}
	
	public int getIndex(){return packIndex;}

}

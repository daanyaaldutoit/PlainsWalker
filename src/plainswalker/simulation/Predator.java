//Particle class modelling a Predator
//Daanyaal du Toit
//16 September 2012

package plainswalker.simulation;

public class Predator extends Animal{

	private static final long serialVersionUID = 1L;
	
	public static int avoidRad = 100;
	
	public Predator(Vector3D p){
		
		position = p;
		velocity = new Vector3D();
		accelaration = new Vector3D();
		
	}

	@Override
	public void update(float dt, Simulation s) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getARad() {
		return avoidRad;
	}

}

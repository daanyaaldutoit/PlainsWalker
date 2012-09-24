//Abstract superclass for all animals that can change during the simulation

package plainswalker.simulation;

import java.io.Serializable;

public abstract class Animal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Vector3D position;
	protected Vector3D velocity;
	protected Vector3D accelaration;
	
	//Changes component based on timestep
	public abstract void update(float dt, Simulation s);
	
	public Vector3D getPosition(){ return position;}
	
	public abstract int getARad();
	
}

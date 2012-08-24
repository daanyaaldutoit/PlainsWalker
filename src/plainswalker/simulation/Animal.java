//Abstract superclass for all animals that can change during the simulation

package plainswalker.simulation;

public abstract class Animal{

	Vector3D position;
	Vector3D velocity;
	Vector3D accelaration;
	
	//Changes component based on timestep
	public abstract void update(float dt, Simulation s);
	
	public abstract int getARad();
	
}

//Particle class modeling a herd animal

package plainswalker.simulation;

import plainswalker.GUI.HerdAnimalPlacer;

public class HerdAnimal extends Animal{
	
	public static int avoidRad = 100;
	public static int neighbourRad = 250;
	private HerdAnimalPlacer placer;
	
	public HerdAnimal(Vector3D p, HerdAnimalPlacer pl){
		
		position = p;
		velocity = new Vector3D();
		accelaration = new Vector3D();
		
		placer = pl;
		
	}

	public void update(float dt, Simulation s) {
		
		accelaration = getAccel(s);
		velocity = velocity.plus( accelaration.multiply(dt) );
		position = position.plus( velocity.multiply(dt));
		placer.setLocation(Math.round(position.x),Math.round(position.y));
		
	}
	
	private Vector3D getAccel(Simulation s){
		
		Vector3D clusCenter = new Vector3D();
		int numInClus = 0;
		Vector3D clusVelocity = new Vector3D();
		Vector3D ac = new Vector3D();
		for(Animal a: s.anims)
			if(!a.equals(this)){
				
				if(position.distance(a.position) <= a.getARad()){//avoidance	
					
					Vector3D dir = position.minus(a.position);	
					Vector3D uDir = dir.multiply(1/dir.mag());
					ac = ac.plus(uDir.multiply(1/(float)position.distanceSq(a.position)));
					
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
			ac = ac.plus(clusCenter.minus(position));
			
		}
		
		return ac;
		
	}

	@Override
	public int getARad() {
		return avoidRad;
	}

}

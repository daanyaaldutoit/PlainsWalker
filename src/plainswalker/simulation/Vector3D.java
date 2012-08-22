//A simple vector class for calculations

package plainswalker.simulation;

public class Vector3D {
	
	protected float [] values;
	
	//Constructors
	public Vector3D(){
		
		values = new float[3];
		
	}
	
	public Vector3D(float x, float y, float z){
		
		values = new float[3];
		values[0] = x;
		values[1] = y;
		values[2] = z;
		
	}
	
	public Vector3D(float[] xyz){
		
		values = new float[3];
		values[0] = xyz[0];
		values[1] = xyz[1];
		values[2] = xyz[2];
		
	}
	
	//Get magnitude
	public float mag(){
		
		return (float)Math.sqrt(Math.pow(values[0], 2) + Math.pow(values[1], 2) + Math.pow(values[2], 2) );
		
	}
	
	

}

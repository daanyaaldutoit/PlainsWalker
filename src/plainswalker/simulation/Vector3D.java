//A simple vector class for calculations

package plainswalker.simulation;

public class Vector3D {
	
	//Coordinate components
	protected float x;
	protected float y;
	protected float z;
	
	//Constructors
	public Vector3D(){
		
		x = 0; y = 0; z = 0;
		
	}
	
	public Vector3D(float x, float y, float z){
		
		this.x = x;
		this.y = y;
		this.z = z;
		
	}
	
	//Get magnitude
	public float mag(){
		
		return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2) );
		
	}
	
	//Arithmetic operations
	//-------------------------------------------------------------------------------
	public Vector3D plus(Vector3D v){
		
		return new Vector3D( x+v.x, y+v.y, z+v.z);
		
	}
	
	public Vector3D minus(Vector3D v){
		
		return new Vector3D( x-v.x, y-v.y, z-v.z);
		
	}
	
	public Vector3D multiply(float scalar){
		
		return new Vector3D( scalar*x, scalar*y, scalar*z);
		
	}
	
	public double distanceSq(Vector3D vec){
		
		return Math.pow(x-vec.x, 2)+Math.pow(y-vec.y, 2)+Math.pow(z-vec.z, 2);
		
	}
	
	public double distance(Vector3D vec){
		
		return Math.sqrt(distanceSq(vec));
		
	}
	
	//--------------------------------------------------------
	
	//Coordinate getters
	public float getX(){return x;}
	
	public float getY(){return y;}

}

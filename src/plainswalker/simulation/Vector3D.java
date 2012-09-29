//A simple vector class for calculations

package plainswalker.simulation;

import java.awt.geom.Point2D;

public class Vector3D extends Point2D.Float{
	
	private static final long serialVersionUID = 1L;
	public float z;
	
	//Constructors
	public Vector3D(){
		
		super();
		z = 0;
		
	}
	
	public Vector3D(float x, float y, float z){
		
		super(x, y);
		this.z = z;
		
	}
	
	//Get magnitude
	public float mag(){
		
		return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2) );
		
	}
	
	//Get unit vector
	public Vector3D normalize(){
		
		if(x == 0 && y == 0 && z == 0)
			return this;
		else
			return this.multiply(1/mag());
		
	}
	
	//Arithmetic operations
	//-------------------------------------------------------------------------------
	public Vector3D plus(Vector3D v){
		
		return new Vector3D(x+v.x, y+v.y, z+v.z);
		
	}
	
	public Vector3D minus(Vector3D v){
		
		return new Vector3D(x-v.x, y-v.y, z-v.z);
		
	}
	
	public Vector3D multiply(float scalar){
		
		return new Vector3D(scalar*x, scalar*y, scalar*z);
		
	}
	
	public float dot(Vector3D v){
		
		return x*v.x + y*v.y + z*v.z;
		
	}
	
	public double distanceSq(Vector3D vec){
		
		return super.distanceSq(vec)+Math.pow(z-vec.z, 2);
		
	}
	
	public double distance(Vector3D vec){
		
		return Math.sqrt(distanceSq(vec));
		
	}
	
	//--------------------------------------------------------

	public String toString(){
		
		return "(" + x + ", " + y + ", " + z + ")";
		
	}
	
	public boolean equals(Object o){
		
		if(o instanceof Vector3D)
			return(((Vector3D) o).x == x && ((Vector3D) o).y == y && ((Vector3D) o).z == z);
		else
			return false;
		
	}
	
}

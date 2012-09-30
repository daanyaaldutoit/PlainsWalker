package plainswalker.simulation;

public class Waypoint extends Vector3D {

	private static final long serialVersionUID = 1L;
	protected int wayIndex;
	
	public Waypoint() {}

	public Waypoint(float x, float y, float z, int index) {
		super(x, y, z);
		wayIndex = index;
	}
	
	public int getIndex(){return wayIndex;}

}

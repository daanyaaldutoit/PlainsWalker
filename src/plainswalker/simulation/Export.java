// Export: given a Simulation, writes to file the location data of each animal at each timestep
package plainswalker.simulation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Export {
	Simulation sim;
	
	// constructor
	public Export(Simulation s){
		sim = s;
	}
	
	// write to file
	public void writeToFile(String filename) throws IOException{
		// open a file for writing
		PrintWriter out = new PrintWriter(new FileWriter(filename)); 
		
		// go through frames in the simulation;
		// output data in the format: e x y z i j k n
		// e := animal ID (e.g. "prey_1" or "pred_5")
		// x y z := position
		// i j k := direction (leave at 0 0 0 for now; not using it)
		// n := timestep number (times this by ten for smoothness in SoftImage rendering)
		
		// close file
		out.close();
	}
}

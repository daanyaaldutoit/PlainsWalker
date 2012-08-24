// HEIGHTMAP CLASS:
// loads a new heightmap from a file/restores a saved heightmap/saves heightmap
// 
package plainswalker.simulation;

import java.io.*;

public class HeightMap {
	// height grid: 2D array
	double[][] heightgrid;
	int length = 100, width = 100;
	
	// default constructor
	public HeightMap(){
		heightgrid = new double[length][width];
		for (int i=0; i<100; i++)
			for (int j=0; j<100; j++)
				heightgrid[i][j] = 0;
	}
	
	// constructor which takes in string filename
	public HeightMap(){
		
	}
	
	// read in from file
	public void readFile(){
		
	}
	
	// load binary file
	
	// save as binary file
	
	// get the height at a point with bilinear interpolation

}

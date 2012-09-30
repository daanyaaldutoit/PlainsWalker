// HEIGHTMAP CLASS:
// loads a new heightmap from a file/restores a saved heightmap/saves heightmap

package plainswalker.simulation;

import java.io.*;
import java.awt.Point;


public class HeightMap{
	// height grid: 2D array
	private double[][] heightgrid;
	private int length = 100, width = 100;
	private float xstep, ystep;
	protected double lowest = Double.POSITIVE_INFINITY, highest = Double.NEGATIVE_INFINITY, range = 0;
	
	// constructor: takes in string filename of a text heightmap
	public HeightMap(String filename) throws FileNotFoundException, IOException{
		try {
			// read in the file (which is one line) and split on " "
			BufferedReader input = new BufferedReader(new FileReader(filename));
			String[] tokens = input.readLine().split(" ");
		
			// assign the first two values to the width & length
			width = Integer.parseInt(tokens[0]);
			length = Integer.parseInt(tokens[1]);
			
			// instantiate array
			heightgrid = new double[width][length];
			
			// populate array
			for (int w = 0; w<width; w++){
				for (int l = 0; l<length; l++){
					heightgrid[w][l] = Double.parseDouble(tokens[2+w+l*width]);
					
					// check for high/low points
					if (heightgrid[w][l] > highest)
						highest = heightgrid[w][l];
					if (heightgrid[w][l] < lowest)
						lowest = heightgrid[w][l];
				}
			}
			
			// set the range (to be used for colour gradient)
			range = highest - lowest;
		}
		catch (FileNotFoundException e){
			System.out.println("(zomg) OH NOES: " + e.getMessage());
			throw(e);
		}
		catch (IOException e){
			System.out.println("(zomg) OH NOES: " + e.getMessage());
			throw(e);
		}
	}
		
	// create a more finally grained grid with linear interpolation
	protected double[][] createTerrain(int w, int l){
		xstep = w/((float)(width)-1);
		ystep = l/((float)(length)-1);
			
		double[][] terrain = new double[l][w];
		
		// loop through the pixels in the image
		for (int x = 0; x < w; x++){
			for (int y = 0; y < l; y++){
				// to get the correct rotation of the image
				terrain[l-y-1][w-x-1] = getInterpolatedHeight(x,y);
			}
		}
		return terrain;
	}
	
	// get the height at a point with bilinear interpolation:
	// finds the closest known x and y points on either side of the desired point
	// and uses the bilinear interpolation formula to find the correct height,
	// assuming a straight line between the known points
	private double getInterpolatedHeight(xValue, yValue){
		// interpolated point
		double ih;
		
		// if the point is on a grid intersection
		if (xValue % xstep == 0 && yValue % ystep == 0){
			ih = heightgrid[(int)(xValue/xstep)][(int)(yValue/ystep)];
		}
		// if the point is on a vertical gridline
		else if (xValue % xstep == 0){
			int y1 = (int)Math.floor(yValue/ystep);
			int y2 = (int)Math.ceil(yValue/ystep);
			float y = yValue/ystep;
			
			// INTERPOLATE over y
			ih = ((y2-y)*heightgrid[(int)(xValue/xstep)][y1]/(y2-y1)) + ((y-y1)*heightgrid[(int)(xValue/xstep)][y2]/(y2-y1));
		}
		// if the point is on a horizontal gridline
		else if (yValue % ystep == 0){
			int x1 = (int)Math.floor(xValue/xstep);
			int x2 = (int)Math.ceil(xValue/xstep);
			float x = xValue/xstep;
			
			// INTERPOLATE over x
			ih = (((x2-x)*heightgrid[x1][(int)(yValue/ystep)])/(x2-x1)) + (((x-x1)*heightgrid[x2][(int)(yValue/ystep)])/(x2-x1));
		}
		// if the point is somewhere in a gridblock
		else{
			double xy1Height;
			double xy2Height;
			
			int x1 = (int)Math.floor(xValue/xstep);
			int x2 = (int)Math.ceil(xValue/xstep);
			float x = xValue/xstep;
			int y1 = (int)Math.floor(yValue/ystep);
			int y2 = (int)Math.ceil(yValue/ystep);
			float y = yValue/ystep;
			
			// INTERPOLATE over y1
			xy1Height = ((x2-x)*heightgrid[x1][y1]/(x2-x1)) + ((x-x1)*heightgrid[x2][y1]/(x2-x1));
			// INTERPOLATE over y2
			xy2Height = ((x2-x)*heightgrid[x1][y2]/(x2-x1)) + ((x-x1)*heightgrid[x2][y2]/(x2-x1));
			// INTERPOLATE over xValue
			ih = ((y2-y)*xy1Height/(y2-y1)) + ((y-y1)*xy2Height/(y2-y1));
		}
		
		return ih;
	}
	
	// accessor for length
	public int getLength(){
		return length;
	}
	
	// accessor for width
	public int getWidth(){
		return width;
	}
	
	// print out the HeightMap
	public String toString(){
		StringBuffer returnstr = new StringBuffer();
		for (int i=0; i<width; i++){
			System.out.println("got to row " + i);
			for (int j=0; j<length; j++){
				returnstr.append(heightgrid[i][j] + " ");
			}
			returnstr.append("\n");
		}		
		System.out.println("TOSTRING!");
		return new String(returnstr);			
	}
}

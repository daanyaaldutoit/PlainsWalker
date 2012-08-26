// HEIGHTMAP CLASS:
// loads a new heightmap from a file/restores a saved heightmap/saves heightmap

package plainswalker.simulation;

import java.io.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class HeightMap{
	// height grid: 2D array
	double[][] heightgrid;
	int length = 100, width = 100;
	
	/*// FOR TESTING: REMOVE LATER
	public static void main(String[] args) {
		//HeightMap a = new HeightMap("GC2.map");
		//System.out.println(a);
		
		HeightMap a = new HeightMap("test.map");
		System.out.println("Interpolated Height at (60,60) = (2, 2): " + a.getInterpolatedHeight(new Point(60, 60)));
		System.out.println("Interpolated Height at (60,123) = (2, 4.1): " + a.getInterpolatedHeight(new Point(60, 123)));
		System.out.println("Interpolated Height at (123,60) = (4.1, 2): " + a.getInterpolatedHeight(new Point(123, 60)));
		System.out.println("Interpolated Height at (123,123) = (4.1, 4.1): " + a.getInterpolatedHeight(new Point(123, 123)));
	}*/
	
	// default constructor
	public HeightMap(){
		heightgrid = new double[length][width];
		for (int i=0; i<100; i++)
			for (int j=0; j<100; j++)
				heightgrid[i][j] = 0;
	}
	
	// constructor which takes in string filename: to be used more often
	public HeightMap(String filename) throws FileNotFoundException, IOException{
		readFile(filename);
	}
	
	
	// read in from file
	public void readFile(String infile) throws IOException, FileNotFoundException{
		try {
			// read in the file (which is one line) and split on " "
			BufferedReader input = new BufferedReader(new FileReader(infile));
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
				}
			}
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
		
	// draw to buffered image
	private BufferedImage drawBufferedImage(int w, int h){
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		// get a graphics2D object to draw onto the image
		Graphics2D imageCreator = bi.createGraphics();
		imageCreator.setBackground(Color.WHITE);
		imageCreator.clearRect(0, 0, w, h);
		imageCreator.setColor(Color.BLACK);
		
		// SOMEHOW?? fill the buffered image with the relevant colour based on interpolated height of the point
		
		return bi;
	}
	
	// get buffered image
	public BufferedImage getBufferedImage(int w, int h){
		return drawBufferedImage(w,h);
	}
	
	// get the height at a point with bilinear interpolation
	public double getInterpolatedHeight(Point p){
		// interpolated point
		double ih;
				
		// if the point is on a grid intersection
		if (p.x % 30 == 0 && p.y % 30 == 0){
			ih = heightgrid[p.x/30][p.y/30];
		}
		// if the point is on a vertical gridline
		else if (p.x % 30 == 0){
			int y1 = (int)Math.floor(p.y/30f);
			int y2 = (int)Math.ceil(p.y/30f);
			float y = p.y/30f;
			
			// INTERPOLATE over y
			ih = ((y2-y)*heightgrid[p.x/30][y1]/(y2-y1)) + ((y-y1)*heightgrid[p.x/30][y2]/(y2-y1));
		}
		// if the point is on a horizontal gridline
		else if (p.y % 30 == 0){
			int x1 = (int)Math.floor(p.x/30f);
			int x2 = (int)Math.ceil(p.x/30f);
			float x = p.x/30f;
			
			// INTERPOLATE over x
			ih = (((x2-x)*heightgrid[x1][p.y/30])/(x2-x1)) + (((x-x1)*heightgrid[x2][p.y/30])/(x2-x1));
		}
		// if the point is somewhere in a gridblock
		else{
			double xy1Height;
			double xy2Height;
			
			int x1 = (int)Math.floor(p.x/30f);
			int x2 = (int)Math.ceil(p.x/30f);
			float x = p.x/30f;
			int y1 = (int)Math.floor(p.y/30f);
			int y2 = (int)Math.ceil(p.y/30f);
			float y = p.y/30f;
			
			// INTERPOLATE over y1
			xy1Height = ((x2-x)*heightgrid[x1][y1]/(x2-x1)) + ((x-x1)*heightgrid[x2][y1]/(x2-x1));
			// INTERPOLATE over y2
			xy2Height = ((x2-x)*heightgrid[x1][y2]/(x2-x1)) + ((x-x1)*heightgrid[x2][y2]/(x2-x1));
			// INTERPOLATE over p.x
			ih = ((y2-y)*xy1Height/(y2-y1)) + ((y-y1)*xy2Height/(y2-y1));
		}
		
		return ih;
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

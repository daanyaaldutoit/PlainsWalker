// HEIGHTMAP CLASS:
// loads a new heightmap from a file/restores a saved heightmap/saves heightmap

package plainswalker.simulation;

import java.io.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HeightMap{
	// height grid: 2D array
	double[][] heightgrid;
	int length = 100, width = 100;
	float xstep, ystep;
	double lowest = Double.POSITIVE_INFINITY, highest = Double.NEGATIVE_INFINITY, range = 0;
	
	// FOR TESTING: REMOVE LATER
	/*public static void main(String[] args) throws FileNotFoundException, IOException {
		HeightMap a = new HeightMap("GC3.map");
		a.saveBufferedImage(a.getBufferedImage(1000, 1000), "GC3");
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
		
	// draw to buffered image
	private BufferedImage drawBufferedImage(int w, int l){
		xstep = w/((float)(width)-1);
		ystep = l/((float)(length)-1);
			
		BufferedImage bi = new BufferedImage(w, l, BufferedImage.TYPE_BYTE_GRAY);
		
		// get a graphics2D object to draw onto the image
		Graphics2D imageCreator = bi.createGraphics();
		
		// loop through the pixels in the image
		for (int x = 0; x < w; x++){
			for (int y = 0; y < l; y++){
				// colour in that pixel with the relevant height data
				double currHeight = getInterpolatedHeight(new Point(x,y));
				float heightPercent = (float)((currHeight-lowest)/range);
				// @TODO: add colour gradient (maybe low saturation for visibility?)
				imageCreator.setColor(new Color(heightPercent, heightPercent, heightPercent));
				imageCreator.fillRect(l-y, w-x, 1, 1); // to get the correct rotation of the image
			}
		}
		
		//System.out.println("DONE with buffered image");
		
		return bi;
	}
	
	// get buffered image
	public BufferedImage getBufferedImage(int w, int h){
		return drawBufferedImage(w,h);
	}
	
	// save buffered image -- takes in an image and a filename (NOTE: without extension)
	public void saveBufferedImage(BufferedImage bi, String filename) throws FileNotFoundException, IOException{
		File outputfile = new File(filename+".png");
		ImageIO.write(bi, "png", outputfile);
	}
	
	// get the height at a point with bilinear interpolation
	public double getInterpolatedHeight(Point p){
		// interpolated point
		double ih;
		
		// if the point is on a grid intersection
		if (p.x % xstep == 0 && p.y % ystep == 0){
			ih = heightgrid[(int)(p.x/xstep)][(int)(p.y/ystep)];
		}
		// if the point is on a vertical gridline
		else if (p.x % xstep == 0){
			int y1 = (int)Math.floor(p.y/ystep);
			int y2 = (int)Math.ceil(p.y/ystep);
			float y = p.y/ystep;
			
			// INTERPOLATE over y
			ih = ((y2-y)*heightgrid[(int)(p.x/xstep)][y1]/(y2-y1)) + ((y-y1)*heightgrid[(int)(p.x/xstep)][y2]/(y2-y1));
		}
		// if the point is on a horizontal gridline
		else if (p.y % ystep == 0){
			int x1 = (int)Math.floor(p.x/xstep);
			int x2 = (int)Math.ceil(p.x/xstep);
			float x = p.x/xstep;
			
			// INTERPOLATE over x
			ih = (((x2-x)*heightgrid[x1][(int)(p.y/ystep)])/(x2-x1)) + (((x-x1)*heightgrid[x2][(int)(p.y/ystep)])/(x2-x1));
		}
		// if the point is somewhere in a gridblock
		else{
			double xy1Height;
			double xy2Height;
			
			int x1 = (int)Math.floor(p.x/xstep);
			int x2 = (int)Math.ceil(p.x/xstep);
			float x = p.x/xstep;
			int y1 = (int)Math.floor(p.y/ystep);
			int y2 = (int)Math.ceil(p.y/ystep);
			float y = p.y/ystep;
			
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
			for (int j=0; j<length; j++){
				returnstr.append(heightgrid[i][j] + " ");
			}
			returnstr.append("\n");
		}
		return new String(returnstr);			
	}
	
	//Getters for width and height
	
	public int getWidth(){return width;}
	
	public int getLength(){return length;}
}

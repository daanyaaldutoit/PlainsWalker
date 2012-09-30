// virtual representation of the world's terrain
// constructed from a heightmap using bilinear interpolation

package plainswalker.simulation;

import java.io.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Terrain implements Serializable{

	private static final long serialVersionUID = 1L;
	protected HeightMap heights;
	protected double[][] landscape;
	protected int width, length;
	double highest, lowest, range;
	
	// FOR TESTING: REMOVE LATER
	/*public static void main(String[] args) throws FileNotFoundException, IOException {
		Terrain t = new Terrain("GC3.map", 1000, 1000);
		t.saveBufferedImage(t.getBufferedImage(), "GC3");
	}*/
	
	// constructor -- takes in a heightmap, and a width and length
	// the terrain will have as many unit steps as specified by length and width
	public Terrain(HeightMap h, int w, int l) throws FileNotFoundException, IOException {
		heights = h;
		width = w;
		length = l;
		
		landscape = heights.createTerrain(width, length);
		
		highest = heights.highest;
		lowest = heights.lowest; 
		range = heights.range;
	}
	
	// constructor -- takes in a string filename (to construct a heightmap), and a width and length
	// the terrain will have as many unit steps as specified by length and width
	public Terrain(String filename, int w, int l) throws FileNotFoundException, IOException {

		this(new HeightMap(filename),w,l);

	}
	
	// accessor for heights
	public double getHeight(int x, int y){
		return landscape[x][y];
	}
	
	// draw the terrain to buffered image
	private BufferedImage drawBufferedImage(){
		BufferedImage bi = new BufferedImage(width, length, BufferedImage.TYPE_BYTE_GRAY);
		
		// get a graphics2D object to draw onto the image
		Graphics2D imageCreator = bi.createGraphics();
		
		// loop through the pixels in the image
		for (int x = 0; x < width; x++){
			for (int y = 0; y < length; y++){
				// colour in that pixel with the relevant height data
				double currHeight = landscape[x][y];
				float heightPercent = (float)((currHeight-lowest)/range);
				
				// coloured heightmap using overflow of the RGB values
				/*float red = 0, blue = 0; 	// initialize blue & green to zero 
				float green = heightPercent;
				if (green > 1){ 	// colour overflow spills into the next color
					red = green-1; 
					green = 1;
					if (red > 1){
						blue = red - 1;
						red = 1;
						if (blue > 1){
							blue = 1;
						}
					}
				}
				imageCreator.setColor(new Color(red, green, blue));*/
				
				imageCreator.setColor(new Color(heightPercent, heightPercent, heightPercent));
				imageCreator.fillRect(x, y, 1, 1);
			}
		}
		
		//System.out.println("DONE creating buffered image");
		
		return bi;
	}
	
	// get buffered image - returns a graphical representation of the terrain
	public BufferedImage getBufferedImage(){
		return drawBufferedImage();
	}
	
	// save buffered image -- takes in an image and a filename (NOTE: without extension)
	public void saveBufferedImage(BufferedImage bi, String filename) throws FileNotFoundException, IOException{
		File outputfile = new File(filename+".png");
		ImageIO.write(bi, "png", outputfile);
	}
	
	// accessor for length
	public int getLength(){
		return length;
	}
	
	// accessor for width
	public int getWidth(){
		return width;
	}
	
}

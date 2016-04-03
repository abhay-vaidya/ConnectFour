package team.abhayumar.connect;

import java.util.Arrays;

/**
 * Class to represent bitmap pixel data.
 * @author Abhay, Umar
 * @version 1.0
 */
public class Bitmap {
	
	// Initialize variables
	public int width, height;
	public int[] pixels;
	
	
	/**
	 * Default contstructor.
	 * 
	 * @param w  the width of the image
	 * @param h  the height of the image
	 */
	public Bitmap(int w, int h) {
		this.width = w;
		this.height = h;
		this.pixels = new int[this.width * this.height];
	}
	
	/**
	 * Fills the pixel array with black.
	 */
	public void clear() {
		Arrays.fill(this.pixels, 0);
	}
	
	/**
	 * Fills the pixel array with the specified color.
	 * 
	 * @param color  the color to fill the pixel data with in integer form
	 */
	public void fill(int color) {
		Arrays.fill(this.pixels, color);
	}
	
	/**
	 * Renders the incoming Bitmap object to this Bitmap.
	 * 
	 * @param bmp  the Bitmap to use as a source
	 * @param x  the x-coordinate to place the incoming Bitmap
	 * @param y  the y-coordinate to place the incoming Bitmap
	 */
	public void render(Bitmap bmp, int x, int y) {
		int xStart = x;
		int xEnd = x + bmp.width;
		int yStart = y;
		int yEnd = y + bmp.height;
		
		// Image bounds checking
		if (xStart < 0) xStart = 0;
		if (xEnd > width) xEnd = width;
		if (yStart < 0) yStart = 0;
		if (yEnd > height) yEnd = height;
		
		int widthResult = xEnd - xStart;
		
		/* Retrieve color data
		 * SOURCE: Kevin Yang (MrDeathJockey)
		 * URL: https://youtu.be/JXpKc7UAQRU?t=12m39s
		 */
		for (int i=yStart; i<yEnd; ++i) {  // Rows
			int tp = i * this.width + xStart;
			int sp = (i - y) * bmp.width + (xStart - x);
			tp -= sp;
			for(int j=sp; j<sp + widthResult; ++j) {  // Columns
				int color = bmp.pixels[j];
				if (color < 0) pixels[tp + j] = color;
			}
		}
	}
	
}
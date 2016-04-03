package team.abhayumar.connect;

import java.util.Arrays;

/**
 * Class to represent bitmap pixel data.
 * @author Abhay, Umar
 * @version 1.0
 */
public class Bitmap {
	
	// Initialize variables
	private int width, height;
	private int[] pixels;
	
	
	/**
	 * Default constructor.
	 * 
	 * @param w  the width of the image
	 * @param h  the height of the image
	 */
	public Bitmap(int w, int h) {
		this.setWidth(w);
		this.setHeight(h);
		this.setPixels(new int[this.getWidth() * this.getHeight()]);
	}
	
	/**
	 * Fills the pixel array with black.
	 */
	public void clear() {
		Arrays.fill(this.getPixels(), 0);
	}
	
	/**
	 * Fills the pixel array with the specified color.
	 * 
	 * @param color  the color to fill the pixel data with in integer form
	 */
	public void fill(int color) {
		Arrays.fill(this.getPixels(), color);
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
		int xEnd = x + bmp.getWidth();
		int yStart = y;
		int yEnd = y + bmp.getHeight();
		
		// Image bounds checking
		if (xStart < 0) xStart = 0;
		if (xEnd > getWidth()) xEnd = getWidth();
		if (yStart < 0) yStart = 0;
		if (yEnd > getHeight()) yEnd = getHeight();
		
		int widthResult = xEnd - xStart;
		
		/* Retrieve color data
		 * SOURCE: Kevin Yang (MrDeathJockey)
		 * URL: https://youtu.be/JXpKc7UAQRU?t=12m39s
		 */
		for (int i=yStart; i<yEnd; ++i) {  // Rows
			int tp = i * this.getWidth() + xStart;
			int sp = (i - y) * bmp.getWidth() + (xStart - x);
			tp -= sp;
			for(int j=sp; j<sp + widthResult; ++j) {  // Columns
				int color = bmp.getPixels()[j];
				if (color < 0) getPixels()[tp + j] = color;
			}
		}
	}

	/**
	 * Gets the pixel data.
	 * @return  integer array of pixel data
	 */
	public int[] getPixels() {
		return pixels;
	}

	/**
	 * Sets the pixel data.
	 * @param pixels  the pixel data to use as a source.
	 */
	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
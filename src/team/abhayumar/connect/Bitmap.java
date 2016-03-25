package team.abhayumar.connect;

import java.util.Arrays;

public class Bitmap {
	
	private int width, height;
	public int[] pixels;
	
	public Bitmap(int w, int h) {
		this.width = w;
		this.height = h;
		setPixels(new int[this.width * this.height]);
	}
	
	public void clear() {
		Arrays.fill(getPixels(), 0);
	}
	
	public void render(Bitmap bmp, int x, int y) {
		int xStart = x;
		int xEnd = x + bmp.width;
		int yStart = y;
		int yEnd = y + bmp.height;
		
		// Image bounds checking
		if (xStart < 0) xStart = 0;
		if (xEnd > bmp.width) xEnd = bmp.width;
		if (yStart < 0) yStart = 0;
		if (yEnd > bmp.height) yEnd = bmp.height;
		
		int widthResult = xEnd - xStart;
		
		// Retrieve color data
		for (int i=yStart; i<yEnd; i++) {  // Rows
			int tp = i * this.width + xStart;
			int sp = (i - y) * bmp.width + (xStart - x);
			for(int j=sp; j<sp + widthResult; j++) {  // Columns
				int color = bmp.getPixels()[j];
				if (color < 0) pixels[xStart + i * this.width + j] = color;
			}
		}
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}
	
}
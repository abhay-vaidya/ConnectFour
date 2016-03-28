package team.abhayumar.connect;

import java.util.Arrays;

public class Bitmap {
	
	public int width, height;
	public int[] pixels;
	
	public Bitmap(int w, int h) {
		this.width = w;
		this.height = h;
		this.pixels = new int[this.width * this.height];
	}
	
	public void clear() {
		Arrays.fill(this.pixels, 0);
	}
	
	public void fill(int color) {
		Arrays.fill(this.pixels, color);
	}
	
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
		
		// Retrieve color data
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
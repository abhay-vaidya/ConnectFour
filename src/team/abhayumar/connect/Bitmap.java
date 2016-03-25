package team.abhayumar.connect;

import java.util.Arrays;

public class Bitmap {
	
	private int width, height;
	private int[] pixels;
	
	public Bitmap() {
		
	}
	
	public Bitmap(int w, int h) {
		width = w;
		height = h;
		setPixels(new int[width * height]);
	}
	
	public void clear() {
		Arrays.fill(getPixels(), 0);
	}
	
	public void render(Bitmap bmp, int x, int y) {
		int x1 = x;
		int x2 = x + bmp.width;
		int y1 = y;
		int y2 = y + bmp.height;
		
		if (x1 < 0) x1 = 0;
		if (x2 > bmp.width) x2 = bmp.width;
		if (y1 < 0) y1 = 0;
		if (y2 > bmp.height) y2 = bmp.height;
		
		
		
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}
	
}
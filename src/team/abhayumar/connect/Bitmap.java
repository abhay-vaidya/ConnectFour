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
		pixels = new int[width * height];
	}
	
	public void clear() {
		Arrays.fill(pixels, 0);
	}
	
	public void draw(Bitmap bmp, int x, int y) {
		
	}
	
}
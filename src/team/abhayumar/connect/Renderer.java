package team.abhayumar.connect;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Renderer extends Bitmap {

	public BufferedImage image;
	
	public Renderer(int w, int h) {
		super(w,h);
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}
	
	public void draw(Bitmap bmp, int x, int y) {
		
	}
	
}

package team.abhayumar.connect;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Renderer extends Bitmap {

	private BufferedImage image;
	
	public Renderer(int w, int h) {
		super(w,h);
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		setPixels(((DataBufferInt)image.getRaster().getDataBuffer()).getData());
	}
	
	
	
	public BufferedImage getImage() {
		return this.image;
	}
	
}

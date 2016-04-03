package team.abhayumar.connect;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Represents final screen to be displayed every time interval
 * 
 * @author Abhay, Umar
 * @version 1.0
 */
public class Screen extends Bitmap {
	
	// Initialize variable
	public BufferedImage image;

	/**
	 * Generates screen image.
	 * 
	 * @param w  width of image
	 * @param h  height of image
	 */
	public Screen(int w, int h) {
		super(w, h);
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}
}

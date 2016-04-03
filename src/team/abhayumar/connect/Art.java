package team.abhayumar.connect;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class to manage art assets that can be rendered on to the Screen.
 * 
 * @author Abhay, Umar
 * @version 1.0
 */
public class Art extends Bitmap {
	
	// Initialize variables
	private BufferedImage image = null;
	
	
	/**
	 * Default constructor for Art object.
	 * 
	 * @param path  the relative file path to the image file
	 * @param w  the width of the image
	 * @param h  the height of the image
	 */
	public Art(String path, int w, int h) {
		super(w, h);

		// Read file and store pixel data
		File inFile = new File(path);
		try {
			image = ImageIO.read(inFile);
			setPixels(image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

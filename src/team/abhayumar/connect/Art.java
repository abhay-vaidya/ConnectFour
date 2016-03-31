package team.abhayumar.connect;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Art extends Bitmap {
	
	private BufferedImage image = null;

	public Art(String path, int w, int h) {
		super(w, h);
		
		File inFile = new File(path);
		try {
			image = ImageIO.read(inFile);
			pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

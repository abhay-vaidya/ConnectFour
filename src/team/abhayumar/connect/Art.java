package team.abhayumar.connect;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Art extends Bitmap {
	
	private BufferedImage image;

	public Art(String path, int w, int h) {
		super(w, h);
		
		File inFile = new File(path);
		try {
			image = ImageIO.read(inFile);
			
			// link BufferedImage data to pixels[]
			pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

package team.abhayumar.connect;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Class to handle game sound.
 * 
 * @author Abhay, Umar
 * @version 1.0
 */
public class Sound implements Runnable {

	// Initialize variables
	private Clip clip;
	private Thread thread;
	private Float volume = -10.0f;

	/**
	 * Constructor to load sound clip.
	 * 
	 * @param path  the path to sound file
	 * @param loop  whether the sound should loop or not
	 */
	public Sound(String path, boolean loop) {
		
		// Load clip
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(path)));

			if (loop == true) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			
			// Set initial volume
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

		// Create new thread
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Toggles volume mute.
	 */
	public void toggleVolume() {
		if (volume == -10.0f) {
			volume = -80.0f;
		} else if (volume == -80.0f) {
			volume = -10.0f;
		}
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(volume);
	}

	/**
	 * Runs sound thread.
	 */
	public void run() {
		try {
			clip.start();
			thread.join();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}

package team.abhayumar.connect;

import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements Runnable {

	private Clip clip;
	private Thread thread;
	private Float volume = -10.0f;

	public Sound(String path, boolean loop) {

		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(path)));

			if (loop == true) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

		thread = new Thread(this);
		thread.start();
	}

	public void toggleVolume() {
		if (volume == -10.0f) {
			volume = -80.0f;
		} else if (volume == -80.0f) {
			volume = -10.0f;
		}
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(volume);
	}

	@Override
	public void run() {
		try {
			clip.start();
			thread.join();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}

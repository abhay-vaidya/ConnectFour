package team.abhayumar.connect;

import java.applet.AudioClip;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {
	
	private static AudioClip click;
	private static File bg = new File("test.wav");
	private static File piece = new File("drop.wav");
	
	public void playBackgroundMusic() {
		  try {
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(bg));
		   clip.start();
		   Thread.sleep(clip.getMicrosecondLength());
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }
	}
	
	public static void playPiece() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(piece));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}

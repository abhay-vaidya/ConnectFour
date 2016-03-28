package team.abhayumar.connect;

import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound implements Runnable{
	
	private Clip clip;
	private Thread thread;
	   
	public Sound(String path){
				
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(path)));			
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		thread = new Thread(this);
		thread.start();
	}
	

	@Override
	public void run() {
		try {
			clip.start();
			thread.join();
			//Thread.sleep(clip.getMicrosecondLength());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
	
	
}

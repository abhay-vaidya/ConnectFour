package team.abhayumar.connect;

import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound implements Runnable{
	
	private static Clip bgMusic;
	private static Clip pieceSound;

	   
	public Sound(){
		try {
			bgMusic = AudioSystem.getClip();
			bgMusic.open(AudioSystem.getAudioInputStream(new File("test.wav")));
			pieceSound = AudioSystem.getClip();
			pieceSound.open(AudioSystem.getAudioInputStream(new File("drop.wav")));
			
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
	}
	
	public static void playBackgroundMusic() {
		  try {

		   bgMusic.start();
		   Thread.sleep(bgMusic.getMicrosecondLength());
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }
	}
	
	public static void playPiece() {
		try {
			pieceSound.start();
			Thread.sleep(pieceSound.getMicrosecondLength());

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
            Sound.playBackgroundMusic();
            
            if (Connect4.pieceDropped){
            	System.out.println("HERE");
            	Sound.playPiece();
            	pieceDropped = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
}

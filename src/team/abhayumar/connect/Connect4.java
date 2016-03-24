package team.abhayumar.connect;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Connect4 extends Canvas implements Runnable {
	
	private static int WIDTH = 700;
	private static int HEIGHT = 600;
	private static String TITLE = "Connect Four";
	private static boolean running = false;
	
	private JFrame frame; 
	
	public Connect4() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		init();
	}
	
	public void start() {
		running = true;
		new Thread(this).start();
	}
	
	public void stop() {
		running = false;
	}	
	
	public void render() {
		
	}
	
	public void tick() {
		
	}
	

	@Override
	public void run() {
		System.out.println("Hello World");
	}
	
	public void init() {
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Connect4 game = new Connect4();
		Dimension dimension = new Dimension(WIDTH, HEIGHT);
		game.setMaximumSize(dimension);
		game.start();
	}

}

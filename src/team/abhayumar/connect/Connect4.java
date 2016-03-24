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
	private Renderer renderer;
	
	public Connect4() {
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
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		renderer = new Renderer();
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}

}

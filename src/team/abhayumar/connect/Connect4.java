package team.abhayumar.connect;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Connect4 extends Canvas implements Runnable {
	
	private static int WIDTH = 700;
	private static int HEIGHT = 600;
	private static String TITLE = "Connect Four";
	private static boolean running = false;
	
	private Thread thread;
	private JFrame frame;
	private Renderer renderer;
	
	public Connect4() {
		init();
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void render() {
		
	}
	
	public void tick() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(255,0,0));
		g.fillRect(0, 0, 100, 100);
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

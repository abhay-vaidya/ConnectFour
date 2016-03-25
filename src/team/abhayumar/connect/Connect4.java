package team.abhayumar.connect;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Connect4 extends Canvas implements Runnable, MouseListener {
	
	private static int WIDTH = 700;
	private static int HEIGHT = 600;
	private static String TITLE = "Connect Four";
	private static boolean running = false;
	
	private Thread thread;
	private JFrame frame;
	private Renderer renderer;
	
	public Connect4() {
		Game game = new Game();
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
			e.printStackTrace();
		}
	}	
	
	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		final double TARGET_FPS = 60.0;
		final double ns = 1000000000.0 / TARGET_FPS; 
		double delta = 0;
		   
		while (running){
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				update();
				delta--;
			}
			render();
		}
		stop();
	}
				
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(3);
			requestFocus();  // VERY IMPORTANT
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		// Draw objects
		Bitmap test = new Bitmap(100,100);
		test.clear();
		renderer.setPixels(test.getPixels());
		
		// Render
		g.drawImage(renderer.getImage(), 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
		bs.show();
	}
	
	public void update() {
		
	}
	
	public void init() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		renderer = new Renderer(WIDTH, HEIGHT);
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

package team.abhayumar.connect;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Connect4 extends Canvas implements Runnable, MouseListener {
	
	private static int WIDTH = 900;
	private static int HEIGHT = 800;
	private static int BOARD_WIDTH = 700;
	private static int BOARD_HEIGHT = 600;
	private static String TITLE = "Connect Four";
	private static boolean running = false;
	private Game game;
	
	private Thread thread;
	private JFrame frame;
	private Renderer renderer;
	
	public Connect4() {
		
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
		
		init();
		
		long lastTime = System.nanoTime();
		final double TARGET_FPS = 60.0;
		final double ns = 1000000000.0 / TARGET_FPS; 
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		   
		while (running){
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer >= 1000) {
	            timer += 1000;
	            frame.setTitle(TITLE + " | ups: " + updates + " fps: " + frames);
	            updates = 0;
	            frames = 0;
	        }
		}
		stop();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			createBufferStrategy(3);
			requestFocus();  // VERY IMPORTANT
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		// Draw objects
		renderer.clear();
		renderer.fill(0x2196F3);
		
		// Draw the pieces
		Art p1 = new Art("p1.png", 100 ,100);
		Art p2 = new Art("p2.png", 100, 100);
		for (int i=0; i<game.ROWS; i++) {
			for (int j=0; j<game.COLUMNS; j++) {
				if (game.getCell(i, j) == 1) {
					renderer.render(p1, j*100+100, i*100+100);
				}
				else if (game.getCell(i, j) == 2){
					renderer.render(p2, j*100+100, i*100+100);
				}
			}
		}
		
		// Render
		g.drawImage(renderer.image, 0, 0, WIDTH, HEIGHT, null);
		// Draw grid
		g.setColor(new Color(0x1976D2));
		for (int i=0; i<8; i++) {
			for (int j=0; j<7; j++) {
				g.drawRect(100, 100, i*100, j*100);
			}
		}
		g.dispose();
		bs.show();
	}
	
	public void update() {
		// Game logic goes here
	}
	
	public void init() {
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setSize(new Dimension(WIDTH, HEIGHT));
		
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		
		renderer = new Renderer(WIDTH, HEIGHT);
		game = new Game();
		game.runGame("Umar", "Abhay");
		
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x >= 100 && x <= 800 && y >= 100 && y <=700){
			int row = Math.round((y - 100)/ 100);
			int column = Math.round((x - 100) / 100);
			
			/*  BOARD DEBUG CODE
			for (int i=0; i<game.ROWS; i++) {
				for (int j=0; j<game.COLUMNS; j++) {
					System.out.print(game.getCell(i, j));
				}
				System.out.print('\n');
			}
			System.out.print('\n');
			System.out.println(String.valueOf(row) + " " + String.valueOf(column));
			*/
			
			int status = game.updateBoard(row, column);
			if (status == 0) {
				game.nextTurn();
			}
			if(game.hasWinner() == true){
				game.clearBoard();
				System.out.println(game.turn.getName() + " has won!");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

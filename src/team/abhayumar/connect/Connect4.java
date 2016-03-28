package team.abhayumar.connect;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Connect4 extends Canvas implements Runnable, MouseListener, MouseMotionListener {
	
	private static int WIDTH = 900;
	private static int HEIGHT = 800;
	private static int BOARD_WIDTH = 700;
	private static int BOARD_HEIGHT = 600;
	private static String TITLE = "Connect Four";
	private static boolean running = false;
	private static int highlightColumn = -1;
	private Game game;
	private Thread thread;
	private JFrame frame;
	private Screen screen;
	private enum STATE {
			MAIN_MENU,
			INSTRUCTION,
			SETUP,
			GAME,
	};
	private STATE State;
	private Sound bgMusic;
	
	private Art p1 = new Art("p1.png", 100 ,100);
	private Art p2 = new Art("p2.png", 100, 100);
	private Art bg = new Art("gameboard.png", 768, 680);
	private Art highlight = new Art("highlight.png", 100, 600);
	private Art turnP1 = new Art("turnP1.png", 100, 60);
	private Art turnP2 = new Art("turnP2.png", 100, 60);
	private Art newGameBtn = new Art("newgamebutton.png", 300 ,100);
	private Art instructionsBtn = new Art("instructionsbutton.png", 300 ,100);
	private Art instructionsText = new Art("instructions.png", 900 ,800);
	private Art volumeOn = new Art("volumeOn.png", 48, 48);
	private Art volumeOff = new Art("volumeOff.png", 48, 48);
	private boolean isVolumeOn = true;
	
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
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus(); // VERY IMPORTANT
			return;
		}
		Graphics g = bs.getDrawGraphics();

		// Draw objects
		screen.clear();

		if (State == STATE.MAIN_MENU) {
			
			screen.fill(0xFFC107);
			if (isVolumeOn){
				screen.render(volumeOn, 827, 27);
			} else {
				screen.render(volumeOff, 827, 27);
			}
			
			screen.render(newGameBtn, (WIDTH - newGameBtn.width) / 2, (HEIGHT - newGameBtn.height) / 3);
			screen.render(instructionsBtn, (WIDTH - instructionsBtn.width) / 2,
					(HEIGHT - instructionsBtn.height) / (3 * 2));

		} else if (State == STATE.INSTRUCTION) {
			screen.fill(0xFFC107);
			screen.render(instructionsText, 0, 0);

		} else if (State == STATE.SETUP) {
			screen.fill(0x009688);
			
		} else if (State == STATE.GAME) {
			screen.fill(0x2196F3);

			// Draw the pieces
			screen.render(bg, (WIDTH - bg.width) / 2, (HEIGHT - bg.height) / 2);
			if (game.turn.getID() == 1) {
				screen.render(turnP1, 0, 0);
			}

			else if (game.turn.getID() == 2) {
				screen.render(turnP2, 0, 0);
			}

			if (highlightColumn != -1) {
				screen.render(highlight, highlightColumn * 100 + 100, 100);
			}
			
			if (isVolumeOn){
				screen.render(volumeOn, 827, 27);
			} else {
				screen.render(volumeOff, 827, 27);
			}

			for (int i = 0; i < game.ROWS; i++) {
				for (int j = 0; j < game.COLUMNS; j++) {
					if (game.getCell(i, j) == 1) {
						screen.render(p1, j * 100 + 100, i * 100 + 100);
					} else if (game.getCell(i, j) == 2) {
						screen.render(p2, j * 100 + 100, i * 100 + 100);
					}
				}
			}
		}

		// Render
		g.drawImage(screen.image, 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
		bs.show();
	}

	public void update() {
		//All animations
	}
	
	public void init() {
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setSize(new Dimension(WIDTH, HEIGHT));
		
		State = STATE.MAIN_MENU;
		
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		
		//PLAY BACKGROUND MUSIC
		bgMusic = new Sound("test.wav", true);
		//System.out.println("This is a test");
		
		screen = new Screen(WIDTH, HEIGHT);
		game = new Game();

		
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	private void setupDialog(){
		PlayerSetupPanel input = new PlayerSetupPanel();
		int option = JOptionPane.showConfirmDialog(null, input, "Player Information", JOptionPane.OK_CANCEL_OPTION);
		
		if (option == JOptionPane.OK_OPTION) {		
			String p1Name = input.getPlayerOneName();
			String p2Name = input.getPlayerTwoName();
			if (p1Name.isEmpty() || p2Name.isEmpty()) {
				JOptionPane.showMessageDialog(null,"Incomplete field");
				State = State.MAIN_MENU;
			} else {
				game.runGame(p1Name, p2Name);
				State = STATE.GAME;
			}				
		} else if (option == JOptionPane.CANCEL_OPTION) {
			State = State.MAIN_MENU;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if (State == STATE.MAIN_MENU) {
			if (x > 827 && y > 27 && x < 875 && y < 75){
				bgMusic.toggleVolume();
				if (isVolumeOn){
				isVolumeOn = false;
				} else{
					isVolumeOn = true;
				}
			}
			if(x > ((WIDTH-300)/2) && y > ((HEIGHT-100)/3) && x < ((WIDTH+300)/2) && y < ((HEIGHT+100)/3)){
				State = STATE.SETUP;
				setupDialog();
			}
			else if (x > ((WIDTH-300)/2) && y > ((HEIGHT/6)-100/3) && x < ((WIDTH+300)/2) && y < (HEIGHT/6)+100/3){
				State = STATE.INSTRUCTION;
			}
		
		} else if (State == STATE.INSTRUCTION) {
			if (x > 35 && y > 45 && x < 140 && y < 85){
				State = STATE.MAIN_MENU;
			}
			else if (x > 625 && y > 45 && x < 865 && y < 85){
				State = STATE.SETUP;
				setupDialog();
			}

		} else if (State == STATE.GAME) {
			if (x > 827 && y > 27 && x < 875 && y < 75){
				bgMusic.toggleVolume();
				if (isVolumeOn){
				isVolumeOn = false;
				} else{
					isVolumeOn = true;
				}
			}
			
			if (x >= 100 && x <= 800 && y >= 100 && y <= 700) {
				int row = Math.round((y - 100) / 100);
				int column = Math.round((x - 100) / 100);

				int status = game.updateBoard(row, column);
				
				if (status == 0) {
					new Sound("drop.wav", false);
					game.nextTurn();					
				}
				if (game.hasWinner()) {
					State = STATE.MAIN_MENU;
					game.clearBoard();
					System.out.println(game.turn.getName() + " has won!");
				} else if (game.hasDraw()) {
					State = STATE.MAIN_MENU;
					game.clearBoard();
					System.out.println("There is a draw!");
				}
			}
		}

	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (State == STATE.GAME) {
			if (x >= 100 && x <= 800 && y >= 100 && y <= 700) {
				int column = Math.round((x - 100) / 100);
				highlightColumn = column;
			}
			else {
				highlightColumn = -1;
			}

		}
	}

}

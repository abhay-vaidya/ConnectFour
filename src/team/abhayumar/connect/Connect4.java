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
import javax.swing.JLabel;
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
	private boolean isVolumeOn = true;
	private static int highlightColumn = -1;
	private Game game;
	private Thread thread;
	private JFrame frame;
	private Screen screen;
	private enum OUTCOME{
		WINNER_P1,
		WINNER_P2,
		DRAW
	}
	private enum STATE {
			MAIN_MENU,
			INSTRUCTION,
			SETUP,
			GAME,
			WINNER
	};
	private STATE State;
	private OUTCOME Outcome;
	private Sound bgMusic;
	
	private Art p1 = new Art("res/p1.png", 100 ,100);
	private Art p2 = new Art("res/p2.png", 100, 100);
	private Art bg = new Art("res/gameboard.png", 768, 680);
	private Art highlight = new Art("res/highlight.png", 100, 600);
	private Art turnP1 = new Art("res/turnP1.png", 100, 60);
	private Art turnP2 = new Art("res/turnP2.png", 100, 60);
	private Art newGameBtn = new Art("res/newgamebutton.png", 300 ,100);
	private Art instructionsBtn = new Art("res/instructionsbutton.png", 300 ,100);
	private Art instructionsText = new Art("res/instructions.png", 900 ,800);
	private Art winnerp1 = new Art("res/winnerp1.png", 900 ,800);
	private Art winnerp2 = new Art("res/winnerp2.png", 900 ,800);
	private Art draw = new Art("res/draw.png", 900 ,800);
	private Art logo = new Art("res/Logo.png", 765, 206);
	private Art volumeOn = new Art("res/volumeOn.png", 48, 48);
	private Art volumeOff = new Art("res/volumeOff.png", 48, 48);
	private Art nameCredit = new Art("res/namecredit.png", 493, 41);
	private Art home = new Art("res/home.png", 48, 48);
	private Art restart = new Art("res/restart.png", 48, 48);
	
	
	
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
				screen.render(volumeOn, 827, 20);
			} else {
				screen.render(volumeOff, 827, 20);
			}
			screen.render(logo, (WIDTH - logo.width)/2, 100);
			screen.render(nameCredit, (WIDTH - nameCredit.width)/2, 700);
			screen.render(newGameBtn, (WIDTH - newGameBtn.width) / 2, ((HEIGHT - newGameBtn.height) / 3)+150);
			screen.render(instructionsBtn, (WIDTH - instructionsBtn.width) / 2, ((HEIGHT - instructionsBtn.height) / 2)+150);

		} else if (State == STATE.INSTRUCTION) {
			screen.fill(0xFFC107);
			screen.render(instructionsText, 0, 0);

		} else if (State == STATE.SETUP) {
			screen.fill(0x009688);
			
		} else if (State == STATE.WINNER){
			if (Outcome == OUTCOME.WINNER_P1){
				screen.render(winnerp1, 0, 0);
			}
			else if (Outcome == OUTCOME.WINNER_P2){
				screen.render(winnerp2, 0, 0);
			}
			else if (Outcome == OUTCOME.DRAW){
				screen.render(draw, 0, 0);
			}
		}	
		
		else if (State == STATE.GAME) {
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
				screen.render(volumeOn, 827, 20);
			} else {
				screen.render(volumeOff, 827, 20);
			}
			
			// Home Icon
			screen.render(home, 760, 20);
			
			// Restart Icon
			screen.render(restart, 693, 20);

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
		bgMusic = new Sound("res/test.wav", true);
		
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
			if (x > 827 && y > 20 && x < 875 && y < 68){
				bgMusic.toggleVolume();
				if (isVolumeOn){
				isVolumeOn = false;
				} else{
					isVolumeOn = true;
				}
			}
			if(x > ((WIDTH-300)/2) && y > ((HEIGHT-100)/3)+150 && x < ((WIDTH+300)/2) && y < ((HEIGHT+100)/3)+150){
				new Sound("res/drop.wav", false);
				State = STATE.SETUP;
				setupDialog();
			}
			else if (x > ((WIDTH-300)/2) && y > ((HEIGHT-100)/2)+150 && x < ((WIDTH+300)/2) && y < ((HEIGHT+100)/2)+150){
				new Sound("res/drop.wav", false);
				State = STATE.INSTRUCTION;
			}
		
		} else if (State == STATE.INSTRUCTION) {
			if (x > 35 && y > 45 && x < 140 && y < 85){
				new Sound("res/drop.wav", false);
				State = STATE.MAIN_MENU;
			}
			else if (x > 625 && y > 45 && x < 865 && y < 85){
				new Sound("res/drop.wav", false);
				State = STATE.SETUP;
				setupDialog();
			}

		} else if (State == STATE.GAME) {
			
			// Volume toggle
			if (x > 827 && y > 20 && x < 875 && y < 68){
				bgMusic.toggleVolume();
				if (isVolumeOn){
				isVolumeOn = false;
				} else{
					isVolumeOn = true;
				}
			}
			
			// Main menu link
			if (x > 760 && y > 20 && x < 808 && y < 68){
				
				int option = JOptionPane.showConfirmDialog(null, new JLabel("Are you sure?"), "Quit Game", JOptionPane.OK_CANCEL_OPTION);
				
				if ( option == JOptionPane.OK_OPTION ) {
					game.clearBoard();
					State = STATE.MAIN_MENU;
				}
			}
			
			// Restart game link
			if (x > 693 && y > 20 && x < 741 && y < 68){
				
				int option = JOptionPane.showConfirmDialog(null, new JLabel("Are you sure?"), "Restart Game", JOptionPane.OK_CANCEL_OPTION);
				
				if ( option == JOptionPane.OK_OPTION ) {
					game.clearBoard();
				}
			}
			
			if (x >= 100 && x <= 800 && y >= 100 && y <= 700) {
				int row = Math.round((y - 100) / 100);
				int column = Math.round((x - 100) / 100);

				int status = game.updateBoard(row, column);
				
				if (game.hasWinner()) {
					
					// Pause game 
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					State = STATE.WINNER;
					
					if (game.turn.getID() == 1){
						Outcome = OUTCOME.WINNER_P1;
					}
					else if (game.turn.getID() == 2){
						Outcome = OUTCOME.WINNER_P2;
					}
					
					game.clearBoard();
					
				} else if (game.hasDraw()) {
					State = STATE.WINNER;
					Outcome = OUTCOME.DRAW;
					game.clearBoard();
				}
				
				else if (status == 0) {
					new Sound("res/drop.wav", false);
					game.nextTurn();					
				}

			}
		}
		
		else if (State == STATE.WINNER){
			if (x > 110 && y > 560 && x < 300 && y < 680){
				State = STATE.MAIN_MENU;
			}
			else if (x > 500 && y > 600 && x < 790 && y < 650){
				State = STATE.GAME;
				game.clearBoard();
			}
		}
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

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

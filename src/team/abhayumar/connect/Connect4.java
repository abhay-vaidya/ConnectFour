package team.abhayumar.connect;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import team.abhayumar.connect.Game.DIAGONAL;

@SuppressWarnings("serial")
public class Connect4 extends Canvas implements Runnable, MouseListener, MouseMotionListener {
	
	private Game game;
	private Thread thread;
	private JFrame frame;
	private Screen screen;
	
	private static int WIDTH = 900;
	private static int HEIGHT = 800;
	private static int BOARD_WIDTH = 700;
	private static int BOARD_HEIGHT = 600;
	private static String TITLE = "Connect Four";
	
	private static int highlightColumn = -1;
	private static int winningRow;
	private static int winningColumn;
	
	private static boolean running = false;
	private boolean isVolumeOn = true;
	private boolean shouldRender = false;
	
	private enum OUTCOME{
		WINNER_P1,
		WINNER_P2,
		DRAW
	}
	private OUTCOME Outcome;
	
	private enum STATE {
		MAIN_MENU,
		INSTRUCTION,
		GAME,
		WINNER
	};
	private STATE State;

	private Sound gameSound;
	
	private final long PERIOD = 1000L; // Adjust to suit timing
	private long lastTime = System.currentTimeMillis() - PERIOD;

	
	private ArrayList<Animation> pieces = new ArrayList<Animation>();
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
	private Art verticalLine = new Art("res/vertical line.png", 100, 400);
	private Art horizontalLine = new Art("res/horizontal line.png", 400, 100);
	private Art diagonalRight = new Art("res/diagonal line right.png", 400, 400);
	private Art diagonalLeft = new Art("res/diagonal line left.png", 400, 400);
	
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
	            frame.setTitle(TITLE + " | " + updates + " UPS | " + frames + " FPS" );
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
			requestFocus();
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
			screen.render(newGameBtn, (WIDTH - newGameBtn.width) / 2, 350);
			screen.render(instructionsBtn, (WIDTH - instructionsBtn.width) / 2, 475);

		} else if (State == STATE.INSTRUCTION) {
			screen.fill(0xFFC107);
			screen.render(instructionsText, 0, 0);

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
			
			
			// Draw board
			screen.render(bg, (WIDTH - bg.width) / 2, (HEIGHT - bg.height) / 2);
			
			// Draw player turn
			if (game.turn.getID() == 1) {
				screen.render(turnP1, 0, 0);
			}
			else if (game.turn.getID() == 2) {
				screen.render(turnP2, 0, 0);
			}
			
			// Highlight column
			if (highlightColumn != -1) {
				screen.render(highlight, highlightColumn * 100 + 100, 100);
			}
				
			// Toggle volume
			if (isVolumeOn){
				screen.render(volumeOn, 827, 20);
			} else {
				screen.render(volumeOff, 827, 20);
			}
			
			// Home Icon
			screen.render(home, 760, 20);
			
			// Restart Icon
			screen.render(restart, 693, 20);
			
			// Draw pieces
			for (int i = 0; i < pieces.size(); i++) {
				screen.render(pieces.get(i).getArt(), pieces.get(i).getX(), pieces.get(i).getY());
			}
			
			if (shouldRender) {

				// Vertical line
				if (game.hasVerticalWinner()) {
					screen.render(verticalLine, (game.winningColumn * 100), (game.winningRow * 100));
				}
				// Horizontal Line
				else if (game.hasHorizontalWinner()) {
					screen.render(horizontalLine, (game.winningColumn * 100), (game.winningRow * 100));
				}
				// Diagonal Line
				else if (game.hasDiagonalWinner()) {
					if (game.getDiagonalState() == DIAGONAL.TOP_LEFT) {
						screen.render(diagonalLeft, (game.winningColumn * 100), (game.winningRow * 100));
					} else if (game.getDiagonalState() == DIAGONAL.TOP_RIGHT) {
						screen.render(diagonalRight, (game.winningColumn * 100), (game.winningRow * 100));
					} else if (game.getDiagonalState() == DIAGONAL.BOTTOM_LEFT) {
						screen.render(diagonalRight, (game.winningColumn * 100), (game.winningRow * 100) - 300);
					} else if (game.getDiagonalState() == DIAGONAL.BOTTOM_RIGHT) {
						screen.render(diagonalLeft, (game.winningColumn * 100) - 300, (game.winningRow * 100) - 300);
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
		if (State == STATE.GAME) {
			for (int i = 0; i < pieces.size(); i++) {
				pieces.get(i).update();
			}

			if (shouldRenderWinLine()) {
				long thisTime = System.currentTimeMillis();
				if ((thisTime - lastTime) >= PERIOD) {
					lastTime = thisTime;
					shouldRender = true;
				}
			}
		}
	}
	
	public boolean shouldRenderWinLine() {
		int counter = 0;
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).isAnimationDone()) {
				counter++;
			}
			if (counter == pieces.size() && game.hasWinner()) {
				return true;
			}
		}
		return false;
	}
	
	public void init() {
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setSize(new Dimension(WIDTH, HEIGHT));
		
		State = STATE.MAIN_MENU;
		
		frame = new JFrame(TITLE);
		ImageIcon img = new ImageIcon("res/icon.png");
		frame.setIconImage(img.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		
		//PLAY BACKGROUND MUSIC
		gameSound = new Sound("res/test.wav", true);
		
		screen = new Screen(WIDTH, HEIGHT);
		game = new Game();

		
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		winningRow = Math.round(y/100);
		winningColumn = Math.round(x/100);
		
		if (State == STATE.MAIN_MENU) {
			if (x > 827 && y > 20 && x < 875 && y < 68){
				gameSound.toggleVolume();
				if (isVolumeOn){
				isVolumeOn = false;
				} else{
					isVolumeOn = true;
				}
			}
			if(x > ((WIDTH-300)/2) && y > 350 && x < ((WIDTH+300)/2) && y < 350+newGameBtn.height){
				new Sound("res/drop.wav", false);
				game.runGame();
				State = STATE.GAME;
			}
			else if (x > ((WIDTH-300)/2) && y > 475 && x < ((WIDTH+300)/2) && y < 475+instructionsBtn.height){
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
				game.runGame();
				State = STATE.GAME;
			}

		} else if (State == STATE.GAME) {
			
			// Volume toggle
			if (x > 827 && y > 20 && x < 875 && y < 68){
				gameSound.toggleVolume();
				if (isVolumeOn){
				isVolumeOn = false;
				} else{
					isVolumeOn = true;
				}
			}
			
			// Main menu link
			if (x > 760 && y > 20 && x < 808 && y < 68){
				new Sound("res/drop.wav", false);
				int option = JOptionPane.showConfirmDialog(null, new JLabel("Are you sure?"), "Quit Game", JOptionPane.OK_CANCEL_OPTION);
				
				if ( option == JOptionPane.OK_OPTION ) {
					game.reset();
					pieces.clear();
					State = STATE.MAIN_MENU;
				}
			}
			
			// Restart game link
			if (x > 693 && y > 20 && x < 741 && y < 68){
				new Sound("res/drop.wav", false);
				int option = JOptionPane.showConfirmDialog(null, new JLabel("Are you sure?"), "Restart Game", JOptionPane.OK_CANCEL_OPTION);
				
				if ( option == JOptionPane.OK_OPTION ) {
					game.reset();
					pieces.clear();
					shouldRender = false;
				}
			}
			
			// Within board bounds
			if (x >= 100 && x <= 800 && y >= 100 && y <= 700) {
				int row = Math.round((y - 100) / 100);
				int column = Math.round((x - 100) / 100);

				int player = game.turn.getID();
				int status = game.updateBoard(column);
				
				if (status != -1) {
					new Sound("res/drop.wav", false);
					
					if (player == 1) {
						pieces.add( new Animation(p1, column*100+100, 100, column*100+100, status*100+100) );
					} else if (player == 2) {
						pieces.add( new Animation(p2, column*100+100, 100, column*100+100, status*100+100) );
					}
					
					if (!game.hasWinner() && !game.hasDraw()) {
						game.nextTurn();
					}
				}
				
				if (game.hasWinner()) {
					// Pause game 
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					// Change state depending on winner
					State = STATE.WINNER;
					
					if (game.turn.getID() == 1){
						Outcome = OUTCOME.WINNER_P1;
					}
					else if (game.turn.getID() == 2){
						Outcome = OUTCOME.WINNER_P2;
					}
					
					// Reset game
					game.reset();
					pieces.clear();
					shouldRender = false;
					
				} else if (game.hasDraw()) {
					State = STATE.WINNER;
					Outcome = OUTCOME.DRAW;
					game.reset();
					pieces.clear();
					shouldRender = false;
				}

			}
		}
		
		else if (State == STATE.WINNER){
			if (x > 110 && y > 560 && x < 300 && y < 680){
				new Sound("res/drop.wav", false);
				State = STATE.MAIN_MENU;
			}
			else if (x > 500 && y > 600 && x < 790 && y < 650){
				new Sound("res/drop.wav", false);
				State = STATE.GAME;
				game.reset();
				pieces.clear();
				shouldRender = false;
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

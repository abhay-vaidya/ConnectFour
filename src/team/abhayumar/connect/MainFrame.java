package team.abhayumar.connect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.OverlayLayout;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private static Game newGame;
	
	// Panels to be merged
	private HashMap<String, C4Panel> panels = new HashMap<String, C4Panel>();
	// Colors
	private static Color[] colors = {null, new Color(255,0,0), new Color(255,255,0)};
	
	
	
	public MainFrame(String title){
		this.setTitle(title);
		
		// Create panels and store in Map for easy access
		panels.put("background", new C4Panel());
		panels.put("graphics", new C4Panel());
		

		// Create overlay panel to hold all other panels
		C4Panel mainMenuPanel = new C4Panel();
		mainMenuPanel.setLayout(new OverlayLayout(mainMenuPanel));
				
			
		// Board image
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("src/Connect4Board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		panels.get("graphics").add(picLabel);
		
		
		// Set transparency
		panels.get("graphics").setOpaque(false);

		// Background
		panels.get("background").setBackground(Color.decode("#FFE082"));
		panels.get("background").setPreferredSize(getMaximumSize());

		
		
		
		mainMenuPanel.add(panels.get("graphics"));
		mainMenuPanel.add(panels.get("background"));

		getContentPane().add(mainMenuPanel);
		
		
		
		// REMOVE THIS
		//switchTo("background");
		//switchBackground(new Player("test",2));
		
		while(true) {
			if (Game.needsUpdate) {
				newGame.initializeBoard();
			}
		}
		
		
	}
	
	/**
	 * Switches the active panel to the one specified
	 * @param key - the name of the panel (i.e. background, buttons, graphics)
	 */
	public void switchTo(String key) {
		if ( panels.containsKey(key) ) {
			for (String k : panels.keySet()) {
				panels.get(k).setVisible(false);
			}
			panels.get(key).setVisible(true);
		} else {
			// DEBUG message
			System.out.println("Invalid panel name!");
		}
	}
	
	/**
	 * Switches background color to that of current player
	 * @param player - Player whose turn it is
	 */
	public void switchBackground(Player player) {
		panels.get("background").setBackground(colors[player.getID()]);
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame("Connect Four");
		// ImageIcon img = new ImageIcon("");
		// frame.setIconImage(img.getImage());
		frame.pack();
		frame.setSize(700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		newGame = new Game();
	}
	

	
}
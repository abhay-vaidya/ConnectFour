package team.abhayumar.connect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	// Panels to be merged
	private HashMap<String, JPanel> panels = new HashMap<String, JPanel>();
	// Colors
	private static Color[] colors = {null, new Color(255,0,0), new Color(255,255,0)};
	
	public MainFrame(String title){
		this.setTitle(title);
		
		// Create panels and store in Map for easy access
		panels.put("background", new JPanel());
		panels.put("buttons", new JPanel());
		panels.put("graphics", new JPanel());
		
		
		// Set temporary frame layout
		getContentPane().setLayout(new BorderLayout());

		// Create overlay panel to hold all other panels
		JPanel mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new OverlayLayout(mainMenuPanel));
		
		
		// Temporary button layout
		GridBagLayout gbl_buttons = new GridBagLayout();
		gbl_buttons.columnWidths = new int[]{700, 0};
		gbl_buttons.rowHeights = new int[]{550, 0};
		gbl_buttons.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttons.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panels.get("buttons").setLayout(gbl_buttons);
		
		// Board image
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("src/Connect4Board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		panels.get("buttons").add(picLabel);
		
		
		// Set transparency
		panels.get("graphics").setOpaque(false);
		panels.get("buttons").setOpaque(false);

		// Background
		panels.get("background").setBackground(Color.decode("#FFE082"));
		panels.get("background").setPreferredSize(getMaximumSize());

		// Test button
		JButton btnTestButton = new JButton("TEST BUTTON");
		btnTestButton.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_btnTestButton = new GridBagConstraints();
		gbc_btnTestButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnTestButton.gridx = 0;
		gbc_btnTestButton.gridy = 0;
		panels.get("buttons").add(btnTestButton, gbc_btnTestButton);
		
		mainMenuPanel.add(panels.get("buttons"));
		mainMenuPanel.add(panels.get("graphics"));
		mainMenuPanel.add(panels.get("background"));

		getContentPane().add(mainMenuPanel);
		
		// REMOVE THIS
		switchTo("background");
		switchBackground(new Player("test",2));
		
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
	
}
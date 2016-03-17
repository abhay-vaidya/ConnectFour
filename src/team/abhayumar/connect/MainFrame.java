package team.abhayumar.connect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class MainFrame extends JFrame {
	public MainFrame(String title){
		super(title);
		
		// Set temporary frame layout
		getContentPane().setLayout(new BorderLayout());

		// Create overlay panel to hold all other panels
		JPanel mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new OverlayLayout(mainMenuPanel));
		
		// Panels to be merged
		JPanel background = new JPanel();
		JPanel buttons = new JPanel();
		JPanel graphics = new JPanel();
		
		// Temporary button layout
		GridBagLayout gbl_buttons = new GridBagLayout();
		gbl_buttons.columnWidths = new int[]{700, 0};
		gbl_buttons.rowHeights = new int[]{550, 0};
		gbl_buttons.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttons.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		buttons.setLayout(gbl_buttons);
		
		// Board image
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("src/Connect4Board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		graphics.add(picLabel);
		
		// Set transparency
		graphics.setOpaque(false);
		buttons.setOpaque(false);

		// Background
		background.setBackground(Color.decode("#FFE082"));
		background.setPreferredSize(getMaximumSize());

		// Test button
		JButton btnTestButton = new JButton("TEST BUTTON");
		btnTestButton.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_btnTestButton = new GridBagConstraints();
		gbc_btnTestButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnTestButton.gridx = 0;
		gbc_btnTestButton.gridy = 0;
		buttons.add(btnTestButton, gbc_btnTestButton);
		
		mainMenuPanel.add(buttons);
		mainMenuPanel.add(graphics);
		mainMenuPanel.add(background);

		getContentPane().add(mainMenuPanel);
	}
}
package team.abhayumar.connect;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.JButton;

public class MainFrame extends JFrame {
	public MainFrame(String title){
		super(title);
		
		// Set temporary frame layout
		getContentPane().setLayout(new BorderLayout());

		// Overlay panel to hold all other panels
		JPanel mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new OverlayLayout(mainMenuPanel));
		
		// Panels to be overlaid
		JPanel background = new JPanel();
		JPanel graphics = new JPanel();
		JPanel buttons = new JPanel();

		// Test item
		JButton btnTestButton = new JButton("TEST BUTTON");
		
		// Panel attributes
		buttons.setOpaque(false);
		buttons.add(btnTestButton);
		
		background.setBackground(Color.decode("#2196F3"));
		background.setPreferredSize(getMaximumSize());
		
		// Add all panels into frame
		mainMenuPanel.add(buttons);
		mainMenuPanel.add(background);
		getContentPane().add(mainMenuPanel);
	}
}
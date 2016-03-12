package team.abhayumar.connect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.JButton;
import java.awt.CardLayout;

public class MainFrame extends JFrame {
	public MainFrame(String title){
		super(title);
		
		getContentPane().setLayout(new BorderLayout());

		JPanel mainMenuPanel = new JPanel();
		JPanel buttons = new JPanel();
		JPanel background = new JPanel();
		JButton btnTestButton = new JButton("TEST BUTTON");
		
		mainMenuPanel.setLayout(new OverlayLayout(mainMenuPanel));

		buttons.setOpaque(false);
		buttons.add(btnTestButton);
		
		background.setBackground(Color.decode("#2196F3"));
		background.setPreferredSize(getMaximumSize());
		
		mainMenuPanel.add(buttons);
		mainMenuPanel.add(background);
		getContentPane().add(mainMenuPanel);

	}
}
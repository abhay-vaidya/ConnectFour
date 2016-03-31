package team.abhayumar.connect;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PlayerSetupPanel extends JPanel {
	
	private JTextField playerOneName = new JTextField(5);
	private JTextField playerTwoName = new JTextField(5);
	
	/**
	 * Default constructor
	 */
	public PlayerSetupPanel() {
		
		this.setLayout(new GridLayout(2, 1));
		this.add( new JLabel("Player One Name:") );
		this.add(playerOneName);
		this.add( new JLabel("Player Two Name:") );
		this.add(playerTwoName);
	}

	public String getPlayerOneName() {
		return this.playerOneName.getText();
	}

	public String getPlayerTwoName() {
		return this.playerTwoName.getText();
	}
}

package team.abhayumar.connect;
import javax.swing.*;

import team.abhayumar.connect.MainFrame;

public class ConnectDriver {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				MainFrame frame = new MainFrame("Connect Four");
				// ImageIcon img = new ImageIcon("");
				// frame.setIconImage(img.getImage());
				frame.pack();
				frame.setSize(700, 600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				
				Game newGame = new Game();
			}
		});
	}

}

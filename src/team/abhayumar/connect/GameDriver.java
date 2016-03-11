package team.abhayumar.connect;
import javax.swing.*;

import team.abhayumar.connect.MainFrame;

public class GameDriver {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JFrame frame = new MainFrame("Connect Four");
				// ImageIcon img = new ImageIcon("");
				// frame.setIconImage(img.getImage());
				frame.setSize(700, 700);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
			}
		});
	}

}

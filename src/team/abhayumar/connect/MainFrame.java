package team.abhayumar.connect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame(String title){
		super(title);
		
		getContentPane().setBackground(Color.decode("#2196F3"));
	}

public void paint(Graphics g) {
    g.setColor(Color.GRAY);
    g.fillRect (100, 100, 500, 500);
    getContentPane().setBackground(Color.decode("#2196F3"));
  }
}


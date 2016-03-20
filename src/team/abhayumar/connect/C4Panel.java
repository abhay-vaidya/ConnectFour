package team.abhayumar.connect;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class C4Panel extends JPanel implements MouseListener {
	
	public C4Panel() {
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("x:" + String.valueOf(e.getX()) + " y: " + String.valueOf(e.getY()));
		int x = e.getX();
		int y = e.getY();
		int row = Math.round(y / 100);
		int column = Math.round(x / 100);
		System.out.println(String.valueOf(row) + " " + String.valueOf(column));
		//MainFrame.newGame.updateBoard(row, column);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
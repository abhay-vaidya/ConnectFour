package team.abhayumar.connect;

public class Animation {
	
	private int curX;
	private int curY;
	private int endY;
	private double velocity = 0;
	private double gravity = 1;
	private Art art;
	
	public Animation(Art art, int startX, int startY, int endX, int endY) {
		this.art = art;
		this.curX = startX;
		this.curY = startY;
		this.endY = endY;
	}
	
	public void update() {
		
		
		int tempY = (int) Math.round(curY + velocity);
		
		if (tempY < endY) {
			curY = tempY;
			velocity += gravity;
		} else {
			curY = endY;
		}
	}
	
	public int getX() {
		return this.curX;
	}
	
	public int getY() {
		return this.curY;
	}

	public Art getArt() {
		return this.art;
	}

}

package team.abhayumar.connect;

public class Animation extends Art {
	
	private int curX;
	private int curY;
	private int endY;
	private double velocity = 0;
	private int gravity = 1;
	
	public Animation(String path, int w, int h, int startX, int startY, int endX, int endY) {
		super(path,w,h);
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
			gravity /= 6;
		}
	}
	
	public int getX() {
		return this.curX;
	}
	
	public int getY() {
		return this.curY;
	}

}

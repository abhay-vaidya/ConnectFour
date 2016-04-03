package team.abhayumar.connect;

/**
 * Class to manage animation of pieces.
 * 
 * @author Abhay, Umar
 * @version 1.0
 */
public class Animation {

	// Initialize variables
	private int curX;
	private int curY;
	private int endY;
	private double velocity = 0;
	private double gravity = 1;
	private Art art;

	
	/**
	 * Default constructor for Animation class.
	 * 
	 * @param art  Art object to animate
	 * @param startX  the starting x-coordinate of the Art object
	 * @param startY  the starting y-coordinate of the Art object
	 * @param endX  the ending x-coordinate of the Art object
	 * @param endY  the ending y-coordinate of the Art object
	 */
	public Animation(Art art, int startX, int startY, int endX, int endY) {
		this.art = art;
		this.curX = startX;
		this.curY = startY;
		this.endY = endY;
	}

	/**
	 * Checks whether the animation has completed (ie. the object reached its
	 * end position).
	 * 
	 * @return   true if the animation is done, false if it is still in progress
	 */
	public boolean isAnimationDone() {
		if (curY == endY) {
			return true;
		}
		return false;
	}

	/**
	 * Advances the animation.
	 */
	public void update() {
		int tempY = (int) Math.round(curY + velocity);
		if (tempY < endY) {
			curY = tempY;
			velocity += gravity;
		} else {
			curY = endY;
		}
	}

	/**
	 * Gets the x-coordinate of the Art object.
	 * 
	 * @return  the x-coordinate
	 */
	public int getX() {
		return this.curX;
	}

	/**
	 * Gets the y-coordinate of the Art object.
	 * 
	 * @return  the y-coordinate
	 */
	public int getY() {
		return this.curY;
	}

	/**
	 * Gets the Art object.
	 * 
	 * @return  the Art object
	 */
	public Art getArt() {
		return this.art;
	}

}

package team.abhayumar.connect;

/**
 * Class to handle players
 * 
 * @author Abhay, Umar
 * @version 1.0
 */
public class Player {

	// Initialize variables
	private int id;
	private int wins = 0;
	private int losses = 0;
	private int draws = 0;
	
	/**
	 * Constructor with name and ID.
	 * 
	 * @param id  the player's ID (1 or 2)
	 */
	public Player(int id) {
		this.id = id;
	}
	
	/**
	 * Constructor for possible file loading.
	 * 
	 * @param id  the player's ID (1 or 2)
	 * @param wins  the player's wins
	 * @param losses  the player's losses
	 * @param draws  the player's draws
	 */
	public Player(int id, int wins, int losses, int draws) {
		this.id = id;
		this.wins = wins;
		this.losses = losses;
		this.draws = draws;
	}
	
	/**
	 * Gets player's ID.
	 * 
	 * @return the player's ID
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Sets player's ID.
	 * 
	 * @param id  the new ID to set
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Gets player's wins.
	 * 
	 * @return wins  the player's # of wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Sets player's wins.
	 * 
	 * @param wins  the new # of wins to set
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * Gets player's losses.
	 * 
	 * @return losses  the player's # of losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * Sets player's losses.
	 * 
	 * @param losses  the new # of losses to set
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}

	/**
	 * Gets player's draws.
	 * 
	 * @return draws  the player's # of draws
	 */
	public int getDraws() {
		return draws;
	}

	/**
	 * Sets player's draws.
	 * 
	 * @param draws  the new # of draws to set
	 */
	public void setDraws(int draws) {
		this.draws = draws;
	}
	
	/**
	 * Override equals method.
	 * 
	 * @Override equals
	 */
	public boolean equals(Object obj) {
		Player p = (Player) obj;
		return p.getID() == this.getID();
	}
}

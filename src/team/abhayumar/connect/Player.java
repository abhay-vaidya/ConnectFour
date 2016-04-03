package team.abhayumar.connect;

public class Player {

	private int id;
	private int wins = 0;
	private int losses = 0;
	private int draws = 0;
	
	/**
	 * Constructor with name and ID
	 * @param name - Player name
	 * @param id - Player ID (1 or 2)
	 */
	public Player(int id) {
		this.id = id;
	}
	
	/**
	 * Constructor for possible file loading
	 * @param name - Player name
	 * @param id - Player ID (1 or 2)
	 * @param wins - Player's wins
	 * @param losses - Player's losses
	 * @param draws - Player's draws
	 */
	public Player(int id, int wins, int losses, int draws) {
		this.id = id;
		this.wins = wins;
		this.losses = losses;
		this.draws = draws;
	}
	
	/**
	 * Gets player's ID
	 * @return - The player's ID
	 */
	public int getID() {
		return id; 
	}
	
	/**
	 * Sets player's ID
	 * @param id - The new ID to set
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Gets player's wins
	 * @return wins - The player's # of wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Sets player's wins
	 * @param wins - The new # of wins to set
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * Gets player's losses
	 * @return losses - The player's # of losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * Sets player's losses
	 * @param losses - The new # of losses to set
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}

	/**
	 * Gets player's draws
	 * @return draws - The player's # of draws
	 */
	public int getDraws() {
		return draws;
	}

	/**
	 * Sets player's draws
	 * @param draws - The new # of draws to set
	 */
	public void setDraws(int draws) {
		this.draws = draws;
	}
	
	/**
	 * Override equals method
	 * @Override
	 */
	public boolean equals(Object obj) {
		Player p = (Player)obj;
		return p.getID() == this.getID();
	}
}

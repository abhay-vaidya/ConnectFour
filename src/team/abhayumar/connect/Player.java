package team.abhayumar.connect;

public class Player {

	private String name;
	private int id;
	private int wins = 0;
	private int losses = 0;
	private int draws = 0;
	
	public Player(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public Player(String name, int id, int wins, int losses, int draws) {
		this.name = name;
		this.id = id;
		this.wins = wins;
		this.losses = losses;
		this.draws = draws;
	}

	/**
	 * @return - The player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name - The new name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return - The player's ID
	 */
	public int getID() {
		return id; 
	}
	
	/**
	 * @param id - The new ID to set
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * @return - The player's # of wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * @param wins - The new # of wins to set
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * @return - The player's # of losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * @param losses - The new # of losses to set
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}

	/**
	 * @return - The player's # of draws
	 */
	public int getDraws() {
		return draws;
	}

	/**
	 * @param draws - The new # of draws to set
	 */
	public void setDraws(int draws) {
		this.draws = draws;
	}
	
	@Override
	public boolean equals(Object obj) {
		Player p = (Player)obj;
		return p.getID() == this.getID();
	}
	
	
	
}

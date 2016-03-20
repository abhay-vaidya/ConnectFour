package team.abhayumar.connect;

/**
 * Class to handle game execution
 * @author Abhay, Umar
 * @version 1.0
 */
public class Game {
	private Player playerOne;
	private Player playerTwo;
	public Player turn;
	private int board[][];
	
	/**
	 * Creates player object
	 * @param name - Name of player
	 */
	public void createPlayer(int player, String name){
		if (player == 1){
			playerOne = new Player(name);
		}
		else {
			playerTwo = new Player(name);
		}
	}
	
	/**
	 * Runs game with two player objects
	 * @param p1 - Player One
	 * @param p2 - Player Two
	 */
	public void runGame(String p1, String p2){
		createPlayer(1, p1);
		createPlayer(2, p2);
	}
	
	/**
	 * Updates board 2D array
	 * @param row - Row value
	 * @param col - Column value
	 */
	public void updateBoard(int row, int col){
		
	}
	
	/**
	 * Checks board 2D array for any winners
	 * @return
	 */
	public boolean hasWinner(){
		return false;
		
	}
	
	
}

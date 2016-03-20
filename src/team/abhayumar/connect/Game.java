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
	private int rows = 6;
	private int columns = 7;
	private int board[][] = new int[rows][columns];
	
	/**
	 * Empty constructor
	 */
	public Game(){
		
	}
	
	/**
	 * Creates player object
	 * @param name - Name of player
	 * @param id - ID of player (1 or 2)
	 */
	public void createPlayer(int id, String name){
		if (id == 1){
			playerOne = new Player(name, id);
		}
		else {
			playerTwo = new Player(name, id);
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
	 * Fills board array with zero/unoccupied values
	 */
	public void initializeBoard(){
		for (int i=0; i < rows; i++){
			for (int j=0; j < columns; j++){
				board[i][j] = 0;
			}
		}
	}
	
	/**
	 * Updates board 2D array
	 * @param row - Row value
	 * @param col - Column value
	 * @param player - Player to be entered
	 */
	public void updateBoard(int row, int col, Player player){
		board[row][col] = player.getID();
	}
	
	/**
	 * Checks board 2D array for any winners
	 * @return Boolean
	 */
	public boolean hasWinner(){
		return false;
		
	}
	
	
}

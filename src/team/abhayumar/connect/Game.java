package team.abhayumar.connect;

/**
 * Class to handle game execution
 * @author Abhay, Umar
 * @version 1.0
 */
public class Game {
	
	// Initialize variables
	private Player playerOne;
	private Player playerTwo;
	public Player turn = playerOne;
	public static final int ROWS = 6;
	public static final int COLUMNS = 7;
	private int board[][] = new int[ROWS][COLUMNS];
	
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
		for (int i = 0; i < ROWS; i++){
			for (int j = 0; j < COLUMNS; j++){
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
	
	public void nextTurn(){
		if (turn.equals(playerOne)){
			turn = playerTwo;
		}
		else if (turn.equals(playerTwo)){
			turn = playerOne;
		}
	}
	
	/**
	 * Checks board 2D array for any winners
	 * @return Boolean
	 */
	public boolean hasWinner(){
		boolean winner = false;
		for (int i = 0; i < ROWS; i ++) {
			for (int j = 0; j < COLUMNS; j ++) {
				// Check player one  
		    	if (board[i][j] == 1 && j < 4 && i < 3){
		    		// Check horizontal
		    		if(board[i][j+1] == 1 && board[i][j+2] == 1 && board[i][j+3] == 1){
		    			winner = true;
		    		}
		    		// Check vertical
		    		if(board[i+1][j] == 1 && board[i+2][j] == 1 && board[i+3][j] == 1){
		    			winner = true;
		    		}
		    		// Check diagonal
		    		if(board[i+1][j+1] == 1 && board[i+2][j+2] == 1 && board[i+3][j+3] == 1){
		    			winner = true;
		    		}
		    	} 	
				// Check player two  
		    	if (board[i][j] == 2 && j < 4 && i < 3){
		    		// Check horizontal
		    		if(board[i][j+1] == 2 && board[i][j+2] == 2 && board[i][j+3] == 2){
		    			winner = true;
		    		}
		    		// Check vertical
		    		if(board[i+1][j] == 2 && board[i+2][j] == 2 && board[i+3][j] == 2){
		    			winner = true;
		    		}
		    		// Check diagonal
		    		if(board[i+1][j+1] == 2 && board[i+2][j+2] == 2 && board[i+3][j+3] == 2){
		    			winner = true;
		    		}
		    	} 
		    }		
		}
		return winner;
	}
}
	
	


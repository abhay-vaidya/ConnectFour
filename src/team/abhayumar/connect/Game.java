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
	public Player turn;
	int lowestRow;
	public static final int ROWS = 6;
	public static final int COLUMNS = 7;
	public int winningRow;
	public int winningColumn;
	private int board[][] = new int[ROWS][COLUMNS];
	public enum DIAGONAL{
		TOP_LEFT,
		TOP_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_RIGHT
	}
	private DIAGONAL Diagonal;
	
	
	/**
	 * Constructor
	 */
	public Game(){
		for (int i = 0; i < ROWS; i++){
			for (int j = 0; j < COLUMNS; j++){
				board[i][j] = 0;
			}
		}
	}
	
	public DIAGONAL getDiagonalState(){
		return Diagonal;
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
		turn = playerOne;
	}
	
	/**
	 * Fills board array with zero/unoccupied values
	 */
	public void clearBoard(){
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
	public int updateBoard(int row, int col){
		lowestRow = 0;
		for (int i = 0; i < ROWS; i++){
			if (board[i][col] == 0){
				lowestRow = i;
			}
		}
		if (board[lowestRow][col] == 0){
			board[lowestRow][col] = turn.getID();
			return 0;
		}
		return -1;
	}
	
	/**
	 * Switches player for next turn
	 */
	public void nextTurn(){
		if (turn.equals(playerOne)){
			turn = playerTwo;
		}
		else if (turn.equals(playerTwo)){
			turn = playerOne;
		}
	}
	
	public boolean hasDraw(){
		int counter = 0;
		for (int i = 0; i < ROWS; i++){
			for (int j = 0; j < COLUMNS; j++){
				if (board[i][j] != 0){
					counter++;
				}
			}
		}
		if (counter == ROWS*COLUMNS && !hasWinner()){
			return true;
		}
		return false;
	}
	
	public boolean hasVerticalWinner() {
		boolean winner = false;

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {

				// Check PLAYER ONE
				if (board[i][j] == 1 && i < 3) {
					if (board[i + 1][j] == 1 && board[i + 2][j] == 1 && board[i + 3][j] == 1) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
					}
				}
				// Check PLAYER TWO
				if (board[i][j] == 2 && i < 3) {
					if (board[i + 1][j] == 2 && board[i + 2][j] == 2 && board[i + 3][j] == 2) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
					}
				}
			}
		}
		return winner;
	}
	
	public boolean hasHorizontalWinner() {
		boolean winner = false;

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {

				// Check PLAYER ONE
				if (board[i][j] == 1 && j < 4) {
					if (board[i][j + 1] == 1 && board[i][j + 2] == 1 && board[i][j + 3] == 1) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
					}
				}
				// Check PLAYER TWo
				if (board[i][j] == 2 && j < 4) {
					if (board[i][j + 1] == 2 && board[i][j + 2] == 2 && board[i][j + 3] == 2) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
					}
				}
			}
		}
		return winner;
	}
	
	public boolean hasDiagonalWinner() {
		boolean winner = false;

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {

				// Check PLAYER ONE
				if (board[i][j] == 1 && j <= 3 && i <= 2) {
					if (board[i + 1][j + 1] == 1 && board[i + 2][j + 2] == 1 && board[i + 3][j + 3] == 1) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
						Diagonal = DIAGONAL.TOP_LEFT;
					}
				}
				if (board[i][j] == 1 && j >= 4 && i <= 2) {
					if (board[i + 1][j - 1] == 1 && board[i + 2][j - 2] == 1 && board[i + 3][j - 3] == 1) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
						Diagonal = DIAGONAL.TOP_RIGHT;
					}
				}
				if (board[i][j] == 1 && j <= 3 && i >= 3) {
					if (board[i - 1][j + 1] == 1 && board[i - 2][j + 2] == 1 && board[i - 3][j + 3] == 1) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
						Diagonal = DIAGONAL.BOTTOM_LEFT;
					}
				}
				if (board[i][j] == 1 && j >= 4 && i >= 3) {
					if (board[i - 1][j - 1] == 1 && board[i - 2][j - 2] == 1 && board[i - 3][j - 3] == 1) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
						Diagonal = DIAGONAL.BOTTOM_RIGHT;
					}
				}

				// Check PLAYER TWO
				if (board[i][j] == 2 && j <= 3 && i <= 2) {
					if (board[i + 1][j + 1] == 2 && board[i + 2][j + 2] == 2 && board[i + 3][j + 3] == 2) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
						Diagonal = DIAGONAL.TOP_LEFT;
					}
				}
				if (board[i][j] == 2 && j >= 4 && i <= 2) {
					if (board[i + 1][j - 1] == 2 && board[i + 2][j - 2] == 2 && board[i + 3][j - 3] == 2) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
						Diagonal = DIAGONAL.TOP_RIGHT;
					}
				}
				if (board[i][j] == 2 && j <= 3 && i >= 3) {
					if (board[i - 1][j + 1] == 2 && board[i - 2][j + 2] == 2 && board[i - 3][j + 3] == 2) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
						Diagonal = DIAGONAL.BOTTOM_LEFT;
					}
				}
				if (board[i][j] == 2 && j >= 4 && i >= 3) {
					if (board[i - 1][j - 1] == 2 && board[i - 2][j - 2] == 2 && board[i - 3][j - 3] == 2) {
						winner = true;
						winningRow = i+1;
						winningColumn = j+1;
						Diagonal = DIAGONAL.BOTTOM_RIGHT;
					}
				}
			}
		}
		return winner;

	}
	
	/**
	 * Checks board 2D array for any winners
	 * @return Boolean
	 */
	public boolean hasWinner(){
		boolean winner = false;
		
		if (hasVerticalWinner() || hasHorizontalWinner() || hasDiagonalWinner()){
			return true;
		}
		else return false;
	}
	
	/**
	 * The status of a board cell at the inputted row and column
	 * @param r - the row
	 * @param c - the column
	 * @return - the status
	 */
	public int getCell(int r, int c) {
		return board[r][c];
	}
}
	
	

package team.abhayumar.connect;

/**
 * Class to handle game execution
 * 
 * @author Abhay, Umar
 * @version 1.0
 */
public class Game {

	// Initialize variables
	private Player playerOne;
	private Player playerTwo;
	public Player turn;
	private int lowestRow;
	public static final int ROWS = 6;
	public static final int COLUMNS = 7;
	public int winningRow;
	public int winningColumn;
	private int board[][] = new int[ROWS][COLUMNS];
	public enum DIAGONAL {
		TOP_LEFT,
		TOP_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_RIGHT
	}
	private DIAGONAL Diagonal;

	/**
	 * Constructor
	 */
	public Game() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j] = 0;
			}
		}
	}

	/**
	 * Gets state of diagonal win.
	 * 
	 * @return state of win
	 */
	public DIAGONAL getDiagonalState() {
		return Diagonal;
	}

	/**
	 * Creates player object.
	 * 
	 * @param id  the id of player (1 or 2)
	 */
	public void createPlayer(int id) {
		if (id == 1) {
			playerOne = new Player(id);
		} else {
			playerTwo = new Player(id);
		}
	}

	/**
	 * Runs game with two player objects.
	 */
	public void runGame() {
		createPlayer(1);
		createPlayer(2);
		randPlayer();
	}

	/**
	 * Fills board array with zero/unoccupied values.
	 */
	public void reset() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				board[i][j] = 0;
			}
		}
		randPlayer();
	}

	/**
	 * Picks random player between 1 and 2.
	 */
	private void randPlayer() {
		int rand = (int) Math.round(Math.random()) + 1;
		if (rand == 1) {
			turn = playerOne;
		} else if (rand == 2) {
			turn = playerTwo;
		}
	}

	/**
	 * Updates board array at given column.
	 * 
	 * @param col  the column value
	 * @return status of board element
	 */
	public int updateBoard(int col) {
		lowestRow = 0;
		for (int i = 0; i < ROWS; i++) {
			if (board[i][col] == 0) {
				lowestRow = i;
			}
		}
		if (board[lowestRow][col] == 0) {
			board[lowestRow][col] = turn.getID();
			return lowestRow;
		}
		return -1;
	}

	/**
	 * Switches player for next turn.
	 */
	public void nextTurn() {
		if (turn.equals(playerOne)) {
			turn = playerTwo;
		} else if (turn.equals(playerTwo)) {
			turn = playerOne;
		}
	}

	/**
	 * Checks board for draw.
	 * 
	 * @return whether game has draw or not
	 */
	public boolean hasDraw() {
		int counter = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (board[i][j] != 0) {
					counter++;
				}
			}
		}
		if (counter == ROWS * COLUMNS && !hasWinner()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks board for four in a row vertically.
	 * 
	 * @return whether game has vertical win or not
	 */
	public boolean hasVerticalWinner() {
		boolean winner = false;

		// Loop through all array elements
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {

				// Check PLAYER ONE
				if (board[i][j] == 1 && i < 3) {
					if (board[i + 1][j] == 1 && board[i + 2][j] == 1 && board[i + 3][j] == 1) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
					}
				}
				// Check PLAYER TWO
				if (board[i][j] == 2 && i < 3) {
					if (board[i + 1][j] == 2 && board[i + 2][j] == 2 && board[i + 3][j] == 2) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
					}
				}
			}
		}
		return winner;
	}

	/**
	 * Checks board for four in a row horizontally.
	 * 
	 * @return whether game has horizontal win or not
	 */
	public boolean hasHorizontalWinner() {
		boolean winner = false;

		// Loop through all array elements
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {

				// Check PLAYER ONE
				if (board[i][j] == 1 && j < 4) {
					if (board[i][j + 1] == 1 && board[i][j + 2] == 1 && board[i][j + 3] == 1) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
					}
				}
				// Check PLAYER TWo
				if (board[i][j] == 2 && j < 4) {
					if (board[i][j + 1] == 2 && board[i][j + 2] == 2 && board[i][j + 3] == 2) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
					}
				}
			}
		}
		return winner;
	}

	/**
	 * Checks board for four in a row diagonally.
	 * 
	 * @return whether game has diagonal win or not
	 */
	public boolean hasDiagonalWinner() {
		boolean winner = false;
		
		// Loop through all array elements
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {

			// Check PLAYER ONE
				// Top left quadrant of board
				if (board[i][j] == 1 && j <= 3 && i <= 2) {
					if (board[i + 1][j + 1] == 1 && board[i + 2][j + 2] == 1 && board[i + 3][j + 3] == 1) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
						Diagonal = DIAGONAL.TOP_LEFT;
					}
				}
				
				// Top right quadrant of board
				if (board[i][j] == 1 && j >= 4 && i <= 2) {
					if (board[i + 1][j - 1] == 1 && board[i + 2][j - 2] == 1 && board[i + 3][j - 3] == 1) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
						Diagonal = DIAGONAL.TOP_RIGHT;
					}
				}
				
				// Bottom left quadrant of board
				if (board[i][j] == 1 && j <= 3 && i >= 3) {
					if (board[i - 1][j + 1] == 1 && board[i - 2][j + 2] == 1 && board[i - 3][j + 3] == 1) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
						Diagonal = DIAGONAL.BOTTOM_LEFT;
					}
				}
				
				// Bottom right quadrant of board
				if (board[i][j] == 1 && j >= 4 && i >= 3) {
					if (board[i - 1][j - 1] == 1 && board[i - 2][j - 2] == 1 && board[i - 3][j - 3] == 1) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
						Diagonal = DIAGONAL.BOTTOM_RIGHT;
					}
				}

			// Check PLAYER TWO
				// Top left quadrant of board
				if (board[i][j] == 2 && j <= 3 && i <= 2) {
					if (board[i + 1][j + 1] == 2 && board[i + 2][j + 2] == 2 && board[i + 3][j + 3] == 2) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
						Diagonal = DIAGONAL.TOP_LEFT;
					}
				}
				
				// Top right quadrant of board
				if (board[i][j] == 2 && j >= 4 && i <= 2) {
					if (board[i + 1][j - 1] == 2 && board[i + 2][j - 2] == 2 && board[i + 3][j - 3] == 2) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
						Diagonal = DIAGONAL.TOP_RIGHT;
					}
				}
				
				// Bottom left quadrant of board
				if (board[i][j] == 2 && j <= 3 && i >= 3) {
					if (board[i - 1][j + 1] == 2 && board[i - 2][j + 2] == 2 && board[i - 3][j + 3] == 2) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
						Diagonal = DIAGONAL.BOTTOM_LEFT;
					}
				}
				
				// Bottom right quadrant of board
				if (board[i][j] == 2 && j >= 4 && i >= 3) {
					if (board[i - 1][j - 1] == 2 && board[i - 2][j - 2] == 2 && board[i - 3][j - 3] == 2) {
						winner = true;
						winningRow = i + 1;
						winningColumn = j + 1;
						Diagonal = DIAGONAL.BOTTOM_RIGHT;
					}
				}
			}
		}
		return winner;
	}

	/**
	 * Checks board 2D array for any winners.
	 * 
	 * @return whether game has winner or not
	 */
	public boolean hasWinner() {
		if (hasVerticalWinner() || hasHorizontalWinner() || hasDiagonalWinner()) {
			return true;
		} else
			return false;
	}

	/**
	 * The status of a board cell at the inputed row and column.
	 * 
	 * @param r  row value
	 * @param c  column value
	 * @return the array element
	 */
	public int getCell(int r, int c) {
		return board[r][c];
	}
}

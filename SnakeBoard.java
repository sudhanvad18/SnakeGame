/**
 *	The playing board which contains the grid to play on, the snake itself, and the target.
 *
 *	@author	Sudhanva Deshpande
 *	@since	May 4, 2023
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	private int height;				//height of the board
	private int width;				//width of the board
	
	/*	Constructor	*/
	public SnakeBoard(int h, int w) 
	{
		board = new char[h][w];
		height = h;
		width = w;
	}
	
	/**
	 *	Print the board to the screen.
	 *	@param	snake: the snake 
	 *	@param	target: the Coordinate of the target
	 */
	public void printBoard(Snake snake, Coordinate target) 
	{
		/*	implement here	*/
		drawBoard(snake, target);

		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[0].length; j++)
			{
				System.out.print(" "+board[i][j]);
			}
			System.out.println("");
		}
		
	}

	/**
	 * Sets the character for each row and column of the board array
	 * @param snake: the snake
	 * @param target: the Coordinate of the target
	 */
	public void drawBoard(Snake snake, Coordinate target)
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[0].length; j++)
			{
				if(i == 0 || i == height-1)
				{
					if(j == 0 || j == width-1)
					{
						board[i][j] = '+';
					}

					else
					{
						board[i][j] = '-';
					}
				}

				else if(j == 0 || j == width-1)
				{
					board[i][j] = '|';
				}

				else if(i == target.getRow() && j == target.getCol())
				{
					board[i][j] = '+';
				}

				else if(i == snake.getSnake().get(0).getValue().getRow() && j == snake.getSnake().get(0).getValue().getCol())
				{
					board[i][j] = '@';
				}

				else if(isSnake(snake, i, j))
				{
					board[i][j] = '*';
				}

				else
					board[i][j] = ' ';


			}
		}
	}
	
	/* Helper methods go here	*/

	/**
	 * Check to see if the given coordinate at row, col is part of the snake
	 * 
	 * @param snake: the snake
	 * @param row: the row of the coordinate
	 * @param col: the column of the coordinate
	 * @return	true if the given coordinate is part of the snake; false otherwise
	 */
	public boolean isSnake(Snake snake, int row, int col)
	{
		for(int i = 0; i < snake.getSnake().size(); i++)
		{
			if(snake.getSnake().get(i).getValue().getRow() == row && snake.getSnake().get(i).getValue().getCol() == col)
			{
				return true;
			}
		}

		return false;
	}
	
	/*	Accessor methods	*/
	/**
	 * @return the board
	 */
	public char[][] getBoard() { return board; }
	/**
	 * @return the height
	 */
	public int getHeight() { return height; }
	/**
	 * @return the width
	 */
	public int getWidth() { return width; }

	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(new Coordinate(3, 3));
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}
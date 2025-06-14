import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

/**
 *	Snake Game - This is the classic Snake Game. There is a snake on the board which can be moved using wasd keys.
 *	The snakes goes around trying to eat the target(+). Each time it eats a target the score increases, and another
 *	target is placed randomly. The goal of the game is to get as high a score possible. If the snake's head hits the
 *	end of the board or itself, or the snake isn't able to move, the game ends.
 *	
 *	@author	Sudhanva Deshpande
 *	@since	May 9, 2023
 */
public class SnakeGame {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game
	private boolean quitGame;	// boolean that indicates where the game should be quit

	/*	Constructor	*/
	public SnakeGame() 
	{
		snake = new Snake(new Coordinate(3, 3));
		board = new SnakeBoard(20,25);
		target = getTarget();
		score = 0;
		quitGame = false;
	}
	
	/*	Main method	*/
	public static void main(String[] args) 
	{
		SnakeGame sg = new SnakeGame();
		sg.runGame();
	}

	/* Runs the game by calling the required methods in order. */
	public void runGame()
	{
		printIntroduction();

		while(!quitGame)
		{
			board.printBoard(snake, target);
			getInput();
		}

		System.out.println("\nGame is over");
		System.out.println("Score = "+score);
		System.out.println("\nThanks for playing SnakeGame!!!");
	}
	
	/**	Print the game introduction	*/
	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board " +
							"eating targets \"+\".");
		System.out.println("Each time the snake eats the target it grows " +
							"another * longer.");
		System.out.println("The objective is to grow the longest it can " +
							"without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}
	
	/**	Print help menu	*/
	public void helpMenu() {
		System.out.println("\nCommands:\n" +
							"  w - move north\n" +
							"  s - move south\n" +
							"  d - move east\n" +
							"  a - move west\n" +
							"  h - help\n" +
							"  f - save game to file\n" +
							"  r - restore game from file\n" +
							"  q - quit");
		Prompt.getString("Press enter to continue");
	}

	/* Gets input from the user. Makes sure to forgive user for mistakes. Calls necessary methods based on
	 * user input.
	 */
	public void getInput()
	{
		String input = ""+Prompt.getChar("Score: "+score+" (w - North, s - South, d - East, a - West, h - Help)");
		boolean isValid = false;

		while(!isValid)
		{
			if(input.equals("q"))
			{
				boolean validQuit = false;
				
				input = ""+Prompt.getChar("\nDo you really want to quit? (y or n)");
				if(input.equals("y"))
				{
					quitGame = true;
				}
				
				isValid = true;
			}

			else if(input.equals("h"))
			{
				helpMenu();
				isValid = true;
			}
			else if(input.equals("f"))
			{
				boolean validQuit = false;
	
				input = ""+Prompt.getChar("\nSave Game? (y or n)");
				if(input.equals("y"))
				{
					quitGame = true;
				}
			
				saveGame();
				isValid = true;
			}
			else if(input.equals("r"))
			{
				restoreGame();
				isValid = true;
			}
			else if(input.equals("w")||input.equals("a")||input.equals("s")||input.equals("d"))
			{
				moveSnake(input);
				isValid = true;
			}

			else
			{
				input = ""+Prompt.getChar("Score: "+score+" (w - North, s - South, d - East, a - West, h - Help)");
			}
		}
	}

	/**
	 * Saves the game by saving the score, target coordinates, snake length, and the coordinates 
	 * of the snake into a text file. Uses FileUtils to write to file.
	 */
	public void saveGame()
	{
		String output = "";
		output+="Score "+score;
		output+="\nTarget "+target.getRow()+" "+target.getCol();
		output+="\nSnake "+(score+5);
		output+="\n";

		for(int i = 0; i < snake.getSnake().size(); i++)
		{
			output+=""+snake.getSnake().get(i).getValue().getRow()+" ";
			output+=""+snake.getSnake().get(i).getValue().getCol();
			output+="\n";
		}

		PrintWriter outFile = FileUtils.openToWrite("snakeGameSave.txt");
		outFile.print(output);
		outFile.close();
	}

	/**
	 * Restores the game by reading in the score, target coordinates, and coordinates of the 
	 * snake. Draws the board according to these coordinates and allows user to continue
	 * the game.
	 */
	public void restoreGame()
	{
		Scanner sc = FileUtils.openToRead("snakeGameSave.txt");
		int count = 0;
		String len = "";
		int size = -1;

		while(sc.hasNext())
		{
			String line = sc.nextLine();
			if(count == 0)
			{
				String saveScore = line.substring(6);
				score = Integer.parseInt(saveScore);
			}

			else if(count == 1)
			{
				String r = line.substring(7);
				String newR = r.substring(0, r.indexOf(" "));
				String c = r.substring(r.indexOf(" ")+1);

				target = new Coordinate(Integer.parseInt(newR), Integer.parseInt(c));
			}

			else if(count == 2)
			{
				len = line.substring(line.indexOf(" ")+1);
				size = Integer.parseInt(len);
				snake.getSnake().clear();
			}

			else 
			{			
				String row = line.substring(0, line.indexOf(" "));
				String col = line.substring(line.indexOf(" ")+1);

				int r = Integer.parseInt(row);
				int c = Integer.parseInt(col);
				snake.getSnake().add(new Coordinate(r, c));
			}
			count++;
		}
	}

	/**
	 * Calls the necessary methods to move the snake. Also checks to make sure that the snake can move.
	 * If for any reason the snake cannot move, the game is quit.
	 * 
	 * @param cmd the command(wasd)
	 */
	public void moveSnake(String cmd)
	{
		quitGame = getCoords(cmd);
		if(!quitGame)
			canContinue();

		if(quitGame)
			board.printBoard(snake, target);
	}

	/**
	 * Gets the new coordinate for the head of the snake to move. Then shifts the position of each part of the
	 * snake to the coordinate of the part before it. If the head hits the end of the board or itself, the 
	 * game is ended. If all coordinates are valid, then they are changed in the SinglyLinkedList.
	 * 
	 * @param cmd the command(wasd)
	 * @return	true if the game should be ended; false otherwise
	 */
	public boolean getCoords(String cmd)
	{
		Coordinate newCoord = null;
		Coordinate last = snake.getSnake().get(snake.getSnake().size()-1).getValue();
		if(cmd.equals("w"))
		{
			newCoord = new Coordinate(snake.getSnake().get(0).getValue().getRow()-1, snake.getSnake().get(0).getValue().getCol());
		}
		
		else if(cmd.equals("a"))
		{
			newCoord = new Coordinate(snake.getSnake().get(0).getValue().getRow(), snake.getSnake().get(0).getValue().getCol()-1);
		}

		else if(cmd.equals("s"))
		{
			newCoord = new Coordinate(snake.getSnake().get(0).getValue().getRow()+1, snake.getSnake().get(0).getValue().getCol());
		}

		else if(cmd.equals("d"))
		{
			newCoord = new Coordinate(snake.getSnake().get(0).getValue().getRow(), snake.getSnake().get(0).getValue().getCol()+1);
		}

		if(newCoord.getRow() <=0 || newCoord.getRow()>=board.getHeight()-1)
			return true;
		else if(newCoord.getCol() <=0 || newCoord.getCol()>=board.getWidth()-1)
			return true;

		else
		{
			for(int i = 1; i < snake.getSnake().size(); i++)
			{
				if(newCoord.equals(snake.getSnake().get(i).getValue()))
					return true;
			}

			for(int i = snake.getSnake().size()-1; i > 0; i--)
			{
				snake.getSnake().get(i).setValue(snake.getSnake().get(i-1).getValue());
			}
			
			snake.getSnake().get(0).setValue(newCoord);
			if(snake.getSnake().get(0).getValue().equals(target)) 
			{
				score++;
				target = getTarget();
				snake.getSnake().add(new Coordinate(last.getRow(), last.getCol()));
			}
			return false;
		}
	}

	/**
	 * Checks to see if there are more than 5 spaces in the board and that the snake head
	 * has atleast one open space to move to. If none of these are true, then the game is
	 * quit. Or else the game continues.
	 */
	public void canContinue()
	{
		char[][] newBoard = board.getBoard();
		int count = 0;

		for(int i=0; i<newBoard.length; i++)
		{
			for(int j=0; j<newBoard[0].length; j++)
			{
				if(newBoard[i][j] == ' ')
					count++;
			}
		}

		if(count <=5)
			quitGame = true;
		else
		{
			boolean isSpace = false;
			int spaces = 0;
			Coordinate first = snake.getSnake().get(0).getValue();
			for(int i = first.getRow()-1; i<=first.getRow()+1; i++)
			{
				for(int j = first.getCol()-1; j<=first.getCol()+1; j++)
				{
					if(newBoard[i][j] == ' ' || newBoard[i][j] == '+')
					{
						isSpace = true;
						spaces++;
					}
				}
			}

			if(!isSpace)
				quitGame = true;
			else if(spaces<=1)
				quitGame = true;
		}
	}

	/**
	 * Generates a random Coordinate for the target
	 * @return random Coordinate
	 */
	public Coordinate getTarget()
	{
		int randRow = (int)(Math.random() * 18 + 1);
		int randCol = (int)(Math.random() * 23 + 1);

		while(board.isSnake(snake, randRow, randCol))
		{
			randRow = (int)(Math.random() * 18 + 1);
			randCol = (int)(Math.random() * 23 + 1);
		}

		Coordinate t = new Coordinate(randRow, randCol);
		return t;
	}
	
}
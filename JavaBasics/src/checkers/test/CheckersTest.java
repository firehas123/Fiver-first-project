package checkers.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import checkers.Checkers;
import checkers.GameBoard;
import checkers.GameBoard.Move;
import gradescope.grader.GradedTest;
import ledger.LoggedTest;

/**
 * @author Tony
 */
public class CheckersTest extends LoggedTest
{
	private boolean debugMove = false;
	private boolean debugValidate = true;
	
	@Test		
    @GradedTest(name="Test startGame()", max_score=5)
    public void testInit() 
    {
		GameBoard uut = new GameBoard();
		uut.startGame();
		char[][] board = uut.getBoard();

		assertEquals("The board must have 8 rows", 8, board.length);
	    for (int index = 0; index < board.length; index++)
	    {
	    	assertEquals("Each row must have 8 columns and row " + index + " did not", 8, board[index].length);
	    	if (index == 0 || index == 2)	
	    	{
	    		assertArrayEquals("Row " + index + " not setup correctly", 
	    				          new char[]{GameBoard.EMPTY, GameBoard.PLAYER_2, GameBoard.EMPTY, GameBoard.PLAYER_2, GameBoard.EMPTY, GameBoard.PLAYER_2, GameBoard.EMPTY, GameBoard.PLAYER_2},
	    				          board[index]);
	    	} else if (index == 1)
	    	{
	    		assertArrayEquals("Row " + index + " not setup correctly", 
				          new char[]{GameBoard.PLAYER_2,GameBoard.EMPTY, GameBoard.PLAYER_2, GameBoard.EMPTY, GameBoard.PLAYER_2, GameBoard.EMPTY, GameBoard.PLAYER_2, GameBoard.EMPTY},
				          board[index]);
	    	} else if (index == 3 || index == 4)
	    	{
	    		assertArrayEquals("Row " + index + " not setup correctly", 
	    				          new char[]{GameBoard.EMPTY, GameBoard.EMPTY, GameBoard.EMPTY, GameBoard.EMPTY, GameBoard.EMPTY, GameBoard.EMPTY, GameBoard.EMPTY, GameBoard.EMPTY},
	    				          board[index]);
	    	} else if (index == 6)
	    	{
	    		assertArrayEquals("Row " + index + " not setup correctly", 
				                  new char[]{GameBoard.EMPTY, GameBoard.PLAYER_1, GameBoard.EMPTY, GameBoard.PLAYER_1, GameBoard.EMPTY, GameBoard.PLAYER_1, GameBoard.EMPTY, GameBoard.PLAYER_1},
				                  board[index]);
	    	} else if (index == 5 || index == 7)
	    	{
	    		assertArrayEquals("Row " + index + " not setup correctly", 
				                  new char[]{GameBoard.PLAYER_1, GameBoard.EMPTY, GameBoard.PLAYER_1, GameBoard.EMPTY, GameBoard.PLAYER_1, GameBoard.EMPTY, GameBoard.PLAYER_1, GameBoard.EMPTY},
	    				          board[index]);
	    	}
	    }
    }
	
	private class TestPosition
	{
		int row;
		int column;
		char squareContents;
		public TestPosition(int column, int row, char squareContents)
		{
			this.row = row;
			this.column = column;
			this.squareContents = squareContents;
		}
	}

	private class MoveTest
	{
		GameBoard.Move move;
		TestPosition[] expectedUpdates;
		public MoveTest(Move move, TestPosition[] expectedUpdates)
		{
			this.move = move;
			this.expectedUpdates = expectedUpdates;
		}
	}
	
	private MoveTest[] getTests(GameBoard gb)
	{
		MoveTest[] tests = {
		new MoveTest(gb.new Move(GameBoard.PLAYER_1, 0, 5, 1, 4), new TestPosition[]{new TestPosition(0, 5, GameBoard.EMPTY), new TestPosition(1, 4, GameBoard.PLAYER_1)}),
		new MoveTest(gb.new Move(GameBoard.PLAYER_2, 1, 2, 0, 3), new TestPosition[]{new TestPosition(1, 2, GameBoard.EMPTY), new TestPosition(0, 3, GameBoard.PLAYER_2)}),
		new MoveTest(gb.new Move(GameBoard.PLAYER_1, 2, 5, 3, 4), new TestPosition[]{new TestPosition(2, 5, GameBoard.EMPTY), new TestPosition(3, 4, GameBoard.PLAYER_1)}),
		new MoveTest(gb.new Move(GameBoard.PLAYER_2, 0, 3, 2, 5), new TestPosition[]{new TestPosition(0, 3, GameBoard.EMPTY), new TestPosition(1, 4, GameBoard.EMPTY), new TestPosition(2, 5, GameBoard.PLAYER_2)}),
		new MoveTest(gb.new Move(GameBoard.PLAYER_1, 3, 6, 1, 4), new TestPosition[]{new TestPosition(3, 6, GameBoard.EMPTY), new TestPosition(2, 5, GameBoard.EMPTY), new TestPosition(1, 4, GameBoard.PLAYER_1)}),
		new MoveTest(gb.new Move(GameBoard.PLAYER_2, 3, 2, 2, 3), new TestPosition[]{new TestPosition(3, 2, GameBoard.EMPTY), new TestPosition(2, 3, GameBoard.PLAYER_2)}),
		new MoveTest(gb.new Move(GameBoard.PLAYER_1, 4, 5, 5, 4), new TestPosition[]{new TestPosition(4, 5, GameBoard.EMPTY), new TestPosition(5, 4, GameBoard.PLAYER_1)})
		};
		return tests;
	}
	
	@Test		
    @GradedTest(name="Test makeMove()", max_score=10)
    public void testMoves()
    {		
		GameBoard uut = new GameBoard();
		uut.startGame();
	    int nextPlayer = 1;
	    int playerShift = 1;
        if (debugMove) uut.drawBoard();
	    for (MoveTest test : getTests(uut))
	    {
	        if (debugMove) System.out.println("Make the move: " + test.move);
	        uut.makeMove(test.move);
	        if (debugMove) uut.drawBoard();
	        
	        for (TestPosition expected : test.expectedUpdates)
	        {
	        	char actual = uut.getBoard()[expected.row][expected.column];
	        	assertEquals("Your code reported " + actual + " at the row " + expected.row + " and column " + expected.column + " but we expected " + expected.squareContents, 
	        			     expected.squareContents + "", actual + "");
	        }
	        nextPlayer = nextPlayer + playerShift;
	        playerShift = playerShift * -1;
	    }
    }
	
	private class ValidationTest
	{
		Move move;
		String expected;
		public ValidationTest(Move move, String expected)
		{
			this.move = move;
			this.expected = expected;
		}
	}

	private ValidationTest [] getValidationTests(GameBoard gb)
	{
		return new ValidationTest[] {
			new ValidationTest(gb.new Move(GameBoard.PLAYER_1, 1,4,0,3), Checkers.OK_MOVE), // A basic move player 1
			new ValidationTest(gb.new Move(GameBoard.PLAYER_2, 0,1,1,2), Checkers.OK_MOVE), // A basic move player 2
			new ValidationTest(gb.new Move(GameBoard.PLAYER_1, 1,4,3,2), Checkers.OK_MOVE), // A jump for player 1 
			new ValidationTest(gb.new Move(GameBoard.PLAYER_1, 3,4,1,2), Checkers.OK_MOVE), // A jump for player 1 
			new ValidationTest(gb.new Move(GameBoard.PLAYER_2, 2,3,0,5), Checkers.OK_MOVE), // A jump for player 2 
			new ValidationTest(gb.new Move(GameBoard.PLAYER_2, 2,3,4,5), Checkers.OK_MOVE), // A jump for player 2 

			new ValidationTest(gb.new Move(GameBoard.PLAYER_1, 0,0,1,1), Checkers.WRONG_PIECE), // Empty square  
			new ValidationTest(gb.new Move(GameBoard.PLAYER_2, 0,0,1,1), Checkers.WRONG_PIECE), // Empty square  
			new ValidationTest(gb.new Move(GameBoard.PLAYER_1, 0,1,1,2), Checkers.WRONG_PIECE), // Opponent  
			new ValidationTest(gb.new Move(GameBoard.PLAYER_2, 1,6,0,5), Checkers.WRONG_PIECE), // Opponent  

			new ValidationTest(gb.new Move(GameBoard.PLAYER_1, 1,6,4,5), Checkers.ONLY_2), // Horizontally  
			new ValidationTest(gb.new Move(GameBoard.PLAYER_2, 6,1,1,1), Checkers.ONLY_2), // Horizontally  
			new ValidationTest(gb.new Move(GameBoard.PLAYER_1, 0,7,0,3), Checkers.ONLY_2), // Vertically  
			new ValidationTest(gb.new Move(GameBoard.PLAYER_2, 7,0,4,3), Checkers.ONLY_2), // Vertically  

			new ValidationTest(gb.new Move(GameBoard.PLAYER_1, 1,4,0,5), Checkers.PLAYER_1_UP), 
			new ValidationTest(gb.new Move(GameBoard.PLAYER_2, 2,3,1,2), Checkers.PLAYER_2_DOWN),   

			new ValidationTest(gb.new Move(GameBoard.PLAYER_1, 1,4,2,3), Checkers.SPACE_TAKEN), 
			new ValidationTest(gb.new Move(GameBoard.PLAYER_2, 2,3,1,4), Checkers.SPACE_TAKEN),   

			new ValidationTest(gb.new Move(GameBoard.PLAYER_1, 0,7,2,5), Checkers.JUMP_SELF), 
			new ValidationTest(gb.new Move(GameBoard.PLAYER_2, 3,0,1,2), Checkers.JUMP_SELF),   

			new ValidationTest(gb.new Move(GameBoard.PLAYER_1, 4,7,2,5), Checkers.JUMP_EMPTY), 
			new ValidationTest(gb.new Move(GameBoard.PLAYER_2, 2,1,0,3), Checkers.JUMP_EMPTY),   
		};
	}
	
	@Test		
    @GradedTest(name="Test validateMove()", max_score=25)
    public void testValidation()
    {		
		GameBoard uut = new GameBoard();
		uut.startGame();
		for (MoveTest setup : getTests(uut))
		{
			uut.makeMove(setup.move);
		}
		
		if (debugValidate) uut.drawBoard();
	    for (ValidationTest test : getValidationTests(uut))
	    {
			if (debugValidate) System.out.println(test.move);
	        String actual = uut.validateMove(test.move);
	        assertEquals("Validation failed (set debugValidate = true for more info)", test.expected, actual);
	    }
    }

	private static final String CODE_FILE= "src/checkers/GameBoard";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}
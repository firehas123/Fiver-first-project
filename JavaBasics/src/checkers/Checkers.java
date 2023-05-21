package checkers;

/**
 * @author Tony
 * This interface contains constants that may help your logic
 * ----You do not need to edit or change this class at all----
 */
public interface Checkers
{
	public static final int SIZE = 8;
	
	public static final char PLAYER_1 = 'O';
	public static final char PLAYER_2 = 'X';
	public static final char EMPTY    = ' ';

	// Validation Results
	public static final String OK_MOVE         = "OK";
	public static final String WRONG_PIECE     = "This space does not contain your piece";
	public static final String ONLY_2          = "You can only move a piece one space diagonally, or two when legally jumping";
	public static final String PLAYER_1_UP     = "Player 1 must move up";
	public static final String PLAYER_2_DOWN   = "Player 2 must move down";
	public static final String SPACE_TAKEN     = "The destination space is already taken";
	public static final String JUMP_SELF       = "You cannot jump your own piece";
	public static final String JUMP_EMPTY      = "You cannot jump an empty space";
	public static final String UNKNOWN_INVALID = "Your move is not valid for an unknown reason";
	    
}

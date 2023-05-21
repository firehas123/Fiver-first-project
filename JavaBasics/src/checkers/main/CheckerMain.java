package checkers.main;

import checkers.Checkers;
import checkers.GameBoard;

public class CheckerMain {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.startGame();

        // Making some moves
        GameBoard.Move move1 = gameBoard.new Move('O', 4, 2, 3, 3);
        String validation1 = gameBoard.validateMove(move1);
        if (validation1.equals(Checkers.OK_MOVE)) {
            gameBoard.makeMove(move1);
            gameBoard.drawBoard();
        }
        //Move 2
        GameBoard.Move move2 = gameBoard.new Move('X', 5, 5, 4, 4);
        String validation2 = gameBoard.validateMove(move2);
        if (validation2.equals(Checkers.OK_MOVE)) {
            gameBoard.makeMove(move2);
            gameBoard.drawBoard();
        }
        //Move 3
        GameBoard.Move move3 = gameBoard.new Move('O', 3, 3, 5, 5);
        String validation3 = gameBoard.validateMove(move3);
        System.out.println(validation3);
        if (validation3.equals(Checkers.OK_MOVE)) {
            gameBoard.makeMove(move3);
            gameBoard.drawBoard();
        }
        
    }
}

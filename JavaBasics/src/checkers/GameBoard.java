package checkers;


import java.util.Stack;

public class GameBoard implements Checkers {

    private static final int SIZE = 8;

    private char[][] board;
    private Stack<Move> moves = new Stack<>();

    public class Move {
        char player;
        int fromRow;
        int fromColumn;
        int toRow;
        int toColumn;

        public Move(char p, int fc, int fr, int tc, int tr) {
            player = p;
            fromRow = fr;
            fromColumn = fc;
            toRow = tr;
            toColumn = tc;
        }

        @Override
        public String toString() {
            return "Move [player=" + player + ", fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", toRow=" + toRow
                    + ", toColumn=" + toColumn + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + fromColumn;
            result = prime * result + fromRow;
            result = prime * result + toColumn;
            result = prime * result + toRow;
            return result;
        }
    }

    public void startGame() {
        board = new char[SIZE][SIZE];
        initializeBoard();
        drawBoard();
    }

    public void makeMove(Move m) {
        int fromRow = m.fromRow;
        int fromColumn = m.fromColumn;
        int toRow = m.toRow;
        int toColumn = m.toColumn;

        char playerPiece = board[fromRow][fromColumn];
        board[toRow][toColumn] = playerPiece;
        board[fromRow][fromColumn] = ' ';

        if (Math.abs(toRow - fromRow) == 2 && Math.abs(toColumn - fromColumn) == 2) {
            int jumpedRow = (fromRow + toRow) / 2;
            int jumpedColumn = (fromColumn + toColumn) / 2;
            board[jumpedRow][jumpedColumn] = ' ';
        }

        moves.push(m);
    }

    public String validateMove(Move m) {
        int fromRow = m.fromRow;
        int fromColumn = m.fromColumn;
        int toRow = m.toRow;
        int toColumn = m.toColumn;
        char playerPiece = m.player;
        char pieceOnBoard = board[fromRow][fromColumn];
        if ( pieceOnBoard!= playerPiece)
            return WRONG_PIECE;

        if (playerPiece == '1' && toRow >= fromRow)
            return PLAYER_1_UP;

        if (playerPiece == '2' && toRow <= fromRow)
            return PLAYER_2_DOWN;

        if (board[toRow][toColumn] != ' ')
            return SPACE_TAKEN;

        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toColumn - fromColumn);

        if (rowDiff > 2 || colDiff > 2)
            return ONLY_2;

        if (rowDiff == 1 && colDiff == 1)
            return OK_MOVE;

        if (rowDiff == 2 && colDiff == 2) {
            int jumpedRow = (fromRow + toRow) / 2;
            int jumpedColumn = (fromColumn + toColumn) / 2;

            if (board[jumpedRow][jumpedColumn] == ' ' || board[jumpedRow][jumpedColumn] == playerPiece)
                return JUMP_SELF;

            return OK_MOVE;
        }

        return UNKNOWN_INVALID;
    }

    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if ((row + col) % 2 == 0) {
                    if (row < 3)
                        board[row][col] = PLAYER_1;
                    else if (row > 4)
                        board[row][col] = PLAYER_2;
                    else
                        board[row][col] = EMPTY;
                } else {
                    board[row][col] = EMPTY;
                }
            }
        }
    }

    private void printBorder(int width) {
        for (int c = 0; c < width; c++) {
            System.out.print("+---");
        }
        System.out.println("+ ");
    }

    private void printSquares(char[] r, int rowNumber) {
        for (char piece : r) {
            System.out.print("| " + piece + " ");
        }
        System.out.println("| " + rowNumber);
    }

    public void drawBoard() {
        int rowNumber = 0;
        for (char[] r : board) {
            printBorder(r.length);
            printSquares(r, rowNumber++);
        }
        printBorder(board[0].length);
        for (int i = 0; i < SIZE; i++) {
            System.out.print("  " + i + " ");
        }
        System.out.println();
        System.out.println();
    }

    public char[][] getBoard() {
        return board;
    }
}

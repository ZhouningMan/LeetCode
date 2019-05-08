package design;

public class TicTacToe {
    private final int board[][];
    private final int size;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new int[n][n];
        size = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        return checkSuccess(row, col, player);
    }

    private int checkSuccess(int row, int col, int player) {
        int r = 0;
        for(; r < size; ++r) {
            //check if col matches
            if(board[r][col] != player) break;
        }
        if(r == size) return player;

        int c = 0;
        for(; c < size; ++c) {
            //check if row matches
            if(board[row][c] != player) break;
        }
        if(c == size) return player;

        if(row == col) {
            //check if diagonal row matches
            for(r = 0, c = 0; r < size && c < size; r++, c++) {
                if (board[r][c] != player) break;
            }
            if(r == size) return player;
        }

        if(row + col == size - 1) {
            for(r = 0, c = size - 1; r < size && c >=0; r++, c--) {
                if(board[r][c] != player) break;
            }
            if(r == size && c == -1) return player;
        }
        return 0;
    }
}

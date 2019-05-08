package array_string;

public class CandyCrush {

    public int[][] candyCrush(int[][] board) {
        final int R = board.length;
        final int C = board[0].length;
        boolean done = false;
        while(!done) {
            done = true;
            //horizontal direction
            for(int r = 0; r < R; ++r) {
                for(int c = 0; c < C - 2; ++c) {
                    int val = Math.abs(board[r][c]);
                    if( val > 0 && val == Math.abs(board[r][c+1]) && val == Math.abs(board[r][c+2])) {
                        done = false;
                        //set them all to negative value;
                        board[r][c] = board[r][c+1] = board[r][c+2] = -val;
                    }
                }
            }
            //vertical direction
            for(int c = 0; c < C; ++c) {
                for(int r = 0; r < R-2; ++r ) {
                    int val = Math.abs(board[r][c]);
                    if (val > 0 && val == Math.abs(board[r + 1][c]) && val == Math.abs(board[r + 2][c])) {
                        done = false;
                        board[r][c] = board[r+1][c] = board[r+2][c] = -val;
                    }
                }
            }
            //drop vertically
            for(int c = 0; c < C; ++c) {
                int pos = R - 1;
                for(int r = R - 1; r >= 0; --r) {
                    if(board[r][c] > 0) board[pos--][c] = board[r][c];
                }
                while(pos >=0) board[pos--][c] = 0;
            }
        }
        return board;
    }
}

package other;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    private static final int ROWS = 9;
    private static final int COLS = 9;

    @SuppressWarnings("Duplicates")
    public boolean isValidSudoku(char[][] board) {
        //check row
        for(int r = 0 ; r < ROWS; ++r) {
            Set<Character> colsFirst =new HashSet<>();
            Set<Character> rowsFirst = new HashSet<>();
            for(int c = 0; c < COLS; ++c) {
                if(board[r][c] != '.' && !colsFirst.add(board[r][c])) return false;
                if(board[c][r] != '.' && !rowsFirst.add(board[c][r])) return false;
            }
        }

        //check square
        for(int r = 0; r < ROWS; r += 3) {
            for(int c = 0; c < COLS; c += 3) {
                if(!validateSquare(board, r, c, r+2, c+2)) return false;
            }
        }
        return true;
    }

    private boolean validateSquare(char[][]board, int br, int bc, int er, int ec) {
        Set<Character> set = new HashSet<>(9);
        for(int r = br; r <= er; ++r) {
            for(int c = bc; c <= ec; ++c) {
                char cr = board[r][c];
                if(cr == '.') continue;
                if(set.contains(cr)) return false;
                set.add(cr);
            }
        }
        return true;
    }
}

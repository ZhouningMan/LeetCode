package array_string;

public class SetMatrixZeros {
    public static void test() {
        new SetMatrixZeros().setZeroes(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
    }

    public void setZeroes(int[][] matrix) {
        //first(0th) col for zeroing rows, so for elements in the first column, we
        //want to make sure we only zero them out if first cell is zero.
        //first row for zeroing cols;
        boolean firstRow = false;
        boolean firstCol = false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (matrix[r][c] == 0) {
                    if (r == 0 && c == 0) {
                        firstCol = true;
                        firstRow = true;
                    }
                    if (r == 0) {
                        firstRow = true;
                    } else if (c == 0) {
                        firstCol = true;
                    }
                    //always true no matter what
                    matrix[0][c] = matrix[r][0] = 0;
                }
            }
        }
        //second pass fill out all zeros
        for (int r = rows - 1; r >= 0; --r) {
            for (int c = cols - 1; c >= 0; --c) {
                if (c == 0) {
                    if (firstCol) matrix[r][c] = 0;
                } else if (r == 0) {
                    if (firstRow) matrix[r][c] = 0;
                } else if (matrix[0][c] == 0 || matrix[r][0] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }
    }
}

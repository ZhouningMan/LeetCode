package array_string;

public class RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //this only works for square matrix
        for(int r = 0; r < n/2; r++) {
            for(int c = r; c < n - 1 - r; c++) {
                //there are just four operations, so don't write
                //a loop which only complicates things
                //Also, do the operations counter clockwise, so we
                //only to save a single value. It is a lot harder
                //to do it clockwise.
                int temp = matrix[r][c];
                matrix[r][c] = matrix[n -1 -c][r];
                matrix[n-1-c][r] = matrix[n-1-r][n-1-c];
                matrix[n-1-r][n-1-c] = matrix[c][n-1-r];
                matrix[c][n-1-r] = temp;
            }
        }
    }
}

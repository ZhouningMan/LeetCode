package design;

public class NumMatrix {
//    private final int[][] matrix;
//    private final int[][] sumMatrix;
//    public NumMatrix(int[][] matrix) {
//        this.matrix = matrix;
//        sumMatrix = new int[matrix.length][matrix[0].length];
//        for(int r = 0; r < matrix.length; ++r) {
//            for(int c = 0; c < matrix[0].length; ++c) {
//                if(r == 0) {
//                    int prev = 0;
//                    if(c > 0) {
//                        prev =sumMatrix[r][c-1];
//                    }
//                    sumMatrix[r][c] = matrix[r][c] + prev;
//                } else if(c == 0) {
//                    sumMatrix[r][c] = matrix[r][c] + sumMatrix[r-1][c];
//                } else {
//                    sumMatrix[r][c] = matrix[r][c] + sumMatrix[r-1][c] + sumMatrix[r][c-1] - sumMatrix[r-1][c-1];
//                }
//            }
//        }
//    }
//
//    public void update(int row, int col, int val) {
//
//    }
//
//    public int sumRegion(int row1, int col1, int row2, int col2) {
//
//    }
}

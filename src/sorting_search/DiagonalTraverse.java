package sorting_search;

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0 ) return new int[0];
        final int ROW = matrix.length, COL = matrix[0].length, SIZE = ROW * COL;
        int[] result = new int[SIZE];
        int i = 0;
        int dR = -1, dC = 1;
        int r = 0, c = 0;
        while(i < SIZE) {
            result[i++] = matrix[r][c];
            if(r + dR < 0) {
                dR = 1;
                dC = -1;
                if(c + 1 == COL) r = r + 1;
                else c = c + 1;
            } else if(r + dR == ROW) {
                dR = -1;
                dC = 1;
                c = c + 1;
            } else if(c + dC < 0) {
                dR = -1;
                dC = 1;
                if(r + 1 == ROW) c = c + 1;
                else r = r + 1;
            } else if(c + dC == COL) {
                dR = 1;
                dC = -1;
                r = r + 1;
            } else {
                r += dR;
                c += dC;
            }
        }
        return result;
    }
}

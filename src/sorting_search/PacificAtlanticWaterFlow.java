package sorting_search;

import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {

    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        //start from the order, and trace backward to identify all the positions can reach the border
        //VERY SMART.
        for (int i = 0; i < n; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, n - 1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(new int[]{i, j});
                }
            }
        }
        return result;
    }

    private void dfs(int[][] matrix, boolean[][] visited, int height, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length
                || visited[i][j] || matrix[i][j] < height) {
            return;
        }

        visited[i][j] = true;
        for (int[] dir : dirs) {
            dfs(matrix, visited, matrix[i][j], i + dir[0], j + dir[1]);
        }
    }

//    public List<int[]> pacificAtlantic(int[][] matrix) {
//        if(matrix == null || matrix.length == 0) return new ArrayList<>();
//        final int rowCount = matrix.length;
//        final int colCount = matrix[0].length;
//        List<int[]> result = new LinkedList<>();
//        for(int r = 0 ; r < rowCount; ++r) {
//            for(int c = 0; c < colCount; c++) {
//                if(reachBoth(matrix, r, c, new boolean[rowCount][colCount], new boolean[2])) {
//                    result.add(new int[]{r, c});
//                }
//            }
//        }
//
//        return result;
//    }
//    private boolean reachBoth(int[][] matrix, int row, int col, boolean[][] visited, boolean[] oceans) {
//        if(visited[row][col]) return false;
//        visited[row][col] = true;
//        if(row == 0 || col == 0) oceans[0] = true; //reach pacific
//        if(row == matrix.length - 1|| col == matrix[0].length - 1) oceans[1] = true; //reach atlantic
//        if(oceans[0] && oceans[1]) return true;
//
//        List<int[]> nextMoves = Arrays.asList(new int[]{row + 1, col},
//                new int[]{row - 1 , col},
//                new int[]{row, col - 1},
//                new int[]{row, col + 1});
//        for(int[] next : nextMoves) {
//            int nextRow = next[0];
//            int nexCol = next[1];
//            if(nextRow >= 0 && nextRow < matrix.length && nexCol >= 0
//                    && nexCol < matrix[0].length
//                    && !visited[nextRow][nexCol]
//                    && matrix[row][col] >= matrix[nextRow][nexCol]) {
//                if(reachBoth(matrix, nextRow, nexCol, visited, oceans)) return true;
//            }
//        }
//        return oceans[0] && oceans[1];
//    }
}

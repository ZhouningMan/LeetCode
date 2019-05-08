package dynamic_programming;

public class MaxAreaOfIsland {

    private static final int[][] DIRECTION = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int max = 0;
        for(int r = 0; r < rows; ++r) {
            for(int c = 0; c < cols; ++c) {
                if(!visited[r][c] && grid[r][c]==1) {
                    max = Math.max(dfs(grid, visited, r, c), max);
                }
            }
        }
        return max;
    }

    //DFS is called only when the cell is not visited and the cell is 1
    //return the total number of connected islands
    private int dfs(int[][] grid, boolean[][] visited, int row, int col) {
        visited[row][col] = true;
        int count = 1;
        for(int[] dir : DIRECTION) {
            int nextR = row + dir[0];
            int nextC = col + dir[1];
            if(nextR >= 0 && nextC >= 0 && nextR < grid.length && nextC < grid[0].length
                && !visited[nextR][nextC] && grid[nextR][nextC] == 1) {
                count += dfs(grid, visited, nextR, nextC);
            }
        }
        return count;
    }

    //Not a good DP solution, this doesn't work
    public int maxAreaOfIslandFailed(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        final int rows = grid.length;
        final int cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        int max = 0;
        for(int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if(grid[r][c] == 0) {
                    dp[r][c] = 0;
                } else {
                    if(r == 0 && c == 0) {
                        dp[r][c] = 1;
                    } else if(r == 0) {
                        dp[r][c] = dp[r][c-1] + 1;
                    } else if(c == 0) {
                        dp[r][c] = dp[r-1][c] + 1;
                    } else {
                        dp[r][c] = dp[r-1][c] + dp[r][c - 1] + 1;
                    }
                }
                max = Math.max(dp[r][c], max);
            }
        }
        return max;
    }
}

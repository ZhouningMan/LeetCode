package dynamic_programming;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        for(int r = rows - 1; r >= 0; r--) {
            for(int c = cols - 1; c >= 0; c--) {
                if(r == rows - 1) {
                    dp[r][c] = grid[r][c] + dp[r][c + 1];
                } else if(c == cols - 1) {
                    dp[r][c] = grid[r][c] + dp[r + 1][c];
                } else {
                    dp[r][c] = grid[r][c] + Math.min(dp[r + 1][c], dp[r][c + 1]);
                }
            }
        }
        return dp[0][0];
    }
}

package dynamic_programming;

import java.util.Arrays;

public class CherryPickup {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n][n][n]; //not allocating extra memory
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                for(int k = 0; k < n; ++k) {
                    if(i == 0 && j ==0 && k ==0) { //base case.
                        dp[0][0][0] = grid[0][0];
                        continue;
                    }
                    int m = i + j - k;
                    //invalid move
                    if(m < 0 || m >=n) continue;//we shouldn't break
                    //one path enter a thorn
                    if(grid[i][j] == -1 || grid[k][m] == -1) continue;
                    int cherries = dp[i][j][k];
                    //Dealing with other edge cases by checking against zero.
                    if(i-1>=0) cherries = Math.max(dp[i-1][j][k], cherries);
                    if(j-1>=0) cherries = Math.max(dp[i][j-1][k], cherries);
                    if(i-1>= 0 && k-1 >=0)  cherries = Math.max(dp[i-1][j][k-1], cherries);
                    if(j-1 >= 0 && k -1 >=0) cherries = Math.max(dp[i][j-1][k-1], cherries);
                    if(cherries == -1) continue; //all previous path are invalid
                    //dont' double count
                    if(i == k) dp[i][j][k] = cherries + grid[i][j];
                    else dp[i][j][k] = cherries + grid[i][j] + grid[k][m];
                }
            }
        }
        return dp[n-1][n-1][n-1] < 0 ? 0 : dp[n-1][n-1][n-1];
    }

    public static void test() {
        new CherryPickup().cherryPickup(new int[][]{{0,1,-1},{1,0,-1},{1,1,1}});
    }
}

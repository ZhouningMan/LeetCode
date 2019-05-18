package dynamic_programming;

@SuppressWarnings("Duplicates")
public class UniquePaths {
    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];
        for(int r = m - 1; r >=0 ; r--) {
            for(int c= n -1; c >= 0; c--) {
                if(r == m -1) dp[m-1][c] = 1;
                else if(c == n -1)dp[r][n-1] = 1;
                else {
                    dp[r][c] = dp[r + 1][c] +dp[r][c+1];
                }
            }
        }

        return dp[0][0];
    }

    public int uniquePathsMemoryEfficient(int m, int n) {

        int[] dp = new int[n];
        for(int r = m - 1; r >=0 ; r--) {
            for(int c= n -1; c >= 0; c--) {
                if(r == m-1) dp[c] = 1;
                else if(c == n -1) dp[c] = 1;
                else {
                    //operand dp[c] is for r + 1 row
                    //and operand dp[c+1] is for c + 1 column
                    dp[c] = dp[c]  + dp[c +1];
                }
            }
        }

        return dp[0];
    }
}

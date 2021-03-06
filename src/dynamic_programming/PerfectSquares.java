package dynamic_programming;

import java.util.Arrays;

public class PerfectSquares {
    public int numSquares(int n) {
        if(n <=0) return 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <=n; ++i) {
            //don't do j*j < i, that could overflow.
            for(int j = 1; j <= i/j; ++j) {
                //cost function.
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }
        return dp[n];
    }
}

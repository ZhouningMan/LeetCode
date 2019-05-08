package dynamic_programming;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if(s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        dp[0] = 0;
        int max = 0;
        for(int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                dp[i] = 0;
            } else {
                int j = i - dp[i -1] - 1;
                if(j >= 0 && s.charAt(j) == '(') {
                    dp[i] = 2 + dp[i - 1];
                    if(j - 1 >= 0){ //we past matches as well.
                        dp[i] += dp[j -1];
                    }
                    //put max else we can reduce another looping
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }
}

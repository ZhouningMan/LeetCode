package dynamic_programming;

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        for(int si = s.length(); si>=0; si--) {
            for(int pi = p.length(); pi >= 0; pi--) {
                if(pi == p.length()) {
                    dp[si][pi] = si == s.length();
                } else if(si == s.length()) {
                    if(p.charAt(pi) == '*') dp[si][pi] = dp[si][pi + 1];
                } else if(matchChar(s, si, p, pi)) {
                    dp[si][pi] = dp[si + 1][pi + 1];
                } else if(p.charAt(pi) == '*') {
                    dp[si][pi] = dp[si][pi + 1] || dp[si + 1][pi + 1] || dp[si + 1][pi];
                } else {
                    dp[si][pi] = false;
                }
            }
        }
        return dp[0][0];
    }

    private boolean matchChar(String s, int si, String p, int pi) {
        return p.charAt(pi) == '?' || s.charAt(si) == p.charAt(pi);
    }

    private boolean lackOfUnderstandingOfDP(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for(int si = s.length(); si>=0; si--) {
            for(int pi = p.length(); pi >= 0; pi--) {
                if(si == s.length() && pi == p.length()) {
                    dp[si][pi] = true;
                } else if(pi == p.length()) {
                    dp[si][pi] = false;
                } else if(si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')) {
                    dp[si][pi] = dp[si + 1][pi + 1];
                } else if (p.charAt(pi) == '*') {
                    for(int k = si; k <= s.length(); k++) {
                        if(dp[k][pi + 1]) {
                            dp[si][pi] = true;
                            break;
                        }
                    }
                } else {
                    dp[si][pi] = false;
                }
            }
        }
        return dp[0][0];
    }

    public static void test() {
        new WildcardMatching().isMatch("cb", "?a");
    }
}

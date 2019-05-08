package dynamic_programming;

public class RegularExpressionMatching {
    public static void test() {
        System.out.println(new RegularExpressionMatching().isMatch("aaa", "ab*a*c*a"));
    }

    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //i, j represents length, not index
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                if (j == 0) {
                    dp[i][j] = i == 0;
                } else if (i == 0) {
                    if (j%2 == 0 && p.charAt(j - 1) == '*') {
                        dp[i][j] =  dp[i][ j - 2];
                    }
                } else if (matchChar(s, i - 1, p, j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (matchChar(s, i - 1, p, j - 2)) {
                        //if the character is matched. there are three conditions
                        //we don't match any character in s, we match a single character in s
                        //or we match multiple character in S(this is the most tricky part)
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2]; //we ignore "c*" pair
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    private boolean matchChar(String s, int si, String p, int pi) {
        return (p.charAt(pi) == '.' || s.charAt(si) == p.charAt(pi));
    }
    //    public boolean isMatch(String s, String p) {
//        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
//        int slen = s.length();
//        int plen = p.length();
//        for(int si = slen; si >=0; ) {
//            for(int pi = plen; pi >=0;) {
//                if(si == slen && pi == plen) {
//                    dp[si][pi] = true;
//                } else if(matchChar(s, si, p, pi)) {
//                    dp[si][pi] = dp[si + 1][pi + 1];
//                } else if(p.charAt(pi) == '*' && pi >0) {
//                    char preC = p.charAt(pi - 1);
//                    for(int k = si; k>=0 && s.charAt(k) == preC; k--) {
//                        dp[k][pi] = dp[k][pi + 1];
//                        dp[k][pi - 1] = dp[k][pi + 1];
//                    }
//                    pi--;
//                }
//            }
//            if(si > 0) si--;
//        }
//        return dp[0][0];
//
//    }
//    private  boolean isMatchRecur(String s, int si, String p, int pi, char prev) {
//        if(si >= s.length() && pi >= p.length()) {
//            return true;
//        } else if(pi >= p.length()) {
//            return  false;
//        } if(p.charAt(pi) == '*') {
//            while(si < s.length() && isMatch(s, si, p, pi-1)) si++;
//            return isMatchRecur(s, si, p, pi + 1, prev);
//        } else if(si < s.length() && isMatch(s, si, p, pi)) {
//            return isMatchRecur(s, si + 1, p, pi + 1, p.charAt(pi));
//        } else {
//            return isMatchRecur(s, si, p, pi + 1, p.charAt(pi));
//        }
//    }
}

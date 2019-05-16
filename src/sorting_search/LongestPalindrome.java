package sorting_search;

public class LongestPalindrome {
    public static void test() {
        System.out.println(new LongestPalindrome().longestPalindrome("adabccbabe"));
    }


    public String longestPalindromeDP(String s) {
        if (s == null || s.length() < 1) return "";
        //if we want to know whether s[i:j} is a palindrome or not,
        //we can check whether s[i+1:j-1] is a palindrome or not.
        //base case when i == j or s[i] == s[j] when j - i = 1
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0;
        int start = 0, end = 0;
        for(int i = s.length() - 1; i >= 0; i--) {
            for(int j = i; j < s.length(); j++) { //j has to be in the explored space of i.
                //grow from base case
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i < 3) || dp[i+1][j-1]);
                int len = j - i + 1;
                if(dp[i][j] && len > max) {
                    max = len;
                    start= i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }


    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        System.out.println(start + " " + end);
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}

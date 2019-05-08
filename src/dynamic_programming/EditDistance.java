package dynamic_programming;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        //dp[i][j] means the minimum cost matching word1.substring(i) to word2.substring(j);
        int[][] dp = new int[word1.length() + 1][ word2.length() + 1];
        for(int i = word1.length(); i >=0 ; i--) {
            for(int j = word2.length(); j >=0; j--) {
                if(i == word1.length()) {
                    dp[i][j] = word2.length() - j;
                } else if(j == word2.length()) {
                    dp[i][j] = word1.length() - i;
                } else if(word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1];
                } else {
                    dp[i][j] = 1 + min(dp[i + 1][j], dp[i][j + 1], dp[i + 1][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    private int min(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }
}

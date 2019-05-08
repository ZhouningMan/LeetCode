package dynamic_programming;

import java.util.*;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordMap = new HashSet<>(wordDict);
        Deque<Integer> matchedIndex = new ArrayDeque<>();
        matchedIndex.push(s.length());
        for(int index = s.length() - 1; index >= 0; index--) {
            for(Integer end : matchedIndex) {
                String toCheck = s.substring(index, end);
                if(wordMap.contains(toCheck)) {
                    matchedIndex.push(index);
                    break;
                }
            }
        }
        return matchedIndex.peek() == 0;
    }

    public boolean wordBreakDP(String s, List<String> wordDict) {
        Set<String> wordMap = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;
        for(int i = s.length() - 1 ; i>=0; i--) {
            for (int j = s.length(); j >=i; j--) {
                if(dp[j] && wordMap.contains(s.substring(i, j))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
}

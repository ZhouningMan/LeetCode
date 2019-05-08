package backtracking;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        var dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)) return 0;
        var queue = new ArrayDeque<String>();
        queue.offer(beginWord);
        var visited = new HashSet<String>();
        int level = 0;
        while(!queue.isEmpty()) {
            level++;
            int size = queue.size();
            visited.add(beginWord);
            for(int i = 0; i < size; ++i) { //search each level
                String curr = queue.poll();
                if(curr.equals(endWord)) return level;
                //exhaust all one char away characters from current character
                for(int j = 0; j < curr.length(); ++j) {
                    for(char nextC = 'a'; nextC <= 'z'; ++nextC) {
                        //it is more efficient to use char array, I can just mention it during the interview.
                        String next = curr.substring(0, j) + nextC + curr.substring(j + 1);
                        if(!visited.contains(next) && dict.contains(next)) { //filter bad candidate
                            queue.offer(next);
                            //it is critical to put visited here because
                            //we are using two loops, and this can help us eliminate a lot of work
                            visited.add(next);
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static void test() {
        var result = new WordLadder().ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log","cog"));
        System.out.println(result);
    }
}

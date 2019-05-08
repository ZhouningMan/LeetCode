package backtracking;

import java.util.*;

public class WordLadderII {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Set<String>> parentPointer = new HashMap<>();
        parentPointer.put(beginWord, new HashSet<>());
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        boolean found = false;
        //standard BFS
        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            Set<String> levelSeen = new HashSet<>();
            for (int i = 0; i < size; ++i) { //search each level
                String curr = queue.poll();
                if (curr.equals(endWord)) {
                    found = true;
                    break;
                }
                //exhaust all one char away characters from current character
                char[] chars = curr.toCharArray();
                for (int j = 0; j < curr.length(); ++j) {
                    char prev = chars[j];
                    for (char nextC = 'a'; nextC <= 'z'; ++nextC) {
                        //it is more efficient to use char array, I can just mention it during the interview.
                        chars[j] = nextC;
                        String next = new String(chars);
                        if (!visited.contains(next) && dict.contains(next)) { //filter bad candidate
                            //keep parent pointer to current node
                            parentPointer.computeIfAbsent(next, (k) -> new HashSet<>()).add(curr);
                            queue.offer(next);
                            levelSeen.add(next);
                        }
                    }
                    chars[j] = prev;
                }
            }
            //only after we have search entire level, we add those seen words to the visited set
            visited.addAll(levelSeen);
        }
        //create the path from the parent pointer.
        List<List<String>> result = new ArrayList<>();
        Deque<String> path = new ArrayDeque<>();
        path.push(endWord);
        createAllPaths(parentPointer, parentPointer.get(endWord), result, path);
        return result;
    }

    private void createAllPaths(Map<String, Set<String>> parentPointer,
                                Set<String> parents,
                                List<List<String>> result,
                                Deque<String> path) {
        if (parents == null) { //result not found
            return;
        } else if (parents.isEmpty()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (String parent : parents) {
            path.push(parent);
            createAllPaths(parentPointer, parentPointer.get(parent), result, path);
            path.pop();
        }
    }
}

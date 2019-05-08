package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSquares {

    public static class Node {
        String val;
        Node[] next = new Node[26];
    }

    public List<String> collect(Node root, List<String> result, String prefix, int d) {
        if(root == null) return result;
        if(d  < prefix.length()) {
            root = root.next[prefix.charAt(d) - 'a'];
            collect(root, result, prefix, d + 1);
        } else {
            if(root.val != null) result.add(root.val);
            for(Node next : root.next) {
                collect(next, result, prefix, d);
            }
        }
        return result;
    }

    public Node append(Node root, String val, int i) {
        if(root == null) root = new Node();
        if(i == val.length()) {
            root.val = val;
            return root;
        }
        root.next[val.charAt(i) - 'a'] = append(root.next[val.charAt(i) - 'a'], val, i+1);
        return root;
    }

    public List<List<String>> wordSquares(String[] words) {
        Node trie = new Node();
        for(String word : words) {
            append(trie, word, 0);
        }

        List<List<String>> result = new ArrayList<>();
        for(String s : words) {
            List<String> candidate = new ArrayList<>();
            candidate.add(s);
            find(trie, result, candidate, s);
        }
        return result;
    }

    private void find(Node trie, List<List<String>> result, List<String> candidate, String first) {
        if(candidate.size() == first.length()) {
            result.add(new ArrayList<>(candidate));
            return;
        }
        int index = candidate.size();
        StringBuilder sb = new StringBuilder();
        for (String aCandidate : candidate) {
            sb.append(aCandidate.charAt(index));
        }

        List<String> prefixMatched = collect(trie, new ArrayList<>(), sb.toString(), 0);
        for (String aPrefixMatched : prefixMatched) {
            candidate.add(aPrefixMatched);
            find(trie, result, candidate, first);
            candidate.remove(candidate.size() - 1);
        }
    }



public static class Solution1 {
    //Bad solutions, but there are some good things to learn from there
    public List<List<String>> wordSquares(String[] words) {
        Map<Character, List<String>> wordsMap = new HashMap<>();
        for(String s : words) {
            wordsMap.computeIfAbsent(s.charAt(0), key -> new ArrayList<>()).add(s);
        }

        List<List<String>> squares = new ArrayList<>();
        for(String s: words) {
            List<List<String>> squresByWord = new ArrayList<>();
            List<String> candidate = new ArrayList<>();
            candidate.add(s);
            wordSquarePerWord(wordsMap, squresByWord, candidate, s, 1);
            squares.addAll(squresByWord);
        }
        return  squares;
    }

    private void wordSquarePerWord(Map<Character, List<String>> map, List<List<String>> result,  List<String> candidate, String first, int i) {
        if(!isValid(candidate, 1, i)) return;
        if(candidate.size() == first.length()  ) {
            result.add(new ArrayList<>(candidate));
            return;
        }
        if(i >= first.length()) return ;
        List<String> words = map.get(first.charAt(i));
        if(words == null) return;
        for(String word: words) {
            candidate.add(word);
            //don't do i++; because that will impact next iteration.
            wordSquarePerWord(map, result, candidate, first, i + 1);
            candidate.remove(i );
        }
    }

    private boolean isValid(List<String> candidate, int i, int limit) {
        if(i >= limit -1 ) return  true;
        if(isValid(candidate, i+1, limit)) {
            String hor = candidate.get(i).substring(i, limit);
            StringBuilder builder = new StringBuilder();
            for(int j = i; j < limit; ++j) {
                builder.append(candidate.get(j).charAt(i));
            }
            return hor.equals(builder.toString());
        }
        return false;
    }
}
}

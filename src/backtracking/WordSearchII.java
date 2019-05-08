package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII {

    static class TrieNode {
        String val;
        TrieNode[] next = new TrieNode[26];
    }
    //return root rooted at the given node.
    private TrieNode insert(TrieNode node, String val, int i) {
        if(i == val.length()) return node;
        char c = val.charAt(i);
        int index = c - 'a';
        if(node.next[index] == null) node.next[index] = new TrieNode();
        if(i == val.length() - 1) {
            node.next[index].val = val; //first node has no value, so we always deal with child node
        }
        node.next[index] = insert(node.next[index], val, i+1);
        return node;
    }

    private TrieNode startWith(TrieNode root, String prefix) {
        TrieNode current = root;
        for(int i =0; i < prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if(current.next[c - 'a'] == null) return null;
            current = current.next[c - 'a'];
        }
        return current;
    }

    public List<String> findWords(char[][] board, String[] words) {
        if(board == null) return new ArrayList<>();

        TrieNode trie = new TrieNode();
        for(String s: words) {
            insert(trie, s, 0);
        }

        Set<String> result = new HashSet<>();
        for(int row = 0; row < board.length; ++row) {
            for(int col = 0; col < board[row].length; ++col) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                dfs(board, trie, result, visited, new StringBuilder(), row, col);
            }
        }
        return new ArrayList<>(result);
    }

    private void dfs(char[][] board, TrieNode trie, Set<String> result, boolean[][] visited, StringBuilder sb, int row, int col) {
        if(row >= board.length || row < 0|| col >= board[0].length || col < 0) return;
        if(visited[row][col]) return;
        sb.append(board[row][col]);
        visited[row][col] = true;
        TrieNode match = startWith(trie, sb.toString());
        if(match == null) {
            backtrack(visited, sb, row, col);
            return;
        }

        if(match.val != null) {
            result.add(match.val);
        }
        dfs(board, trie, result, visited, sb, row + 1 , col);
        dfs(board, trie, result, visited, sb, row -1 , col);
        dfs(board, trie, result, visited, sb, row , col + 1);
        dfs(board, trie, result, visited, sb, row, col - 1);
        backtrack(visited, sb, row, col);
    }

    private void backtrack(boolean[][] visited, StringBuilder sb, int row, int col) {
        sb.deleteCharAt(sb.length() - 1);
        visited[row][col] = false;
    }
}

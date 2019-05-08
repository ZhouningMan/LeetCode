package design;

public class WordDictionary {
    static class TrieNode {
        boolean val;
        TrieNode[] children = new TrieNode[26];
    }

    private final TrieNode root = new TrieNode();

    /** Initialize your data structure here. */
    public WordDictionary() {
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for(int i = 0 ; i < word.length(); i++) {
            char c = word.charAt(i);
            if(node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.val = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word);
    }

    private boolean search(TrieNode subTrie, String suffix) {
        if(suffix.isEmpty()) {
            return subTrie != null && subTrie.val;
        } else if(subTrie == null) {
            return false;
        } else {
            char c = suffix.charAt(0);
            suffix = suffix.substring(1);
            if(c != '.') {
                return search(subTrie.children[c - 'a'], suffix);
            } else {
                for(int i = 0 ; i < 26; i++) {
                    if(search(subTrie.children[i], suffix)) return true;
                }
                return false;
            }
        }
    }
}

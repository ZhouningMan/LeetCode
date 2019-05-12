package trie;

@SuppressWarnings("Duplicates")
public class LongestCommonPrefix {
    static class Node {
        int count; //extra information
        //don't need an extra character, the index contains this information
        //char c;
        Node[] children = new Node[26];

        //boolean isEnd; we are not using it so we don't need it
    }

    public String longestCommonPrefix(String[] strs) {
        Node root = new Node();
        for(String s : strs) add(s, root, 0);
        StringBuilder prefix = new StringBuilder();
        find(root, prefix, strs.length);
        return prefix.toString();
    }

    private void add(String s, Node root, int i) {
        if(i == s.length()) return;
        char c = s.charAt(i);
        Node child = root.children[c - 'a'];
        if(child == null) {
            child = new Node();
            root.children[c - 'a'] = child;
        }
        child.count++;
        add(s, child, i+1);
    }

    private void find(Node root, StringBuilder sb, int len) {
        for(int i = 0; i < 26; ++i) {
            Node child = root.children[i];
            if(child != null) {
                if(child.count == len) {
                    //'a' + i ==> integer
                    // 10 is within bound and can be checked at compile time
                    //'a' + 10 ==> character
                    sb.append((char)('a' + i));
                    find(child, sb, len);
                }
                return;
            }
        }
    }
}

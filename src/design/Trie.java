package design;

public class Trie {
    private final Node root = new Node();

    public Trie() {
    }

    public void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); ++i) {
            if (curr.children[word.charAt(i) - 'a'] == null) {
                curr.children[word.charAt(i) - 'a'] = new Node();
            }
            curr = curr.children[word.charAt(i) - 'a'];
        }
        curr.existed = true;
    }

    public boolean search(String word) {
        Node node = findNode(word);
        return node != null && node.existed;
    }

    public boolean startsWith(String prefix) {
        Node node = findNode(prefix);
        return node != null;
    }

    private Node findNode(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); ++i) {
            Node child = curr.children[prefix.charAt(i) - 'a'];
            if (child == null) return null;
            curr = child;
        }
        return curr;
    }

    private static class Node {
        boolean existed;
        //boolean hasChildren; //I don't need this
        Node[] children = new Node[26];
    }
}

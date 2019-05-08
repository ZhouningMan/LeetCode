package tree_graph;

public class Node {
    int val;
    Node left;
    Node right;

    Node(int x) {
        val = x;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

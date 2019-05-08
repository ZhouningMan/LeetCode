package tree_graph;

public class PopulatingNextRightPointersInEachNode {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}
        public Node(int val) {
            this.val = val;
        }
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    ", next=" + next +
                    '}';
        }
    }

    public Node connect(Node root) {
        updateNext(root, null);
        return root;
    }

    private void updateNext(Node root, Node right) {
        if(root == null) return;
        if(right != null) {
            root.next = right;
            updateNext(root.right, right.left);
        } else {
            updateNext(root.right, null);
        }
        updateNext(root.left, root.right);
    }
}

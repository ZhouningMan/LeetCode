package tree_graph;

import static tree_graph.PopulatingNextRightPointersInEachNode.Node;

public class PopulatingNextRightPointersInEachNodeII {

    public Node connect(Node root) {
        updateNext(root, null);
        return root;
    }

    public Node connectIterative(Node root) {
        Node leftMost = root;
        while(leftMost != null) {
            Node level = leftMost;
            //create a dummy node. This is a great technique so we don't
            //care whether a node has a child or not!!
            Node nextLevelLeftMost = new Node();
            Node curr = nextLevelLeftMost;
            while(level != null) {
                if(level.left != null) {
                    curr.next = level.left;
                    curr = level.left;
                }
                if(level.right != null) {
                    curr.next = level.right;
                    curr = level.right;
                }
                level = level.next;
            }
            //this is the essence of this problem
            leftMost = nextLevelLeftMost.next;
        }
        return root;
    }


    private void updateNext(Node root, Node next) {
        if(root == null) return;
        root.next = next;
        //find the left most node
        Node leftMostOfNext = null;
        while(next != null && leftMostOfNext == null) {
            leftMostOfNext = next.left == null ? next.right : next.left;
            next = next.next;
        }

        if(root.right == null) {
            updateNext(root.left, leftMostOfNext);
        } else {
            //we MUST update right first because the way we find leftMostOfNext
            //depends on next.next which is only set if the right tree has the next
            //setup
            updateNext(root.right, leftMostOfNext);
            updateNext(root.left, root.right);
        }
    }


    public static void test() {
        Node root = new Node(1,
                new Node(2, new Node(4, new Node(7), null), new Node(5)),
                new Node(3, null, new Node(6, null, new Node(8))));
        new PopulatingNextRightPointersInEachNodeII().connectIterative(root);
    }
}

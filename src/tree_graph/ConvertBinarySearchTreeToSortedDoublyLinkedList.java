package tree_graph;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    public Node treeToDoublyList(Node root) {
        if(root == null) return null;
        Node smallest = root;
        while(smallest.left != null) {
            smallest = smallest.left;
        }
        return convert(root, smallest);
    }

    /**
     * Straighten BT rooted at node, and then connect the end to successor
     * @param node
     * @param successor
     * @return
     */
    private Node convert(Node node, Node successor) {
        if(node == null) return successor;
        //straighten left and connect left to current node
        Node smallestLeft = convert(node.left, node);
        //straighten right and connect the tail of right to whatever successor it is
        Node smallestRight = convert(node.right, successor);
        //link right and left together.
        node.right = smallestRight;
        smallestRight.left = node;
        return smallestLeft;
    }
}

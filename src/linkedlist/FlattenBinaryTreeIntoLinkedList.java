package linkedlist;

public class FlattenBinaryTreeIntoLinkedList {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

//    public void flatten(TreeNode root) {
//         flatten(root, root);
//    }

    /**
     * This solution chain head --> flattened left subtree which is then chain to flattened right subtree.
     * connect head --> flattened subRoot.
     * @param subRoot
     * @param head
     * @return tail of each sub tree
     */
    public TreeNode flatten(TreeNode subRoot, TreeNode head) {
        if(subRoot == null) return head;
        TreeNode right = subRoot.right;
        TreeNode leftTail = flatten(subRoot.left, subRoot);
        TreeNode rightTail = flatten(right, leftTail);
        subRoot.left = null;
        if(head != subRoot) { //for special case when we have root.
            head.right = subRoot;
        }
        return rightTail;
    }

}

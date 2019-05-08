package tree_graph;

public class InorderSuccessortBST {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null) return null;

        if(root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode successor = inorderSuccessor(root.left, p);
            return successor == null?  root  : successor;
        }
    }
}

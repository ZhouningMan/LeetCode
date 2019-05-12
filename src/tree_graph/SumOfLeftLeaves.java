package tree_graph;

public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        return sumLeaf(root, false);
    }

    private int sumLeaf(TreeNode root, boolean left) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) {
            return left ? root.val : 0;
        }

        int lVal = sumLeaf(root.left, true);
        int rVal  = sumLeaf(root.right, false);
        return lVal + rVal;
    }
}

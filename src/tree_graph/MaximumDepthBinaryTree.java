package tree_graph;

public class MaximumDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode root, int depth) {
        if(root == null) return depth;
        depth++;
        int result = Math.max(depth, helper(root.left, depth));
        result = Math.max(result, helper(root.right, depth));
        return result;
    }
}

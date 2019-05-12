package tree_graph;

public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        int[] max = new int[] {Integer.MIN_VALUE};
        maxPathSum(root, max);
        return max[0];
    }

    private int maxPathSum(TreeNode root, int[] max) {
        if(root == null) {
            return 0;
        }

        int left = root.left != null?  maxPathSum(root.left, max) : 0;
        int right = root.right != null ? maxPathSum(root.right, max) : 0;

        //BE AWARE of overflow when we initialize value to MIN/MAX
        //optimize result by checking with cross path
        int maxCross = root.val + left + right;
        //find through this node, which might or might not include the child path
        int maxThrough = Math.max(left, right) > 0 ? root.val + Math.max(left, right) : root.val ;
        //optimize result;
        max[0] = Math.max(maxThrough, max[0]);
        max[0] = Math.max(maxCross, max[0]);
        return maxThrough;
    }
}

package tree_graph;

public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null) return null;
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int start, int end) {
        if(start > end) {
            return null;
        } else {
            int maxIdx = -1;
            int max = Integer.MIN_VALUE;
            for(int i = start; i <= end; ++i) {
                if(nums[i] > max) {
                    max = nums[i];
                    maxIdx = i;
                }
            }
            TreeNode parent = new TreeNode(max);
            parent.left = helper(nums, start, maxIdx - 1);
            parent.right = helper(nums, maxIdx + 1, end);
            return parent;
        }
    }
}

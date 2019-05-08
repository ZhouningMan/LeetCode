package tree_graph;

public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        int[] crossMax = new int[]{0};
        int throughMax = diameterBT(root, crossMax);
        return Math.max(crossMax[0], throughMax);
    }

    private int diameterBT(TreeNode root, int[] crossMax) {
        int throughLeft = 0;
        if(root.left != null) {
            throughLeft = 1 + diameterBT(root.left, crossMax);
        }
        int throughRight = 0;
        if(root.right != null) {
            throughRight = 1 + diameterBT(root.right, crossMax);
        }
        crossMax[0] = Math.max(crossMax[0], throughLeft + throughRight);
        return Math.max(throughLeft, throughRight);
    }
}

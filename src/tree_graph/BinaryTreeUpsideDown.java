package tree_graph;

public class BinaryTreeUpsideDown {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return helper(root, null, null);
    }

    private TreeNode helper(TreeNode subRoot, TreeNode turnedLeft, TreeNode turnRight) {
        if(subRoot == null) return null;
        //kind of like preorder traversal, the ordered information is passed down to
        //subtree
        TreeNode left = subRoot.left;
        TreeNode right = subRoot.right;
        subRoot.left = turnedLeft;
        subRoot.right = turnRight;
        if(left == null){ //if left is null, this is the end.
            return subRoot;
        } else {
            return helper(left, right, subRoot);
        }
    }
}

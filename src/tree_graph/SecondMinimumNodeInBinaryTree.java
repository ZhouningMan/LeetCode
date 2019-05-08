package tree_graph;

public class SecondMinimumNodeInBinaryTree {

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;

        int left;
        if (root.left != null && root.left.val > root.val) {
            left = root.left.val; //left is a candidate
        } else {
            //when left.val == root.val, recursively find candidate
            //from left subtree
            left = findSecondMinimumValue(root.left);
        }
        int right;
        if (root.right != null && root.right.val > root.val) {
            right = root.right.val; //right is a candidate
        } else {
            //when right.val == root.val, recursively find candidate
            //from right subtree.
            right = findSecondMinimumValue(root.right);
        }
        //neither candidate is good
        if (left <= root.val && right <= root.val) return -1;
        //left is not good, then right is the answer
        else if (left <= root.val) return right;
        //right is not good, then left is the answer
        else if (right <= root.val) return left;
        //we have two candidate, return the smaller of the two.
        else return Math.min(left, right);
    }
}

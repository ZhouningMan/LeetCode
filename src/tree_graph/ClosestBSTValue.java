package tree_graph;

import java.util.Stack;

public class ClosestBSTValue {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public int closestValue(TreeNode root, double target) {
        return closestNode(root, root, target).val;
    }

    //return the node that is closest to the given value.
    //given the best candidate and a subtree
    private TreeNode closestNode(TreeNode root, TreeNode candidate, double target) {
        if(root == null) return candidate;
        if(Math.abs(root.val - target) < 0.5) return root;
        TreeNode newCandidate = Math.abs(root.val - target) < Math.abs(candidate.val - target) ? root : candidate;
        if(root.val < target) {
            return closestNode(root.right, newCandidate, target);
        } else {
            return closestNode(root.left, newCandidate, target);
        }
    }


    public int closeValueInorder(TreeNode root, double target) {
        TreeNode smaller = null;
        TreeNode bigger = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            //in order operations
            root = stack.pop();
            if(root.val < target) {
                smaller = root;
            } else {
                bigger = root;
                break;
            }
            root = root.right;
        }

        if(smaller == null) return bigger.val;
        if(bigger == null) return smaller.val;
        return Math.abs(smaller.val - target) > Math.abs(bigger.val - target) ? bigger.val : smaller.val;
    }

}

package tree_graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class LowestCommonAncestorOfBinaryTree {
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> pDeque = new ArrayDeque<>();
        findNode(root, p, pDeque);
        Deque<TreeNode> qDeque = new ArrayDeque<>();
        findNode(root, q, qDeque);
        TreeNode ancestor = null;
        while(pDeque.peekLast() == qDeque.peekLast()) {
            //only need to store the value from one deque
             ancestor = pDeque.pollLast();
             //but need to remove the node from both end
            qDeque.pollLast();
        }
        return ancestor;
    }

    private boolean findNode(TreeNode root, TreeNode node, Deque<TreeNode> stack) {
        if(root == null) return false;
        //It is important to push the root node rather than the node
        //to the stack, since we will be using referece comparison.
        stack.push(root);
        if(root.val == node.val) {
            return true;
        }

        if(findNode(root.left, node, stack)) return true;
        else if(findNode(root.right, node, stack)) return true;
        else {
            stack.pop();
            return false;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //if matching any node, return the match. This node could be the LCA
        //but this is just a possibility
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //if we have a match in the left and right, then the current node is the LCA
        if(left != null && right != null) return root;
        //if only left has a match, then it is in the left
        else if(left != null) return left;
        //if right subtree has match, then it is in the right
        else if(right != null) return right;
        else return null;
    }


    public static void test() {
        TreeNode root = new TreeNode(3, new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
                new TreeNode(1, new TreeNode(0), new TreeNode(8)));
        new LowestCommonAncestorOfBinaryTree().lowestCommonAncestor(root, new TreeNode(5), new TreeNode(4));
    }
}

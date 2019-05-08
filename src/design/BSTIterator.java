package design;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTIterator {
    private final Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        buildStak(root);
    }

    public int next() {
        TreeNode next = stack.pop();
        buildStak(next.right);
        return next.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void buildStak(TreeNode root) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

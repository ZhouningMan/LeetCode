package array_string;

import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LongestUnivaluePath {

//    public int longestUnivaluePath(TreeNode root) {
//        if(root == null) return 0;
//        return dfs(root);
//    }
//
//
//    private int dfs(TreeNode subroot) {
//        int countL = 0;
//        int countR = 0;
//        if(subroot.left != null) {
//            countL = dfs(subroot.left);
//            if(subroot.val == subroot.left.val) countL++;
//        }
//        if(subroot.right != null) {
//            countR = dfs(subroot.right);
//            if(subroot.val == subroot.right.val) countR++;
//        }
//
//         //this is not right because whatif you have something like 1->1->1->1->1->2->2->2
//        if(subroot.left != null && subroot.right != null && subroot.left.val == subroot.right.val
//            && subroot.left.val == subroot.val) {
//            return countL + countR;
//        } else {
//            return Math.max(countL, countR);
//        }
//    }

//    private int kernel = 0;
//    private Map<TreeNode, Integer> nodeKernel = new HashMap<>();
//    public int longestUnivaluePath(TreeNode root) {
//        if(root == null) return 0;
//        dfs(root, 0);
//        int[] kernelCount = new int[kernel + 1];
//        for(Integer kernel: nodeKernel.values()) {
//            kernelCount[kernel]++;
//        }
//        int count =  IntStream.of(kernelCount).max().orElse(0);
//        return count > 0 ? count - 1: 0;
//    }
//
//    private void dfs(TreeNode root, int rootK) {
//        nodeKernel.put(root, rootK);
//        if(root.left != null) {
//            if(root.val != root.left.val) {
//                kernel++;
//                dfs(root.left, kernel);
//            } else {
//                dfs(root.left, rootK);
//            }
//        }
//
//        if(root.right != null) {
//            if(root.val != root.right.val) {
//                kernel++;
//                dfs(root.right, kernel);
//            } else {
//                dfs(root.right, rootK);
//            }
//        }
//    }

    private int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        if(root == null) return 0;
        dfs(root);
        return max;
    }

    //return the path starts at provide node
    private int dfs(TreeNode root) {
        int result = 0;
        int lenL = 0;
        if(root.left != null) {
            lenL = dfs(root.left);
            if(root.left.val == root.val) {
                lenL++;
                result= lenL;
            }
        }
        int lenR = 0;
        if(root.right != null) {
            lenR = dfs(root.right);
            if(root.right.val == root.val) {
                lenR++;
                result = Math.max(result, lenR);
            }
        }

        if(root.left != null && root.right != null && root.left.val == root.right.val && root.left.val == root.val) {
            max = Math.max(max, lenL + lenR );
        } else {
            max = Math.max(max, Math.max(lenL, lenR));
        }

        return result;
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

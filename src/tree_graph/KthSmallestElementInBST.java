package tree_graph;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInBST {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> result = new ArrayList<>();
        helper(root, k, 0,  result);
        return result.get(0);
    }

    //return the highest rank fo the subtree.
    //The argument is where we accumulate information
    private int helper(TreeNode subtree, int k, int rank, List<Integer> result) {
        if(subtree == null) return rank;
        if(result.size() == 1) return rank; //optimization
        int left = helper(subtree.left, k , rank, result);
        if(left + 1  == k) { //we found the match
            result.add(subtree.val);
        }
        return helper(subtree.right, k, left + 1, result);
    }
}


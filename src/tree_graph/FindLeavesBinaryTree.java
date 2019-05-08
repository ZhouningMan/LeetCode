package tree_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLeavesBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        if(root == null) return new ArrayList<>();
        Map<Integer, List<Integer>> result = new HashMap<>();
        helper(root, result);
        return new ArrayList<>(result.values());
    }

    //root cannot be null.
    private int helper(TreeNode root, Map<Integer, List<Integer>> result) {
        if(root.left == null && root.right == null) {
            result.computeIfAbsent(0, k -> new ArrayList<>()).add(root.val);
            return 0;
        }
        int level = -1;
        if(root.left != null) level = helper(root.left, result);
        if(root.right != null) level = Math.max(level, helper(root.right, result));
        level++; //increment current level;
        result.computeIfAbsent(level, k -> new ArrayList<>()).add(root.val);
        return level;
    }
}

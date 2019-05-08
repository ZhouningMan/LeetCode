package tree_graph;

import java.util.*;

public class FindDuplicateSubtrees {



    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        postOrder(root, new HashMap<>(), result);
        return result;
    }

    private String postOrder(TreeNode root, Map<String, Integer> paths, List<TreeNode> res) {
        //to help identify tree structure, we need to have a special node to
        //represent null node.
        if(root == null) return "$";
        String key = root.val + ','
                + postOrder(root.left, paths, res) + ','
                + postOrder(root.right, paths, res);
        int count = paths.getOrDefault(key, 0);
        if(count == 1) res.add(root);
        paths.put(key, count + 1);
        return key;
    }

    //Try to do subtree comparison will not work ,and is also too complicated.
    private void find(TreeNode tree1, TreeNode tree2, Set<TreeNode> result) {
        if(tree1 == null || tree2 == null) return;
        if(isSame(tree1, tree2)) {
            result.add(tree1);
            find(tree1.left, tree2.left, result);
            find(tree1.right, tree2.right, result);
        } else {
            find(tree1, tree2.left, result);
            if(!result.contains(tree1)) find(tree1, tree2.right, result);
            if(!result.contains(tree1)) find(tree1.left, tree2, result);
            find(tree1.right, tree2, result);
        }
        find(tree1.left, tree1.right, result);
        find(tree2.left, tree2.right, result);
    }

    private boolean isSame(TreeNode tree1, TreeNode tree2) {
        if(tree1 == null || tree2 == null) return false;
        boolean same = tree1.val == tree2.val;
        if(same && (tree1.left != null || tree2.left != null)) same = isSame(tree1.left, tree2.left);
        if(same && (tree1.right != null || tree2.right != null)) same = isSame(tree1.right, tree2.right);
        return same;
    }
}

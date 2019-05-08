package tree_graph;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        binaryTreePaths(root, new StringBuilder(), paths);
        return paths;
    }

    private void binaryTreePaths(TreeNode root, StringBuilder sb, List<String> paths) {
        if(root == null) {
            return;
        }
        int len = sb.length();
        sb.append(root.val);
        //if it is leaf node, we then need to take the string.
        if(root.left == null && root.right == null) {
            paths.add(sb.toString());
            //backtrack
            sb.delete(len, sb.length());
            return;
        }
        sb.append("->");
        if(root.left == null) {
            binaryTreePaths(root.right, sb, paths);
        } else if(root.right == null) {
            binaryTreePaths(root.left, sb, paths);
        } else {
            binaryTreePaths(root.left, sb, paths);
            binaryTreePaths(root.right, sb, paths);
        }
        //backtrack
        sb.delete(len, sb.length());
    }
}

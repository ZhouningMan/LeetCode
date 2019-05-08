package tree_graph;

import java.util.ArrayList;
import java.util.List;

public class PrintBinaryTree {
    public List<List<String>> printTree(TreeNode root) {
        if(root == null) return null;
        int h = height(root);
        List<List<String>> result = new ArrayList<>(h);
        int cols = (1 << h) - 1;
        for(int i = 0; i < h; ++i) {
            List<String> column = new ArrayList<>(cols);
            result.add(column);
            for(int j = 0; j < cols; ++j) {
                column.add("");
            }
        }

        print(root, result,0, 0, cols - 1);
        return result;
    }

    private int height(TreeNode root) {
        if(root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    private void print(TreeNode root, List<List<String>> result, int level, int begin, int end) {
        if(root == null) return;
        int mid = (end + begin)/2;
        result.get(level).set(mid, String.valueOf(root.val));
        print(root.left, result, level+1, begin, mid - 1);
        print(root.right, result, level+1, mid + 1, end);
    }

    public static void test() {
       // TreeNode tree = new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), new TreeNode(3));

        TreeNode tree = new TreeNode(1, new TreeNode(2), null);
        new PrintBinaryTree().printTree(tree);
    }
}

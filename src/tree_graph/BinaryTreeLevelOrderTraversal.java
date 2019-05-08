package tree_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Deque<TreeNode> level = new ArrayDeque<>();
        level.offer(root);
        while(!level.isEmpty()) {
            int size = level.size();
            List<Integer> levelVals = new ArrayList<>();
            for(int i = 0; i < size; ++i) {
                TreeNode node = level.poll();
                levelVals.add(node.val);
                if(node.left != null) level.offer(node.left);
                if(node.right != null) level.offer(node.right);
            }
            result.add(levelVals);
        }
        return result;
    }
}

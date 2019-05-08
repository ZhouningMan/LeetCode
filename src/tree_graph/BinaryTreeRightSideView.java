package tree_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; ++i) {
                TreeNode parent = queue.poll();
                if(i == 0) {
                    result.add(parent.val);
                }
                if(parent.right != null) queue.offer(parent.right);
                if(parent.left != null) queue.offer(parent.left);
            }
        }
        return result;
    }
}

package tree_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ZigZagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Deque<TreeNode> odd = new ArrayDeque<>();
        Deque<TreeNode> even = new ArrayDeque<>();
        boolean reverse = false;
        odd.offer(root);
        while(!odd.isEmpty() || !even.isEmpty()) {
            int size = reverse ? even.size() : odd.size() ;
            Deque<TreeNode> queue = reverse ? even : odd;
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < size; ++i) {
                TreeNode node = queue.pollFirst();
                level.add(node.val);
                if(reverse) {
                    if(node.right != null) odd.push(node.right);
                    if(node.left != null) odd.push(node.left);
                } else {
                    if(node.left != null) even.push(node.left);
                    if(node.right != null) even.push(node.right);
                }
            }
            reverse = !reverse;
            result.add(level);
        }
        return result;
    }
}

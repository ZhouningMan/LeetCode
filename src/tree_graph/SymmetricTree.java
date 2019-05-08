package tree_graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SymmetricTree {
    private static final TreeNode NULL = new TreeNode(Integer.MIN_VALUE);
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        Deque<TreeNode> level = new ArrayDeque<>();
        level.add(root);
        while(!level.isEmpty()) {
            int i = 0;
            int j = level.size() - 1;
            List<TreeNode> list = new ArrayList<>(level);
            while(i < j) {
                TreeNode head = list.get(i++);
                TreeNode tail = list.get(j--);
                boolean matched = false;
                if(head == NULL && tail == NULL) {
                    matched = true;
                } else if(head != NULL && tail != NULL && head.val == tail.val) {
                    matched = true;
                }
                if(!matched) return false;
            }
            for(int k = 0; k < list.size(); k++) {
                TreeNode node = level.poll();
                if(node == NULL) continue;
                if(node.left == null){
                    level.offer(NULL);
                } else {
                    level.offer(node.left);
                }
                if(node.right == null) {
                    level.offer(NULL);
                } else {
                    level.offer(node.right);
                }
            }
        }
        return true;
    }

}

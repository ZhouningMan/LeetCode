package tree_graph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        int[] range = new int[2];
        colRange(root, 0, range);
        int size = range[1] - range[0] + 1;
        List<List<Integer>> vOrder = new ArrayList<>(size);
        for(int i =0; i < size; ++i) {
            vOrder.add(i, new ArrayList<>());
        }
        int offset =  0 - range[0];
        vOrderTraversal2(root, vOrder, offset);
        return vOrder;
    }

    private void colRange(TreeNode node, int curr, int[] range) {
        if(node == null) return;
        if(node.left != null) {
            range[0] = Math.min(curr -1, range[0]);
            colRange(node.left, curr - 1, range);
        }
        if(node.right != null) {
            range[1] = Math.max(curr + 1, range[1]);
            colRange(node.right, curr + 1, range);
        }
    }

    public static class ColNode {
        int col;
        TreeNode node;
        ColNode(int col, TreeNode node) {
            this.col= col;
            this.node = node;
        }
    }

    private void vOrderTraversal2(TreeNode root, List<List<Integer>> vOrder,  int offset) {
        Queue<ColNode> queue = new ArrayDeque<>();
        queue.offer(new ColNode(0,root ));
        while (!queue.isEmpty()) {
            ColNode colNode = queue.poll();
            int col = colNode.col;
            vOrder.get( col + offset).add(colNode.node.val);
            if(colNode.node.left != null) {
                queue.offer(new ColNode(col -1, colNode.node.left));
            }
            if(colNode.node.right != null) {
                queue.offer(new ColNode(col + 1, colNode.node.right));
            }
        }
    }


    private void vOrderTraversal(TreeNode root, List<List<Integer>> vOrder, int curr, int offset) {
        if(root == null) return;
        int pos = curr + offset;
        vOrder.get(pos).add(root.val);
        if(root.left != null) vOrderTraversal(root.left, vOrder, curr-1, offset);
        if(root.right != null) vOrderTraversal(root.right, vOrder, curr + 1, offset);
    }
}

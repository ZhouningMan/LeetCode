package tree_graph;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0) return null;

        Map<Integer, Integer> inIndex = new HashMap<>();
        for(int i = 0; i < inorder.length; ++i) {
            inIndex.put(inorder[i], i);
        }
        int[] prePos = new int[1];
        return buildTree(0, preorder.length - 1, prePos, inIndex, preorder);
    }

    public TreeNode buildTree(int min, int max, int[] prePos, Map<Integer, Integer> inIndex, int[] preorder) {
        if(min > max) return null;
        int parent = preorder[prePos[0]];
        TreeNode node = new TreeNode(parent);
        prePos[0]++;
        int index = inIndex.get(parent);
        node.left = buildTree(min, index - 1, prePos, inIndex, preorder);
        node.right = buildTree(index + 1, max, prePos, inIndex, preorder);
        return node;
    }

    public static void test() {
        new ConstructBinaryTreeFromPreorderAndInorder().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
    }
}

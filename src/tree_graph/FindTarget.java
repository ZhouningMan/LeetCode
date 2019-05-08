package tree_graph;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FindTarget {
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        List<TreeNode> inorder = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        //iterative in-order traversal
        while(curr != null || stack.size() > 0) {
            //the stack is scrape space just like a recursive stack
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode node = stack.pop();
            inorder.add(node);
            curr = node.right;
        }
        //two pointers
        for(int i = 0, j = inorder.size() -1; i < j;) {
            int sum = inorder.get(i).val + inorder.get(j).val;
            if(sum == k) return true;
            else if(sum < k) i++;
            else j--;
        }
        return false;
    }



}

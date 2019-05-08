package tree_graph;

public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        return helper(root, new StringBuilder());
    }

    private int helper(TreeNode root, StringBuilder sb) {
        if(root.left == null && root.right == null) {
            sb.append(root.val);
            int sum = Integer.parseInt(sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            return sum;
        } else {
           int sum = 0;
            sb.append(root.val);
           if(root.left != null) sum += helper(root.left, sb);
           if(root.right != null) sum += helper(root.right, sb);
           sb.deleteCharAt(sb.length() - 1);
           return sum;
        }
    }

    //this is a better one since there is no backtracking required as sum lives in stack
    private int sum(TreeNode n, int s){
        if (n == null) return 0;
        if (n.right == null && n.left == null) return s*10 + n.val;
        return sum(n.left, s*10 + n.val) + sum(n.right, s*10 + n.val);
    }
}

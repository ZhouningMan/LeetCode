package tree_graph;

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        boolean nodeSame = false;
        if(p == null & q == null) return true;
        else if(p == null) nodeSame = false;
        else if(q == null) nodeSame = false;
        else if(p.val == q.val) nodeSame = true;

        return nodeSame && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

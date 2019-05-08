package tree_graph;

import java.util.ArrayList;
import java.util.List;

public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null && t == null) return true;
        else if( s== null || t == null) return false;

        int m =  collect(t, -1, null);//size of t
        List<TreeNode> candidates = new ArrayList<>();
        collect(s, m, candidates);
        for(TreeNode candidate : candidates) {
            if(compare(candidate, t)) return true;
        }
        return false;
    }
    //This method does two things, because they are pretty tightly coupled
    private int collect(TreeNode node, int m, List<TreeNode> candidates) {
        if(node == null) return 0;
        int size =  1 + collect(node.left, m, candidates) + collect(node.right, m, candidates);
        if(size == m) {//collect subtree with size of t
            candidates.add(node);
        }
        return size;
    }

    private boolean compare(TreeNode s, TreeNode t) {
        if(s == null && t == null ) return true;
        else if(s == null || t == null) return false;
        return s.val == t.val && compare(s.left, t.left) && compare(s.right, t.right);
    }
}

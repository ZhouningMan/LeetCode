package tree_graph;

public class LowestCommonAncestorOfBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //make sure p <= q
        if (p.val > q.val) return lowestCommonAncestor(root, q, p);
        if (p.val <= root.val && q.val >= root.val) {
            return root;
        } else if (q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        // Value of p
        int pVal = p.val;
        // Value of q;
        int qVal = q.val;
        // Start from the root node of the tree
        TreeNode node = root;
        // Traverse the tree
        while (node != null) {
            // Value of ancestor/parent node.
            int parentVal = node.val;
            if (pVal > parentVal && qVal > parentVal) {
                // If both p and q are greater than parent
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                // If both p and q are lesser than parent
                node = node.left;
            } else {
                // We have found the split point, i.e. the LCA node.
                return node;
            }
        }
        return null;
    }
}

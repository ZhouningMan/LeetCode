package design;

public class SerializeAndDeerializeBinaryTree {
//    private static final TreeNode NULL_NODE = new TreeNode(-1);
//    public static void test() {
//        //TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));
//        SerializeAndDeerializeBinaryTree codec = new SerializeAndDeerializeBinaryTree();
//        TreeNode test = codec.deserialize(codec.serialize(null));
//        System.out.println("DOne");
//    }
//
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        Deque<TreeNode> queue = new ArrayDeque<>();
//        queue.offer(translate(root));
//        StringBuilder sb = new StringBuilder();
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            if (node == NULL_NODE) {
//                sb.append("null,");
//            } else {
//                sb.append(node.val).append(',');
//                queue.offer(translate(node.left));
//                queue.offer(translate(node.right));
//            }
//        }
//        return sb.toString();
//    }
//
//    private TreeNode translate(TreeNode node) {
//        if (node == null) return NULL_NODE;
//        else return node;
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        int begin = 0;
//        int end = data.indexOf(',', begin);
//        TreeNode root = extractNode(data, begin, end);
//        if (root == null) return root;
//        Deque<TreeNode> nodes = new ArrayDeque<>();
//        nodes.push(root);
//        begin = end + 1;
//        while (!nodes.isEmpty()) {
//            TreeNode parent = nodes.poll();
//            end = data.indexOf(',', begin);
//            TreeNode left = extractNode(data, begin, end);
//            begin = end + 1;
//            parent.left = left;
//            if (left != null) nodes.offer(left);
//            end = data.indexOf(',', begin);
//            TreeNode right = extractNode(data, begin, end);
//            begin = end + 1;
//            parent.right = right;
//            if (right != null) nodes.offer(right);
//        }
//        return root;
//    }
//
//    private TreeNode extractNode(String data, int begin, int end) {
//        String str = data.substring(begin, end);
//        return str.equals("null") ? null : new TreeNode(Integer.parseInt(str));
//    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if(root == null){
            sb.append("N,");
            return;
        }
        sb.append(root.val).append(',');
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tokens = data.split(",");
        return deserialize(tokens, new int[]{0});
    }

    private TreeNode deserialize(String[] tokens, int[] i) {
        if(tokens[i[0]].equals("N")) {
            i[0]++;
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.parseInt(tokens[i[0]]));
            i[0]++;
            node.left = deserialize(tokens, i);
            node.right = deserialize(tokens, i);
            return node;
        }
    }
}

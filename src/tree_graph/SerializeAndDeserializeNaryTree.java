package tree_graph;

import java.util.*;

public class SerializeAndDeserializeNaryTree {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(Node root, StringBuilder sb) {
        if(root == null) return;
        int numChildren;
        if(root.children == null) numChildren = 0;
        else numChildren = root.children.size();
        sb.append(root.val).append('-').append(numChildren).append(':');
        for(int i = 0; i < numChildren; ++i) {
            serialize(root.children.get(i), sb);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        //"" split will be size one array !
        if(data.isEmpty()) return null;
        Deque<String> nodes = new ArrayDeque<>(Arrays.asList(data.split(":")));
        return deserialize(nodes);
    }

    private Node deserialize(Deque<String> nodes) {
        String s = nodes.poll();
        String[] info = s.split("-");
        int val = Integer.parseInt(info[0]);
        int numChildren = Integer.parseInt(info[1]);
        Node parent = new Node(val, new ArrayList<>(numChildren));
        for(int i = 0 ; i < numChildren; ++i) {
            parent.children.add(deserialize(nodes));
        }
        return parent;
    }

    public static void test() {
        SerializeAndDeserializeNaryTree codec = new SerializeAndDeserializeNaryTree();
       String content =  codec.serialize(null);
       Node node = codec.deserialize(content);

    }
}

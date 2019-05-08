package tree_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        return cloneNode(node, new HashMap<>());
    }

    private Node cloneNode(Node node, Map<Node, Node> cloned) {
        if(cloned.containsKey(node)) return cloned.get(node);
        Node clone = new Node(node.val, null);
        cloned.put(node, clone);
        List<Node> neighbors = node.neighbors;
        if(neighbors != null) {
            clone.neighbors =  new ArrayList<>(neighbors.size());
            for(Node neighbor: neighbors) {
                clone.neighbors.add(cloneNode(neighbor, cloned));
            }
        }
        return clone;
    }
}

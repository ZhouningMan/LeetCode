package tree_graph;

public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        int[] parent = new int[n];
        for(int i = 0; i < parent.length; ++i) {
            parent[i] = i; //set the parent of every vertex to itself
        }

        for(int i = 0; i < edges.length; ++i) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];

            int p1 = findParent(parent, v1);
            int p2 = findParent(parent, v2);

            if(p1 == p2) return false; //they are already connected
            parent[v1] = v2; //update one's parent to the other
        }

        return  edges.length == n - 1;
    }

    private int findParent(int[] parents, int v) {
        while (parents[v] != v) {
            v = parents[v];
        }
        return v;
    }
}

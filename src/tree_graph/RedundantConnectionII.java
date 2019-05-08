package tree_graph;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RedundantConnectionII {

    //    public int[] findRedundantDirectedConnectionOffset(int[][] edges) {
//
//        int edge1 = -1;
//        int edge2 = -1;
//        //identify two edges that could result in one node having two parents
//        //They might not exist
//        int parent[] = new int[edges.length];
//        Arrays.fill(parent, -1);
//        for(int i = 0; i < edges.length; ++i) {
//            if(parent[edges[i][1] - 1] != -1) {
//                edge1 = parent[edges[i][1] - 1];
//                edge2 = i;
//                break;
//            } else {
//                parent[edges[i][1] -1 ] = i;
//            }
//        }
//        for(int i = 0; i < parent.length; ++i) {
//            parent[i] = i + 1;
//        }
//
//        //find a cycle, ignoring edge2 assuming it is the culprit so if the loops can complete fully,
//        //than that edge is the indeed the problematic edge.
//        for(int i = 0; i < edges.length; ++i) {
//            if(i == edge2) continue; //assuming edge 2 is extra
//            if(isConnected(parent, edges[i])) { //after ignoring edge2, we still have a cycle, that means that edge
//                //is not the problem, the problem is either edge1 which takes precedence of current edge that create a
//                //cycle
//                if(edge1 != -1 && isConnected(parent, edges[edge1])) {
//                    return Arrays.copyOf(edges[edge1], 2);
//                }
//                return Arrays.copyOf(edges[i], 2);
//            } else {
//                union(parent, edges[i]);
//            }
//        }
//        //edge 2 is actually the right edge because once it is removed, all the other edges form a tree.
//        return Arrays.copyOf(edges[edge2], 2);
//    }


    public int[] findRedundantDirectedConnection(int[][] edges) {
        int edge1 = -1;
        int edge2 = -1;
        //identify two edges that could result in one node having two parents
        //They might not exist
        int parent[] = new int[edges.length + 1];
        Arrays.fill(parent, -1);
        for(int i = 0; i < edges.length; ++i) {
            if(parent[edges[i][1]] != -1) {
                edge1 = parent[edges[i][1]];
                edge2 = i;
                break;
            } else {
                parent[edges[i][1]] = i;
            }
        }
        for(int i = 0; i < parent.length; ++i) {
            parent[i] = i;
        }

        //find a cycle, ignoring edge2 assuming it is the culprit so if the loops can complete fully,
        //than that edge is the indeed the problematic edge.
        for(int i = 0; i < edges.length; ++i) {
            if(i == edge2) continue; //assuming edge 2 is extra
            if(isConnected(parent, edges[i])) { //after ignoring edge2, we still have a cycle, that means that edge
                //is not the problem, the problem is either edge1 which takes precedence of current edge that create a
                //cycle
                if(edge1 != -1 && isConnected(parent, edges[edge1])) {
                    return Arrays.copyOf(edges[edge1], 2);
                }
                return Arrays.copyOf(edges[i], 2);
            } else {
                union(parent, edges[i]);
            }
        }
        //edge 2 is actually the right edge because once it is removed, all the other edges form a tree.
        return Arrays.copyOf(edges[edge2], 2);
    }

    private boolean isConnected(int[] parent, int[] edge) {
        return root(parent, edge[0])  == root(parent, edge[1]);
    }

    private int root(int[] parent, int i) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }

    private void union(int[] parent, int[] edge) {
        int root1 = root(parent, edge[0]);
        int root2 = root(parent, edge[1]);
        parent[root2] = root1;
    }
}

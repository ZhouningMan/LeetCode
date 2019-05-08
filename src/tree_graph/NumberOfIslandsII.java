package tree_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslandsII {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] roots = new int[m * n];
        List<Integer> result = new ArrayList<>();
        Arrays.fill(roots, -1);
        int count = 0;
        for(int[] pos : positions) {
            count++;
            int row = pos[0], col = pos[1];
            int idx = n * row + col;
            roots[idx] = idx; //add a new land
            //form a island with connected land
            int[][] neighbors = new int[][]{{row + 1, col},{row -1, col}, {row, col + 1}, {row, col -1}};
            for(int[] ngb : neighbors) {
                if(ngb[0] < 0 || ngb[0] >= m || ngb[1] < 0 || ngb[1] >= n) continue;
                int ngbIdx = ngb[0]*n + ngb[1];
                //if we have a piece of land at the neighboring position
                if(roots[ngbIdx] == -1) continue;
                int ngbRoot = findRoot(roots, ngbIdx);
                int idxRoot = findRoot(roots, idx);
                //only union lands together. Once two piece of disjoint lands are united, reduce the count.
                if(ngbRoot != idxRoot) {
                    roots[ngbRoot] = idxRoot;
                    count--;
                }
            }
            result.add(count);
        }
        return result;
    }

    int findRoot(int[] roots, int idx) {
        while (roots[idx] != idx) {
            //path compression
            roots[idx] = roots[roots[idx]];
            idx = roots[idx];
        }
        return idx;
    }

    private void union(int[] ngb, int[] pos, int[] roots) {
    }
}

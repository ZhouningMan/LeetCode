package tree_graph;

import java.util.ArrayDeque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CutOffTreesForGolfCourse {
    public int cutOffTree(List<List<Integer>> forest) {
        Queue<int[]> trees = findAllTrees(forest);
        int total = 0;
        int[] startLoc = new int[]{0, 0};
        while(!trees.isEmpty()) {
            int[] treeLoc = trees.poll();
            int steps = bfs(forest, startLoc, treeLoc[1], treeLoc[2]);
            if(steps == -1) return -1;
            total += steps;
            startLoc = new int[]{treeLoc[1], treeLoc[2]};
        }
        return total;
    }

    private Queue<int[]> findAllTrees(List<List<Integer>> forest) {
        Queue<int[]> treeQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for(int r = 0; r < forest.size(); ++r) {
            for(int c = 0; c < forest.get(0).size(); ++c) {
                int val = forest.get(r).get(c);
                if(val > 1) {
                    treeQueue.offer(new int[]{val, r, c});
                }
            }
        }
        return treeQueue;
    }

    private int bfs(List<List<Integer>> forest, int[] startLoc, int row, int col) {
        int steps = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(startLoc);
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        visited[startLoc[0]][startLoc[1]] = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; ++i) {
                int[] loc = queue.poll();
                int r = loc[0];
                int c = loc[1];
                if(r == row && c == col) return steps;
                for(int[] dir : dirs) {
                    int nextR = r + dir[0];
                    int nextC = c + dir[1];
                    if(nextR >=0 && nextR < forest.size() && nextC >=0 && nextC < forest.get(0).size() &&
                    forest.get(nextR).get(nextC) > 0 && !visited[nextR][nextC]) {
                        queue.offer(new int[]{nextR, nextC});
                        visited[nextR][nextC] = true;
                    }
                }

            }
            //take one more step
            steps++;
        }
        //not found
        return -1;
    }
}

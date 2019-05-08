package sorting_search;

import java.util.ArrayDeque;
import java.util.Queue;

@SuppressWarnings("Duplicates")
public class ShortestBridge {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int shortestBridge(int[][] A) {
        final int rows = A.length;
        final int cols = A[0].length;
        boolean[][] visited =new boolean[rows][cols];
        int id = 1;
        int[][] ids = new int[rows][cols];
        //assigned component;
        for(int r = 0; r < rows; ++r) {
            for(int c = 0; c < cols; ++c) {
                if(!visited[r][c] && A[r][c] == 1) {
                    dfs(A, ids, visited, r, c, id);
                    id++;
                }
            }
        }
        Queue<int[]> queue = new ArrayDeque<>();
        visited =new boolean[rows][cols]; //reset value to false;
        //push border waters to the queue
        int res = 0;
        for(int r = 0; r < rows; ++r) {
            for(int c =0; c < cols; ++c) {
                if(ids[r][c] == 0) {
                    for(int[] dir : DIRS) {
                        int nextR = r + dir[0];
                        int nextC = c + dir[1];
                        if(nextR >=0  && nextR < rows && nextC >=0 && nextC < cols && !visited[r][c] && ids[nextR][nextC] == 1) {
                            queue.offer(new int[]{r, c});
                            visited[r][c] = true;
                        }
                    }
                }
            }
        }
        while(!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for(int i = 0 ; i < size; ++i) {
                int[] pos = queue.poll();
                int r = pos[0];
                int c = pos[1];
                for(int[] dir : DIRS) {
                    int nextR = r + dir[0];
                    int nextC = c + dir[1];
                    if(nextR >=0  && nextR < rows && nextC >=0 && nextC < cols && !visited[nextR][nextC]) {
                        if(ids[nextR][nextC] == 2) return res; //find another island
                        if(ids[nextR][nextC] == 0) {
                            queue.offer(new int[]{nextR, nextC});
                            visited[nextR][nextC] = true;
                        }
                    }
                }
            }
        }

        return res;
    }

    private void dfs(int[][] A, int[][] ids, boolean[][] visited, int r, int c, int id) {
        visited[r][c] = true;
        ids[r][c] = id;
        for(int[] dir : DIRS) {
            int nextR = r + dir[0];
            int nextC = c + dir[1];
            if(nextR >=0 && nextR < A.length && nextC >= 0 && nextC < A[0].length
                    && !visited[nextR][nextC] && A[nextR][nextC] == 1) {
                dfs(A, ids, visited, nextR, nextC, id);
            }
        }
    }

    public static void test() {
        new Impl2().shortestBridge(new int[][]{{0, 1}, {1, 0}});
    }

    private static class Impl2 {
        public int shortestBridge(int[][] A) {
            final int rows = A.length;
            final int cols = A[0].length;
            boolean[][] visited =new boolean[rows][cols];
            Queue<int[]> queue = new ArrayDeque<>();
            //visit one island only
            //the boolean is used to exit the double loops
            boolean found = false;
            for(int r = 0; r < rows && !found; ++r) {
                for(int c = 0; c < cols && !found; ++c) {
                    if(!visited[r][c] && A[r][c] == 1) {
                        dfs(A, queue, visited, r, c);
                        found = true;
                    }
                }
            }
            //continuously expand the islands
            int res = 0;
            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; ++i) {
                    int[] pos = queue.poll();
                    int r = pos[0];
                    int c = pos[1];
                    for(int[] dir : DIRS) {
                        int nextR = r + dir[0];
                        int nextC = c + dir[1];
                        if(nextR >=0 && nextR < A.length && nextC >= 0 && nextC < A[0].length
                                && !visited[nextR][nextC]) {
                            if(A[nextR][nextC] == 1) return res;
                            visited[nextR][nextC] = true;
                            queue.offer(new int[]{nextR, nextC});
                        }
                    }
                }
                //only increase the size when the islands are not connected
                res++;
            }
            return 0;
        }

        private void dfs(int[][] A, Queue<int[]> queue, boolean[][] visited, int r, int c) {
            visited[r][c] = true;
            queue.offer(new int[]{r, c});
            for(int[] dir : DIRS) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];
                if(nextR >=0 && nextR < A.length && nextC >= 0 && nextC < A[0].length
                        && !visited[nextR][nextC] && A[nextR][nextC] == 1) {
                    dfs(A, queue, visited, nextR, nextC);
                }
            }
        }
    }
}
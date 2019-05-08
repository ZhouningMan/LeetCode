package sorting_search;

import java.util.*;

public class ShortestDistanceFromAllBuildings {
    static class Solution2 {
        //VERY GOOD SOLUTION
        public int shortestDistance(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            //keep track of the cumulative distance from this empty slot [m,n] to all buildings
            int[][] distance = new int[m][n];
            //keep track of how many buildings can reach this [m,n] empty slots
            int[][] reached = new int[m][n];
            int houseCount = 0;
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if (grid[i][j] == 1)
                        houseCount++;

            //do BFS from the building and check the distance to every empty slot
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if (grid[i][j] == 1 && !bfs(grid, i, j, distance, reached, houseCount))
                        return -1;

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if (grid[i][j] == 0 && reached[i][j] == houseCount)
                        min = Math.min(min, distance[i][j]);
            return min;
        }

        private boolean bfs(int[][] grid, int i, int j, int[][] distance, int[][] reached, int houseCount) {
            int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{i, j});
            int level = 0;
            int count = 0;
            while (!q.isEmpty()) {
                int len = q.size();
                level++;
                //BFS keeping track of the level, because level is the distance.
                for (int k = 0; k < len; k++) {
                    int[] current = q.poll();
                    for (int[] direction : directions) {
                        int x = current[0] + direction[0];
                        int y = current[1] + direction[1];
                        int[] nextCell = {x, y};
                        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !visited[x][y]) {
                            if (grid[x][y] == 0) {
                                reached[x][y]++;
                                distance[x][y] += level;
                                q.add(nextCell);
                            } else if (grid[x][y] == 1) {
                                count++;
                            }
                            visited[x][y] = true;
                        }
                    }
                }
            }
            return count == houseCount;
        }
    }

    static class Solution1 {
        public int shortestDistance(int[][] grid) {
            if (grid == null || grid[0] == null) return -1;
            Map<String, int[][]> distanceMap = new HashMap<>();
            //from every building to any other empty cells, find all their distance
            for (int row = 0; row < grid.length; ++row) {
                for (int col = 0; col < grid[0].length; ++col) {
                    if (grid[row][col] == 1) {
                        int[][] dist = new int[grid.length][grid[0].length];
                        distanceMap.put(row + "," + col, dist);
                        bfs(grid, dist, row, col);
                    }
                }
            }
            //For each empty cells, locate their path to every building.
            int min = Integer.MAX_VALUE;
            for (int row = 0; row < grid.length; ++row) {
                for (int col = 0; col < grid[0].length; ++col) {
                    if (grid[row][col] == 0) {
                        int val = 0;
                        for (int[][] dist : distanceMap.values()) {
                            if (dist[row][col] == 0) {
                                val = Integer.MAX_VALUE;
                                break;
                            }
                            val += dist[row][col];
                        }
                        min = Math.min(val, min);
                    }
                }
            }
            return min == Integer.MAX_VALUE ? -1 : min;
        }

        private void bfs(int[][] grid, int[][] dist, int row, int col) {
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            visited[row][col] = true;
            queue.offer(new int[]{row, col});
            dist[row][col] = 0;
            while (!queue.isEmpty()) {
                int[] pos = queue.poll();
                int r = pos[0];
                int c = pos[1];
                int[][] nextPos = new int[][]{{r - 1, c}, {r + 1, c}, {r, c + 1}, {r, c - 1}};
                for (int[] np : nextPos) {
                    int nr = np[0];
                    int nc = np[1];
                    if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == 0 && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        queue.offer(np);
                        dist[nr][nc] = dist[r][c] + 1;
                    }
                }
            }
        }
    }
}

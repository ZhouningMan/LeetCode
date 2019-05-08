package sorting_search;

import java.util.ArrayDeque;
import java.util.Queue;


@SuppressWarnings("Duplicates")
public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0) return;
        int rows = rooms.length;
        int cols = rooms[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        //multi source BFS
        for(int r = 0; r < rows; ++r) {
            for(int c = 0; c < cols; ++c) {
                if(rooms[r][c] == 0) queue.offer(new int[]{r, c});
            }
        }

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            for(int[] dir : directions) {
                int nextR = r + dir[0];
                int nextC = c + dir[1];
                if(nextR >=0 && nextC >=0 && nextR < rooms.length && nextC < rooms[0].length
                        //only visit next position if it is an unvisited empty room
                        //if it is a visited room, it has the shortest distance already
                        && rooms[nextR][nextC] == Integer.MAX_VALUE) {
                    rooms[nextR][nextC] = rooms[r][c] + 1;
                    queue.offer(new int[]{nextR, nextC});
                }
            }
        }
    }

    public void wallsAndGates1(int[][] rooms) {
        if(rooms == null || rooms.length == 0) return;
        int rows = rooms.length;
        int cols = rooms[0].length;
        for(int r = 0; r < rows; ++r) {
            for(int c = 0; c < cols; ++c) {
                if(rooms[r][c] == 0) {
                    sinlgeSourceBFS(rooms, r, c);
                }
            }
        }
    }

    private void sinlgeSourceBFS(int[][] rooms, int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c, 0});
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited =  new boolean[rooms.length][rooms[0].length];
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //I don't need this for loop;
            //the for loop is only required when I need to know the relationship
            //between the current node and its immediately neighbors.
           // for(int i = 0; i < size; ++i) {
                int[] pos = queue.poll();
                int currR = pos[0];
                int currC = pos[1];
                int dist = pos[2];
                rooms[currR][currC] = Math.min(rooms[currR][currC], dist);

                for(int[] dir : directions) {
                    int nextR = currR + dir[0];
                    int nextC = currC + dir[1];
                    if(nextR >=0 && nextC >=0 && nextR < rooms.length && nextC < rooms[0].length
                        && rooms[nextR][nextC] > 0 && !visited[nextR][nextC]) {
                        visited[nextR][nextC] = true;
                        queue.offer(new int[]{nextR, nextC, dist+1});
                    }
                }
            //}
        }
    }
}

package tree_graph;

public class FloodFill {
    private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] visited = new boolean[image.length][image[0].length];
        dfs(image, visited, sr, sc, newColor);
        return image;
    }

    private void dfs(int[][] image, boolean[][] visited, int sr, int sc, int newColor) {
        visited[sr][sc] = true;
        for(int[] dir : DIRS) {
            int nextR = dir[0] + sr;
            int nextC = dir[1] + sc;
            if(nextR >=0  && nextR < image.length && nextC >= 0 && nextC < image[0].length
            && !visited[nextR][nextC] && image[nextR][nextC] == image[sr][sc]) {
                dfs(image, visited, nextR, nextC, newColor);
            }
        }
        image[sr][sc] = newColor;
    }
}

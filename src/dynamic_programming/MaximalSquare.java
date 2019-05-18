package dynamic_programming;

import java.util.ArrayDeque;
import java.util.Deque;

@SuppressWarnings("Duplicates")
public class MaximalSquare {

    public int maximalSquareDP(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int side = 0;
        for(int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if(r == 0 || c == 0) dp[r][c] = matrix[r][c] - '0';
                else if(matrix[r][c] == '1'){
                    dp[r][c] = Math.min(Math.min(dp[r-1][c-1], dp[r-1][c]), dp[r][c-1]) + 1;
                }
                side = Math.max(dp[r][c], side);
            }
        }
        return side * side;
    }

    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int[] heights = new int[matrix[0].length];
        int max = 0;
        for(int r = 0; r < matrix.length; r++) {
            for(int c = 0; c < matrix[0].length; c++) {
                heights[c] = matrix[r][c] == '1' ? heights[c] + 1 : 0;
            }
            max = Math.max(max, square(heights));
        }
        return max;
    }

    private int square(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        for(int i =0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int j = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int side = Math.min(heights[j], i - left - 1);
                max = Math.max(side * side, max);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            int j = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int side = Math.min(heights[j], heights.length - left -1);
            max = Math.max(side * side, max);
        }
        return max;
    }
}

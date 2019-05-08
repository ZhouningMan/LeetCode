package dynamic_programming;

import java.util.Arrays;

public class PaintHouse {
    public int minCost(int[][] costs) {
        //dp means the min cost i for each color: read, blue, green.
        //This information can help us make a choice easily in for the next row.
        int[] dp = new int[3];
        for(int i = 0; i < costs.length; ++i) {
            //critical, I need to make a copy.
            int[] temp =  Arrays.copyOf(dp, 3);
            for(int c = 0; c < 3; ++c) {
                if(i == 0) {
                    dp[c] = costs[0][c];
                } else {
                    //find the cost of painting the last house in different color.
                    //loop through the array except for index equals current index
                    dp[c] = Math.min(temp[(c + 1)%3], temp[(c+2)%3]) + costs[i][c];
                }
            }
        }
        return Math.min(Math.min(dp[0], dp[1]), dp[2]);
    }

    public static void test() {
        new PaintHouse().minCost(new int[][]{{17,2,17},{16,16,5},{14,3,19}});
    }
}

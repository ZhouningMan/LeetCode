package dynamic_programming;

public class PaintHouseII {

    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        final int ROWS = costs.length;
        final int COLS = costs[0].length;
        int min1 = 0, min2 = 0, minIndex = -1;//previous row information
        for(int i = 0; i < ROWS; ++i) {
            //current row information
           int curMin1 = Integer.MAX_VALUE, curMin2 = Integer.MAX_VALUE, curMinIdx = -1;
           for(int j = 0; j < COLS; ++j) {
               int cost;
               //use previous row information to calculate current row information
               if(j == minIndex) cost = costs[i][j] + min2;
               else cost = costs[i][j] + min1;

               if(cost < curMin1) {
                   curMin2 = curMin1;
                   curMin1 = cost;
                   curMinIdx = j;
               } else if(cost < curMin2){
                   curMin2 = cost;
               }
           }
           //update previous row information to current row.
           min1 = curMin1;
           min2 = curMin2;
           minIndex = curMinIdx;
        }
        return min1;
    }

}

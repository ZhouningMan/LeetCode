package recursion;

import javax.print.attribute.IntegerSyntax;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AndroidUnlockPattern {
//    public int numberOfPatterns2(int m, int n) {
//        int total = 0;
//        for(int i = m ; i <= n; ++i) {
//            total += numberOfPatterns(i);
//        }
//        return total;
//    }
//
//    private int numberOfPatterns(int m) {
//        if(m == 1) {
//            return 9;
//        } else {
//            return 4 * startZeroZero(m-1) + 4 * startZeroOne(m -1) + startOneOne(m -1);
//        }
//    }
//
//    private int startZeroZero(int i) {
//        if(i == 1) return 5;
//        else {
//            return 4 * startZeroOne( i -1) + startOneOne( i -1);
//        }
//    }
//
//    private int startOneOne(int i) {
//        if(i == 1) return  8;
//        else return 4 * startZeroZero(i -1) + 4* startZeroOne( i -1);
//    }
//
//    private int startZeroOne(int i) {
//        if(i == 1) return 7;
//        else {
//            return 4 * startZeroZero(i -1) + 2 * startZeroOne( i - 1) + startOneOne( i -1);
//        }
//    }


    public int numberOfPatterns(int m, int n) {
        boolean[][] visited = new boolean[3][3];
        int[][] skipMetric = new int[9][9];
        //The skipMetric is the key to this problem.
        //It feels like there needs 3 dimension array, but no that is not required
        //just need to be a little bit creative.
        //skipMetric[origin][target] = number that must be visited.
        skipMetric[0][2] = skipMetric[2][0] = 1;
        skipMetric[0][6] = skipMetric[6][0] = 3;
        skipMetric[0][8] = skipMetric[8][0] = 4;
        skipMetric[1][7] = skipMetric[7][1] = 4;
        skipMetric[2][8] = skipMetric[8][2] = 5;
        skipMetric[2][6] = skipMetric[6][2] = 4;
        skipMetric[3][5] = skipMetric[5][3] = 4;
        skipMetric[8][6] = skipMetric[6][8] = 7;
        int total = 0;
        for(int i = m ; i <= n; ++i) {
            total += dfs(visited, skipMetric, 0, 0, i -1) * 4;
            total += dfs(visited, skipMetric, 0, 1, i -1) * 4;
            total += dfs(visited, skipMetric, 1, 1, i -1);
        }
        return total;
    }

    private int dfs(boolean[][] visited, int[][] skipMetric, int row, int col, int remaining) {
        if(remaining == 0) {
            return  1;
        }
        visited[row][col] = true;
        int result = 0;
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                int origin = row * 3  + col;
                int target = r * 3 + c;
                int skipNum = skipMetric[origin][target];
                if(!visited[r][c] && (skipNum == 0 || visited[skipNum/3][skipNum%3])) {
                    result += dfs(visited, skipMetric, r, c, remaining - 1);
                    visited[r][c] = false;
                }
            }
        }
        visited[row][col] = false;
        return result;
    }
}

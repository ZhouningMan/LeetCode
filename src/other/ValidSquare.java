package other;

import java.util.Arrays;

public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] points = new int[][]{p1, p2, p3, p4};
        Arrays.sort(points, (a, b) -> {
            int res = Integer.compare(a[0], b[0]);
            return  res == 0 ? Integer.compare(b[1], a[1]) : res;
        });
        long side1 = distSq(points[0], points[1]);
        long side2 = distSq(points[1], points[3]);
        long side3 = distSq(points[3], points[2]);
        long side4 = distSq(points[2], points[0]);
        long diag1 = distSq(points[0], points[3]);
        long diag2 = distSq(points[1], points[2]);
        return  side1 != 0 && side1 == side2 && side2 == side3 && side3 == side4 && diag1 == diag2;
    }

    private long distSq(int[] p1, int[] p2) {
        int diffH = p2[1] - p1[1];
        int diffW = p2[0] - p1[0];
        return diffH* diffH + diffW * diffW;
    }
}

package design;

import java.util.Random;

public class RandomPickWithWeight {
    private final int[] w;
    private final int total;
    private final Random random;

    public RandomPickWithWeight(int[] w) {
        if (w == null || w.length == 0) throw new IllegalArgumentException();
        for (int i = 1; i < w.length; ++i) {
            w[i] += w[i - 1];
        }
        total = w[w.length - 1];
        this.w = w;
        random = new Random();
    }

    public int pickIndex() {
        ////[1,total]
        int val = random.nextInt(total) + 1;
        int lo = 0, hi = w.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (w[mid] == val) {
                return mid;//it is imperative i return mid.
            } else if (w[mid] < val) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }
}

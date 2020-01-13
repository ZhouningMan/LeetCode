package array_string;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> sum2 = new HashMap<>();
        for(int c : C) {
            for(int d : D) {
                int s = c + d;
                sum2.put(s, sum2.getOrDefault(s, 0) + 1);
            }
        }

        int res = 0;
        for(int a : A) {
            for(int b : B) {
                res += sum2.getOrDefault(-(a + b), 0);
            }
        }
        return res;
    }
}

package array_string;

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarrySum {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> accum = new HashMap<>();
        int sum = 0;
        int max = 0;
        for(int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if(sum == k) {
                max = i + 1;
            } else if(accum.containsKey(sum - k)) {
                //we found a window
                //NOTE, (i, j], the beginning index is non-exclusive.
                max = Math.max(i - accum.get(sum - k), max);
            }

            if(!accum.containsKey(sum)) { //do not override entry because that could reduce the possible window
                accum.put(sum, i);
            }
        }
        return max;
    }
}

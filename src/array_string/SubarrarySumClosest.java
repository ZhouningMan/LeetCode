package array_string;

import java.util.Arrays;
import java.util.TreeMap;

public class SubarrarySumClosest {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        if(nums == null || nums.length == 0) {
            throw new IllegalArgumentException("...");
        }

        var sumToIdx = new TreeMap<Long, Integer>();
        long pre_sum = nums[0];
        long diff = Math.abs(nums[0]);
        int start = 0, end = 0;
        sumToIdx.put(pre_sum, 0);
        for(int i = 1; i < nums.length; ++i) {
            pre_sum += nums[i];
            var floor = sumToIdx.floorEntry(pre_sum);
            var ceiling = sumToIdx.ceilingEntry(pre_sum);
            var list = Arrays.asList(floor, ceiling);
            for(var entry : list) {
                if(entry != null && Math.abs(pre_sum - entry.getKey()) < diff) {
                    start = entry.getValue() + 1;
                    end = i;
                    diff = Math.abs(pre_sum - entry.getKey());
                }
            }
            sumToIdx.put(pre_sum, i);
        }
        return new int[]{start, end};
    }

    public static void test() {
        var solution = new SubarrarySumClosest();
        int[] result  = solution.subarraySumClosest(new int[]{-3,1,1,-3,5});
        System.out.print(Arrays.toString(result));
    }
}

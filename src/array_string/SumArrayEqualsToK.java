package array_string;

import java.util.HashMap;
import java.util.Map;

public class SumArrayEqualsToK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        int sum = 0;
        int count = 0;
        for(int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if(sum == k) {
                count++;
            }
            count += sumMap.getOrDefault(sum - k, 0);
            sumMap.compute(sum, (key, val) -> val == null ? 1 : val + 1); //DON'T DO val++
        }
        return count;
    }

    public static void test() {
        new SumArrayEqualsToK().subarraySum(new int[]{0,0,0,0,0,0,0,0,0,0}, 0);
    }
}

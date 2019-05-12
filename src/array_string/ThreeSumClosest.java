package array_string;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        //always initialize with meaningful value
        int result = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length; ++i) {
            int j = i+1;
            int k = nums.length - 1;
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == target) return sum;
                if(sum > target) {
                    k--;
                } else {
                    j++;
                }
                result = Math.abs(result - target) > Math.abs(sum - target) ? sum : result;
            }
        }
        return result;
    }
}

package backtracking;

import java.util.Arrays;

public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] result = new int[]{0};
        helper(nums, target, result);
        return result[0];
    }

    private void helper(int[] nums, int target, int[] result) {
        if(target == 0) {
            result[0]++;
            return;
        }

        for(int i = 0; i < nums.length; i++) {//no change in exploration space
            if(nums[i] > target) break;
            helper(nums, target - nums[i], result);
        }
    }

    public int combinationSum4DP(int[] nums, int target) {
        Arrays.sort(nums);
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i = 1; i <= target; i++) {
            for(int num : nums) {
                if(num > i) break;
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }

}

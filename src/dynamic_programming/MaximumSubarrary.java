package dynamic_programming;

public class MaximumSubarrary {
    public int maxSubArray(int[] nums) {
        if(nums.length == 0) return 0;
        int prior = nums[0];
        int max = prior;
        for(int i = 1; i < nums.length; ++i) {
            prior = Math.max(prior + nums[i], nums[i]);
            max = Math.max(max, prior);
        }
        return max;
    }
}

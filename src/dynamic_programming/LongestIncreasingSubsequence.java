package dynamic_programming;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        //dp[i] means the length of longest increasing subsequence ended at nums[i]
        int[] dp = new int[nums.length];
        int result = 1;
        for(int i = 0; i < nums.length; ++i) {
            dp[i] = 1;
            if(i > 0) {
                int maxPrev = 0;
                for(int j = i -1; j >=0 ; j--) {
                    if(nums[j] < nums[i]) {
                        maxPrev = Math.max(maxPrev, dp[j]);
                    }
                }
                dp[i] += maxPrev;
                result = Math.max(dp[i], result);
            }
        }
        return result;
    }

    public static void test() {
        new LongestIncreasingSubsequence().lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6});
    }
}

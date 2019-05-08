package array_string;

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int sum = 0;
        int minLen = 0;
        for(int i = 0, j = 0; i < nums.length && j < nums.length; j++) {
            sum += nums[j];
            if(sum >= s) {
                if(minLen == 0) minLen =  j - i + 1;
                else minLen = Math.min(minLen,j - i + 1);
                while(i < j) {
                    sum -= nums[i];
                    i++;
                    if(sum < s) break;
                    else minLen = Math.min(minLen, j - i + 1);
                }
            }
        }
        return minLen;
    }
}

package dynamic_programming;

public class HouseRobberII {
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];

        int[] dpSkipFirst = new int[nums.length + 2];
        for(int i = nums.length - 1; i >=1 ; i--) {
            dpSkipFirst[i] = Math.max(nums[i] + dpSkipFirst[i + 2], dpSkipFirst[i + 1]);

        }

        int[] dpSkipLast = new int[nums.length + 2];
        for(int i = nums.length - 2; i >=0; i--) {
            dpSkipLast[i] = Math.max(nums[i] + dpSkipLast[i + 2], dpSkipLast[i + 1]);
        }

        return Math.max(dpSkipFirst[1], dpSkipLast[0]);
    }

}

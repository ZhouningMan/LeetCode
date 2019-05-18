package dynamic_programming;

public class JumpGame {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length < 2) return true;
        int target = nums.length - 1;
        for(int i = nums.length - 2; i >=0; i--) {
            //as long as we can use maximum nums[i] jumps to reach next target
            //we are good
            if(nums[i] >= target - i) {
                target = i;
            }
        }
        //if the target is the first step which is where we starts,
        //we are good.
        return target == 0;
    }
}

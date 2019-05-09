package other;

import java.util.Random;

public class RandomPickIndex {
    private final int[] nums;
    private final Random random;
    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int idx = -1;
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] == target) {
                count++;
                // 1/possibility to keep the last match
                if(random.nextInt(count) == 0) {
                    idx = i;
                }
            }
        }
        return idx;
    }
}

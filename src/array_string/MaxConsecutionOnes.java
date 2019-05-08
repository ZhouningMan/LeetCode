package array_string;

import java.util.ArrayList;

public class MaxConsecutionOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums == null) return 0;
        int counter = 0;
        int max = 0;
        int flipIndex = -1;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                counter++;
            } else {
                if(flipIndex == -1) {
                    flipIndex = i;
                    counter++;
                } else {
                    max = Math.max(max, counter);
                    counter = i - flipIndex;
                    flipIndex = i;
                }
            }
        }

        return Math.max(counter, max);
    }
}

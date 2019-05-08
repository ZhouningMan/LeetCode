package array_string;

import java.util.HashMap;
import java.util.Map;

public class RemoveDupFromSortedArrays {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int rank = 1;
        int previous = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > previous) {
                nums[rank++] = nums[i];
                previous = nums[i];
            }
        }
        return rank;
    }
}

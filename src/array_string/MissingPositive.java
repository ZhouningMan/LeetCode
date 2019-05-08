package array_string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MissingPositive {
    public int firstMissingPositiveOn(int[] nums) {
        if(nums == null) return 1;
        Set<Integer> vals = new HashSet<>();
        int min = 1;
        for(int i = 0; i < nums.length; ++i) {
            if(min == nums[i]) {
                min++;
                while(vals.contains(min)) {
                    min++;
                }
            } else if(min < nums[i]) {
                vals.add(nums[i]);
            }
        }
        return min;
    }

    public int firstMissingPositiveConstant(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        for(int i = 0; i < nums.length; ++i) {
            while(nums[i] > 0 && nums[i] < nums.length + 1 && nums[i] != nums[nums[i] -1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] -1] = nums[i];
                nums[i] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] != i + 1) return i + 1;
        }

        return nums.length + 1;
    }
}

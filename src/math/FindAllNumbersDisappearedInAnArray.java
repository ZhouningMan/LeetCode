package math;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        //use the value rather than the index to index the array this it the key
        for (int i = 0; i < n; ++i) {
            //we need to take the absolute value because our values might be
            //negated. Take absolute value restore the previous state for processing.
            //This is the general principle for in-place algorithm, you need to have a way
            //to restore to previous state as if you have an auxiliary data structure
            int idx = Math.abs(nums[i]) - 1;
            if(nums[idx] > 0) { //if less than zero ==>there exists Math.abs(idx) + 1
                nums[idx] = -nums[idx];//change the value to negative
            }
        }

        for(int i = 0; i < n; ++i) {
            //there is no such value x in the array such that x -1 = i;
            //so ith value is not touched.
            if(nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }


    public static void test() {

    }
}

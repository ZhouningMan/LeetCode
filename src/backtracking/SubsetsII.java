package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void helper(int[] nums, int start, List<Integer> subset, List<List<Integer>> result) {
        result.add(new ArrayList<>(subset));
        for(int i = start; i < nums.length; ++i) {
            //we have handled previous case.
            if(i > 0 && nums[i] == nums[i -1] && i > start) continue;
            subset.add(nums[i]);//subset contains nums[i]
            helper(nums, i + 1, subset, result);
            subset.remove(subset.size() - 1);//subset will nto have nums[i-1]
        }
    }
}

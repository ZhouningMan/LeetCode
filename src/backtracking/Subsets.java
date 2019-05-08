package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        return subsets(nums, 0);
    }

    private List<List<Integer>> subsets(int[] nums, int pos) {
        List<List<Integer>>  result = new ArrayList<>();
        if(pos == nums.length) {
            result.add(new ArrayList<>());
            return result;
        }
        int val = nums[pos];
        List<List<Integer>> subsets = subsets(nums, pos + 1);
        result.addAll(subsets);
        for(List<Integer> subset : subsets) {
            List<Integer> list = new ArrayList<>();
            list.add(val);
            list.addAll(subset);
            result.add(list);
        }
        return result;
    }
}

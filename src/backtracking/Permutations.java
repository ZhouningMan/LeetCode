package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        return permute(nums, 0);
    }

    private List<List<Integer>> permute(int[] nums, int p) {
        List<List<Integer>> result = new ArrayList<>();
        if(p == nums.length) {
            result.add(new ArrayList<>());
            return result;
        }

        int val = nums[p];
        List<List<Integer>> prev = permute(nums, p + 1);
        for(List<Integer> prevPerm : prev) {
            for(int i = 0; i <= prevPerm.size(); ++i) {
                List<Integer> list = new ArrayList<>(prevPerm);
                list.add(i, val);
                result.add(list);
            }
        }
        return result;
    }
}

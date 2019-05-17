package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        helper(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void helper(int[] candidates, int target, int start, List<Integer> chosen, List<List<Integer>> result) {
        if(target == 0) {
            //make a deep copy
            result.add(new ArrayList<>(chosen));
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            if(candidates[i] > target) break;//filter bad possibility
            //de-duplicate
            if(i > start && candidates[i] == candidates[i - 1]) continue;
            chosen.add(candidates[i]);
            //progress the backtrack by incrementing start index
            helper(candidates, target - candidates[i], i + 1, chosen, result);
            chosen.remove(chosen.size() - 1);
        }
    }
}

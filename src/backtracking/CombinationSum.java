package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), 0, candidates, target);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> comb, int partialSum, int[] candidates, int target) {
        if(partialSum == target) {
            result.add(new ArrayList<>(comb));
            return;
        }

        int maxElement = !comb.isEmpty() ? comb.get(comb.size() - 1) : Integer.MIN_VALUE;
        for (int candidate : candidates) {
            //filter exploration to improve efficiency
            if (partialSum + candidate > target) break;
            else if (candidate < maxElement) continue; //eliminate the possibility of duplicates.
            comb.add(candidate);
            helper(result, comb, partialSum + candidate, candidates, target);
            comb.remove(comb.size() - 1);//remove last element;
        }
    }
}

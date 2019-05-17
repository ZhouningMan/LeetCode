package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (k <= 0 || k > 10 || n < 0 || n > 50) return result;
        helper(k, n, new ArrayList<>(), 1, result);
        return result;
    }

    private void helper(int k, int n, List<Integer> chosen, int start, List<List<Integer>> result) {
        if (k == 0 && n == 0) {
            result.add(new ArrayList<>(chosen));
            return;
        } else if (k == 0 || n == 0) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (i > n / k) break;
            chosen.add(i);
            helper(k - 1, n - i, chosen, i + 1, result);
            chosen.remove(chosen.size() - 1);
        }
    }
}

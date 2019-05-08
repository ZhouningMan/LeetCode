package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        permuteUnique(nums, new ArrayList<>(), result);
        return  result;
    }

    private void permuteUnique(int[] nums, List<Integer> perm, List<List<Integer>> result) {
        if(nums.length == 0) {
            result.add(new ArrayList<>(perm)); //need to make copy!!!
        } else {
            for(int i = 0; i < nums.length; ++i) { //we always start from 0!!!
                if(i ==0  || nums[i] != nums[i - 1]) {
                    perm.add(nums[i]);
                    //we need shrink the decision tree so we make a copy
                    //maybe this is something we want to revisit.,
                    int[] copy = copyExcept(nums, i);
                    permuteUnique(copy,  perm, result);
                    perm.remove(perm.size() - 1);//remove last one
                }
            }
        }
    }

    private int[] copyExcept(int[] nums, int i) {
        int[] copy = new int[nums.length -1 ];
        int j = 0;
        for (int pos = 0; pos < nums.length; ++pos) {
            if(pos == i) continue;
            copy[j++] = nums[pos];
        }
        return copy;
    }

    public static void test() {
        new PermutationII().permuteUnique(new int[]{1,1,2});
    }
}

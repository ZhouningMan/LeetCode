package backtracking;

import java.util.Arrays;

public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if(sum % k != 0) return false;
        int target = sum / k;
        return partition(nums, new boolean[nums.length], target, 0,0,  k);
    }

    //return true when we can partition the subset, otherwise, return false
    //exploration space k --> 1
    private boolean partition(int[] nums, boolean[] visited, int target, int partialSum, int start, int k) {
        if(k == 1) return true;
        //backtracking problem, the loop start condition is important, we don't want to do extra work
        for(int i = start; i < nums.length; ++i) {
            if(visited[i]) continue; //a global set to avoid reusing the same number
            int temp = partialSum + nums[i];//a local variable so we don't temper next iteration
            if(temp > target) continue; //optimization
            visited[i] = true;
            boolean done;
            //each iteration is one recursive call!!! because we want to do backtracking
            //if we do more things than a simple recursion, we cannot do backtracking
            if(temp == target) {//reduce exploration space
                 //loop from beginning to find a new target
                //We CANNOT return immediately here. Consider [4,4,6,2,3,8,10,2,10,7]
                done = partition(nums, visited, target, 0, 0, k -1);
            } //don't loop from beginning to avoid redundant work
            else {
                done = partition(nums, visited, target, temp, i + 1, k);
            }
            if(done) return true;
            visited[i] = false;
        }
        return false;
    }

    public static void test() {
        new PartitionToKEqualSumSubsets().canPartitionKSubsets(new int[]{4,4,6,2,3,8,10,2,10,7}, 4);
    }
}

package dynamic_programming;

import java.util.*;

public class MaximumSumOfThreeSubArrays {
    private static class Entry{
        Deque<Integer> indices = new ArrayDeque<>(3);
        int sum = 0;
        Entry(){}
        Entry(Entry entry) {
          this.indices = new ArrayDeque<>(entry.indices);
          this.sum = entry.sum;
        }
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if(nums.length < 3) return new int[3];
        int[] windowSums = windowSum(nums, k);
        Entry[][] dp = new Entry[nums.length + 1][4];
        for(int i = 0; i <= windowSums.length; i++) {
            for(int j = 0; j <= 3; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = new Entry();
                } else {
                    Entry entry;
                    if(dp[i][j - 1].sum  > dp[i-1][j - 1].sum) {
                        entry = new Entry(dp[i][j - 1]);
                    } else {
                        entry = new Entry(dp[i-1][j - 1]);
                    }

                    if(entry.indices.size() == 0 || i - 1 - entry.indices.peek() >= k) {
                        entry.sum += windowSums[i - 1];
                        entry.indices.push(i - 1);
                    } else {
//                        int prevIndices = entry.indices.peek();
//                        if(windowSums[i - 1] > windowSums[prevIndices]) {
//                            entry.sum -= windowSums[prevIndices];
//                            entry.indices.pop();
//                            entry.sum += windowSums[i - 1];
//                            entry.indices.push(i - 1);
//                        }
                    }
                    if(entry.indices.size() == j && entry.sum > dp[i -1][j].sum) {
                         dp[i][j] = entry;
                    } else {
                        dp[i][j] =  dp[i-1][j];
                    }
                }
            }
        }
        return dp[windowSums.length][3].indices.stream().mapToInt(Integer::intValue).toArray();
    }

    //sliding windows, find a better approach
    private int[] windowSum(int[] nums, int k){
        int window = 0;
        int[] winSums = new int[nums.length - k + 1];
        int winSum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(window < k) {
                winSum += nums[i];
                window++;
            }
            if(window == k) {
                winSums[i - k + 1] = winSum;
                winSum -= nums[i - k + 1];
                window--;
            }
        }
        return winSums;
    }

    public static void test() {
        System.out.println(Arrays.toString(new MaximumSumOfThreeSubArrays().maxSumOfThreeSubarrays(
                new int[]{1,2,1,2,6,7,5,1},
                2
        )));
    }

}

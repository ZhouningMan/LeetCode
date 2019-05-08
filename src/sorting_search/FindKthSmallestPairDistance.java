package sorting_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FindKthSmallestPairDistance {

    public int  smallestDistancePair(int[] nums, int k) {
        //sort the array
        Arrays.sort(nums);
        int low = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length - 1; ++i) {
            low = Math.min(low, nums[i + 1] - nums[i]);
        }
        int high = nums[nums.length - 1] - nums[0];
        while(low < high) {
            //count the number of pairs that are smaller than mid distance
            int mid = low + (high - low) /2;
            //double pointer to count;
            int left =0, count = 0;
            for(int right = 1; right < nums.length; ++right) {
                while(nums[right] - nums[left] > mid) {
                    left++;
                }
                count += right - left;
            }
            //we cannot return mid if it is equal, because mid is not a value that we cares.
            if(count >= k) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    public static void test() {
        List<Integer> randInts = new ArrayList<>(10);
        Random random = new Random();
        for(int i =0; i < 10; i++) {
            randInts.add(random.nextInt(10));
        }
        new FindKthSmallestPairDistance().smallestDistancePair(randInts.stream().mapToInt(Integer::intValue).toArray(), 40);
    }
}

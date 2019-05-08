package array_string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int goodIndex = -1;
        for(int i = 0; i < nums.length - 2; ++i) {
            if(goodIndex != -1 && nums[goodIndex] == nums[i]) continue;//avoid duplicate combination.
            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                if(sum > 0 ) {
                    right--;
                } else if(sum == 0) {
                    List<Integer> triplet = Arrays.asList(nums[i], nums[left], nums[right]);
                    result.add(triplet);
                    goodIndex = i;
                    //avoid duplicate
                    while(left < right && nums[left] == triplet.get(1)) left++;
                    //avoid duplicate
                    while(right > left && nums[right] == triplet.get(2)) right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }


    public static void test() {
        new ThreeSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
    }
}

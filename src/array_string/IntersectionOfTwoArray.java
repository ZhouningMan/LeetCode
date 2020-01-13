package array_string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class IntersectionOfTwoArray {
    /**
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> commons = new HashSet<>();
        for(int n : nums2) {
            if(set1.contains(n)) {
                commons.add(n);
            }
        }
        int[] ans = new int[commons.size()];
        int i = 0;
        for(int n : commons) {
            ans[i++] = n;
        }
        return ans;
    }
}
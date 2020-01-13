package array_string;

import java.util.*;

public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> numsCount = new HashMap<>();
        for(int n : nums1) {
            numsCount.put(n, numsCount.getOrDefault(n, 0) + 1);
        }
        Map<Integer, Integer> commons = new HashMap<>();
        for(int n : nums2) {
            if(numsCount.getOrDefault(n, 0) > 0) {
                commons.put(n, commons.getOrDefault(n, 0) + 1);
                numsCount.put(n, numsCount.get(n) - 1);
            }
        }
        return mapToList(commons);
    }

    private int[] mapToList(Map<Integer, Integer> commons) {
        List<Integer> nums = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : commons.entrySet()) {
            for(int i = 0; i < entry.getValue(); ++i) {
                nums.add(entry.getKey());
            }
        }
        return nums.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static void test() {
        var s = new IntersectionOfTwoArraysII();
        var ans = s.intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        System.out.println(Arrays.toString(ans));
    }
}

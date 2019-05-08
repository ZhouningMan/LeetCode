package array_string;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntersectionOfTwoArray {
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> num1Frequency = new HashMap<>();
        for(int num : nums1) {
            num1Frequency.compute(num, (key, val) -> val == null ? 1 : val + 1);
        }

        ArrayList<Integer> intersection = new ArrayList<>();
        for(int num : nums2) {
            num1Frequency.computeIfPresent(num, (key, val) -> {
                intersection.add(num);
                return val == 1? null : val - 1;
            });
        }
        int[] result = new int[intersection.size()];
        for(int i = 0; i < intersection.size(); ++i) {
            result[i] = intersection.get(i);
        }
        return result;
    }
}

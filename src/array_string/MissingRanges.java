package array_string;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            format(lower, upper, result);
            return result;
        }

        int start = lower;
        int end;
        for (int num : nums) {
            if (num == Integer.MAX_VALUE) {
                end = Integer.MAX_VALUE - 1;
                format(start, end, result);
                return result;
            } else if (num == Integer.MIN_VALUE) {
                start = Integer.MIN_VALUE + 1;
            } else {
                end = num - 1;
                format(start, end, result);
                start = end + 2;
            }
        }

        format(start, upper, result);
        return result;
    }

    private void format(int start, int end, List<String> result) {
        if(start < end) {
            result.add(start + "->" + end);
        } else if(start == end) {
            result.add(String.valueOf(start));
        }
    }
}

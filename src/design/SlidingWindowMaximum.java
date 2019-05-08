package design;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length < k || k <= 0) return null;
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> window = new ArrayDeque<>(k);
        int r = 0;
        for (int i = 0; i < nums.length; ++i) {
            //remove outdate index;
            //the invariant of the deque is that
            //1. it has <= k elements
            //2. window[i] is decreasing from head to tail.
            //To understand how this work, just use induction
            while (!window.isEmpty() && window.peek() < i - k + 1) {
                window.poll();//remove expired indices
            }
            while (!window.isEmpty() && nums[window.peekLast()] <= nums[i]) {
                window.pollLast();
            }
            window.offer(i);
            if (i >= k - 1) {
                result[r++] = nums[window.peek()];
            }
        }
        return result;
    }

    // This solution has worst time of O(nk) but avg case is lot better
    // as it only scans k elements if first element of previous window was max
    // AND last element of new windoe is not greater than max .
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            max = Math.max(max, nums[i]);
        }
        res[0] = max;
        int i = 0, j = k;
        while (j < nums.length) {
            if (nums[j] > max) {
                max = nums[j];
                res[i + 1] = max;
            } else {
                if (nums[i] == max) {
                    max = nums[j];
                    for (int x = i + 1; x < j; x++) {
                        if (nums[x] > max) {
                            max = nums[x];
                        }
                    }
                }
                res[i + 1] = max;
            }
            i++;
            j++;
        }
        return res;
    }
}

package sorting_search;

import java.util.*;

public class NextGreaterElements {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if(nums2.length == 0 || nums1.length == 0) return new int[0];
        Map<Integer, Integer> nextGreater = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < nums2.length; ++i) {
            int val = nums2[i];
            while(!stack.isEmpty() && stack.peek() < val) nextGreater.put(stack.pop(), val);
            stack.push(val);
        }
        int[] result = new int[nums1.length];
        for(int i = 0; i < nums1.length; ++i)  {
            result[i] = nextGreater.getOrDefault(nums1[i], -1);
        }
        return result;
    }
}

package array_string;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> lengthMap = new HashMap<>();
        int max = 0;
        for(int v : nums) {
            if(lengthMap.containsKey(v)) continue;
            int left = lengthMap.getOrDefault(v - 1, 0);
            int right = lengthMap.getOrDefault(v + 1, 0);
            int len = left + 1 + right;
            max = Math.max(len, max);
            lengthMap.put(v, len);
            if(lengthMap.containsKey(v -1)) {
                //
                lengthMap.put(v - left, len);
            }
            if(lengthMap.containsKey(v + 1)) {
                lengthMap.put(v + right, len);
            }
        }
        return max;
    }

    public static void test() {
        new LongestConsecutiveSequence().longestConsecutive(new int[] {
                100,4,200,1,3,2
        });
    }
}

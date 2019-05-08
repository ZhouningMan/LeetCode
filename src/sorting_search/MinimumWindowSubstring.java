package sorting_search;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        Map<Character, Integer> frequency = new HashMap<>();
        for(int i = 0; i < t.length(); ++i) {
            frequency.put(t.charAt(i), frequency.getOrDefault(t.charAt(i), 0) + 1);
        }
        int counter = t.length();
        int left = 0, right = 0; //two pointers
        int window = Integer.MAX_VALUE,  head = -1; //optimal condition
        while(right < s.length()) {
            char c = s.charAt(right++);
            Integer val = frequency.computeIfPresent(c, (k, v) -> v - 1);
            if(val != null && val >= 0) counter--;
            //we don't need to check left with right because we only handle left
            // when the condition is still satisfied
            while (counter == 0) { //try to optimize the solution
                // the conditions are still satisfied at this moment because we have done
                // nothing to nsatisfy it, so we record the result.
                if(right - left < window) {
                    window = right - left;
                    head = left;
                }
                c = s.charAt(left++);
                val = frequency.computeIfPresent(c, (k,v) -> v + 1);
                //unsatisfy the condition
                if(val != null && val > 0) counter++;
            }
        }
        return head == -1 ? "" : s.substring(head, head + window);
    }

    public static void test() {
        new MinimumWindowSubstring().minWindow("cabwefgewcwaefgcf", "cae");
    }
}

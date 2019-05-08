package array_string;

import java.util.*;

public class LongestSubstringWithKDistinctChar {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k <= 0) return 0;
        char[] content = s.toCharArray();
        int startIndex = 0;
        int index = 0;
        Map<Character, Integer> distinctIndex = new HashMap<>();
        int max = 0;
        while (index < s.length()) {
            if (distinctIndex.size() < k || (distinctIndex.size() == k && distinctIndex.containsKey(content[index]))) {
                distinctIndex.put(content[index], index);
            } else {
                max = Math.max(max, index - startIndex);
                Map.Entry<Character, Integer> entryToRemove = Collections.min(distinctIndex.entrySet(),
                        Comparator.comparing(Map.Entry::getValue));
                distinctIndex.remove(entryToRemove.getKey());
                distinctIndex.put(content[index], index);
                startIndex = entryToRemove.getValue() + 1;
            }
            index++;
        }
        return Math.max(max, index - startIndex);
    }

    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        int n = s.length();
        if (n * k == 0) return 0;
        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();
        int max_len = 1;
        while (right < n) {
            // add new character and move right pointer
            hashmap.put(s.charAt(right), right++);
            // slidewindow contains 3 characters
            if (hashmap.size() == k + 1) {
                // delete the leftmost character
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                // move left pointer of the slidewindow
                left = del_idx + 1;
            }
            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }

    public int lengthOfLongestSubstringKDistinct3(String s, int k) {
        int n = s.length();
        if (n * k == 0) return 0;
        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        LinkedHashMap<Character, Integer> hashmap = new LinkedHashMap<Character, Integer>(k + 1);
        int max_len = 1;
        while (right < n) {
            Character character = s.charAt(right);
            // if character is already in the hashmap -
            // delete it, so that after insert it becomes
            // the rightmost element in the hashmap
            if (hashmap.containsKey(character))
                hashmap.remove(character);
            hashmap.put(character, right++);
            // slidewindow contains k + 1 characters
            if (hashmap.size() == k + 1) {
                // delete the leftmost character
                Map.Entry<Character, Integer> leftmost = hashmap.entrySet().iterator().next();
                hashmap.remove(leftmost.getKey());
                // move left pointer of the slidewindow
                left = leftmost.getValue() + 1;
            }
            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}

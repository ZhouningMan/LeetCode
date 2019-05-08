package array_string;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> unique = new HashSet<>();
        int j = 0;
        int max = 0;
        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            while(unique.contains(c)) {
                unique.remove(s.charAt(j++));
            }
            unique.add(c);
            max = Math.max(max, unique.size());
        }
        return max;
    }
}

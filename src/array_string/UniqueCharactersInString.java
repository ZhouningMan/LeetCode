package array_string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueCharactersInString {
    public int firstUniqCharV2(String s) {
        Map<Character, Integer> frequency = new HashMap<>();
        for(int i = 0; i < s.length(); ++i) {
            frequency.merge(s.charAt(i), 1, (oldVal, newVal) -> newVal + oldVal);
        }

        for(int i = 0; i < s.length(); ++i) {
            if(frequency.get(s.charAt(i)) == 1) return i;
        }

        return -1;
    }

    public int firstUniqChar(String s) {
        int[] charset = new int[26];
        for(int i = 0; i < s.length(); ++i) {
            charset[s.charAt(i) - 'a']++;
        }

        for(int i = 0; i < s.length(); ++i) {
            if(charset[s.charAt(i) - 'a'] == 1) return i;
        }

        return -1;
    }
}

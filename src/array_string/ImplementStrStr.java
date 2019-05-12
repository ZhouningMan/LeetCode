package array_string;

import java.util.HashMap;
import java.util.Map;

public class ImplementStrStr {
    public int strStr(String haystack, String needle) {
        if(needle.isEmpty()) return 0;
        //boyer moore implementation
        final int n = haystack.length(), m = needle.length();
        Map<Character, Integer> rightMostIndex = new HashMap<>();
        for(int i = 0; i < needle.length(); ++i) rightMostIndex.put(needle.charAt(i), i);
        int i = 0;
        //example: find abca in abcecdabaacaabca
        while(i <= n - m) { //i is always increasing, so that we don't need backup
            int skip = 0;
            //for every i, we align the beginnign character, and we do match from the end of the pattern
            //This also means the worst case performance could be m*n
            //Example: find ABBB in BBBBBBBBBBBBBBBBBBBBBBBBB;
            for(int j = m -1; j >= 0; --j) {
                //mismatch
                if(needle.charAt(j) != haystack.charAt(i + j)) {
                    int rightMost = rightMostIndex.getOrDefault(haystack.charAt(i + j), -1);
                    if(rightMost == -1) {
                        // character if not in pattern, we want to shift everything to the right of that
                        //character, not shifting by length o pattern
                        skip = j + 1;
                    } else if(j < rightMost) {
                        //this is where our heruistc fails, we just increment i by one to make minimum progress
                        //which is still pregress
                        skip = 1;
                    } else {
                        //shift the pattern to the right by j - rightMost of that character
                        //which basically translates to shift the beginning to the right by j - rightMost.
                        skip =  j - rightMost;
                    }
                    break;
                }
            }
            if(skip == 0) return i;
            i += skip;
        }
        return -1;
    }

    public static void test() {
        new ImplementStrStr().strStr("ababbbbaaabbbaaa", "bbbb");
    }
}

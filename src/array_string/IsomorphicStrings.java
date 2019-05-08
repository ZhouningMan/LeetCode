package array_string;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> tranMapST = new HashMap<>();
        Map<Character, Integer> tranMapTS = new HashMap<>();
        for(int i = 0 ; i < s.length(); ++i) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            int diff = sc - tc;
            tranMapST.putIfAbsent(sc, diff);
            tranMapTS.putIfAbsent(tc, diff);
            //for two string to be isomorphic, the difference between each chracter should
            //be the same for the same character at each position
            //we need to do two ways check. Cannot just rely on one map.
            //aa --> ab
            if(tranMapST.get(sc) != diff || tranMapTS.get(tc) != diff) return false;
        }
        return true;
    }
}

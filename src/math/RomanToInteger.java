package math;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    private static final Map<String, Integer> SUB_MAP = new HashMap<>()
    {{
        put("IV", 4);
        put("IX", 9);
        put("XL", 40);
        put("XC", 90);
        put("CD", 400);
        put("CM", 900);
    }};

    private static final Map<Character, Integer> ROMAN_TO_INT = new HashMap<>() {{
       put('I', 1);
       put('V', 5);
       put('X', 10);
       put('L', 50);
       put('C', 100);
       put('D', 500);
       put('M', 1000);
    }};
    public int romanToInt(String s) {
        int oneAway = 0;
        int twoAway = 0;
        int result = 0;
        for(int i = s.length() - 1; i >= 0; --i) {
            if(i <= s.length() - 2 && SUB_MAP.containsKey(s.substring(i, i+2))) {
                result = SUB_MAP.get(s.substring(i, i+2)) + twoAway;
            } else {
                result = oneAway + ROMAN_TO_INT.get(s.charAt(i));
            }
            twoAway = oneAway;
            oneAway = result;
        }
        return result;
    }
}

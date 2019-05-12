package math;

import java.util.Map;
import java.util.TreeMap;

public class IntegerToRoman {
    private static final TreeMap<Integer, String> ROMAN = new TreeMap<>((a ,b )-> b-a) {{
        put(1000, "M");
        put(900, "CM");
        put(500, "D");
        put(400, "CD");
        put(100, "C");
        put(90, "XC");
        put(50, "L");
        put(40, "XL");
        put(10, "X");
        put(9, "IX");
        put(5, "V");
        put(4, "IV");
        put(1, "I");
    }};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            //loop through the ordered entry
            for(Map.Entry<Integer, String> entry : ROMAN.entrySet()) {
                int quotient = num / entry.getKey();
                if(quotient != 0) {
                    num = num % entry.getKey();
                    while(quotient>0) {
                        sb.append(entry.getValue());
                        quotient--;
                    }
                    break;
                }
            }
        }
        return sb.toString();
    }
}

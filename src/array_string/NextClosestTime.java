package array_string;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class NextClosestTime {

    public String nextClosestTime(String time) {
        List<Integer> digits = toNumber(time);
        TreeSet<Integer> orderedDigits = new TreeSet<>(digits);
        boolean found = false;
        for(int i = digits.size() -1; i >=0 && !found; i--) {
            Integer val = digits.get(i);
            Integer max = getAllowedMax(i, digits, orderedDigits);
            if(max != null && max > val) { //we can find the time today
                digits.set(i, max);
                for(int j = i + 1; j < digits.size(); ++j) {
                    int min = orderedDigits.first();
                    digits.set(j, min);
                }
                found = true;
            }
        }
        if(found) return toTimeString(digits);

        for(int i = 0; i < digits.size(); ++i) {
            int min = orderedDigits.first();
            digits.set(i, min);
        }
        return toTimeString(digits);
    }

    private String toTimeString(List<Integer> digits) {
        StringBuilder sb = new StringBuilder();
        sb.append(digits.get(0))
                .append(digits.get(1))
                .append(':')
                .append(digits.get(2))
                .append(digits.get(3));
        return sb.toString();
    }

    private Integer getAllowedMax(int i,List<Integer> digits,  TreeSet<Integer> orderedDigits) {
        int max = 0;
        switch (i) {
            case 0: max = 2; break;
            case 1: {
                if(digits.get(0) < 2) max = 9;
                else max = 3;
            } break;
            case 2: max = 5; break;
            case 3: max = 9; break;
        }
        Integer ceiling = orderedDigits.ceiling(digits.get(i) + 1);
        if(ceiling != null && ceiling > max) ceiling = null;
        return  ceiling;
    }

    private List<Integer> toNumber(String time) {
        List<Integer> digits = new ArrayList<>();
        for(char c : time.toCharArray()) {
            if(c != ':') digits.add(c - '0');
        }
        return digits;
    }
}

package other;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {

    private String fractionToDecimal(long divisor, long dividend) {
        StringBuilder sb = new StringBuilder();
        if(dividend == 0) throw new IllegalArgumentException();
        else if(divisor == 0) return "0";
        else if((divisor ^ dividend) < 0) { //deal with sign just once.
            sb.append('-');
        }
        divisor = Math.abs(divisor);
        dividend = Math.abs(dividend);
        long quotient = divisor / dividend;
        long remainder = divisor % dividend;
        sb.append(quotient);
        if(remainder != 0)  sb.append('.');
        int fractionLen = sb.length();
        Map<Long, Integer> remainderMap = new HashMap<>();
        while(remainder != 0) {
            remainderMap.put(remainder, fractionLen++);
            divisor = remainder * 10; //potential overflow with int
            //repeating condition
            quotient = divisor / dividend;
            remainder = divisor % dividend;
            sb.append(quotient);
            if(remainderMap.containsKey(remainder)) {
                sb.insert(remainderMap.get(remainder).intValue(), '(');
                sb.append(')');
                break;
            }
        }
        return sb.toString();
    }

    public String fractionToDecimal(int numerator, int denominator) {
        return fractionToDecimal((long)numerator, (long)denominator);
    }

    public static void test() {
        new FractionToRecurringDecimal().fractionToDecimal(4, 333);
    }
}

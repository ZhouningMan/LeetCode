package math;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while(n != 1) {
            int val = 0;
            while(n > 0) {
                int mod = n % 10;
                n = n /10;
                val += mod * mod;
            }
            n = val;
            if(seen.contains(val)) break;
            seen.add(val);
        }
        return n == 1;
    }
}

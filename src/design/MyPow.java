package design;

import java.util.HashMap;
import java.util.Map;

public class MyPow {
    public double myPow(double x, int n) {
        if(x == 0) return 0;
        return myPow(x, n, new HashMap<>());
    }

    public double myPow(double x, int n, Map<Integer, Double> memo) {
        if(n == 0 ) return 1;
        else if(n == 1 ) return x;
        else if(n == -1) return 1/x;

        if(memo.containsKey(n)) return memo.get(n);
        double res = myPow(x, n/2, memo) * myPow(x, n - n/2, memo);
        memo.put(n, res);
        return res;
    }
}

package dynamic_programming;

public class ClimbingStairs {
    public int climbStairs(int n) {
        int last = 0;
        int secondToLast = 0;
        int total = 0;
        for(int i = n; i >= 0 ; i--) {
            if(i == n || i == n - 1) {
                total = 1;
                last = 1;
                secondToLast = 1;
            } else {
                total = last + secondToLast;
                secondToLast = last;
                last = total;
            }
        }
        return total;
    }
}

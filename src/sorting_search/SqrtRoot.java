package sorting_search;

public class SqrtRoot {
    public int mySqrt(int x) {
        if(x == 0 || x == 1) return x;
        int low = 0 ;
        int hi = x;
        while(low <= hi) {
            int mid = low + (hi - low) / 2;
            int val = x / mid; //overflow.
            if(mid > val) hi = mid - 1;
            else if(mid < val )low = mid + 1;
            else return mid;
        }
        return hi;
    }

    public static void test() {
        System.out.println(new SqrtRoot().mySqrt(2147395599));
    }
}

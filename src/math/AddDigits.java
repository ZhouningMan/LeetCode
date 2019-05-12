package math;

public class AddDigits {
    public int addDigits2(int num) {
        int prev = 0;
        int curr;
        while(num != 0) {
            curr = num % 10;
            prev = reduce(curr, prev);
            num /=  10;
        }
        return prev;
    }

    private int reduce(int d1, int d2) {
        int sum = d1 + d2;
        if(sum < 10) return sum;
        return reduce(sum/10 , sum %10);
    }

    public int addDigits(int num) {
        int sumD = 0;
        while(num != 0) {
            sumD += num % 10;
            num = num/10;
        }
        return sumD > 10 ? addDigits(sumD) : sumD;
    }


}

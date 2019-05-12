package math;

public class NthDigit {
    public int findNthDigit(int n) {
        // digits pattern: = 9 x 1+ 9 x 10 x 2 + 9 x 100 x 3 + 9 x 1000 x 4
        //we can find which region the number belongs to using substraction
        int len = 1;
        long count = 9;
        //find the region
        while(n > count * len) {
            n -= count * len;
            count *= 10;
            len++;
        }
        //we do n-1 because count/9 will occupies len digits,
        long target =  count/9 + (n -1)/ len ;
        int index = (n -1)%len;
        return String.valueOf(target).charAt(index) - '0';
    }
}

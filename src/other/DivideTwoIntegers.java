package other;

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        //deal with special case
        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        } else if(divisor == Integer.MIN_VALUE) {
            return dividend == divisor? 1 : 0;
        }
        int signBit = 1 << 31;
        boolean sameSign = (dividend & signBit) == (divisor & signBit);
        int quotient;
        if(dividend == Integer.MIN_VALUE) { //cannot take abs value of MIN_VALUE directly
            int[] result = divideRecur(Math.abs(dividend >> 1), Math.abs(divisor));
            //quotient = 0 means that the Math.abs(divisor) is greater than Integer.MIN_VALUE/2
            quotient = result[0] + result[0];
            if(quotient == 0 ) return sameSign ? 1 : -1;
            if(result[1] + result[1] >= Math.abs(divisor)) {
                quotient++;
            }
        } else {
            int[] result = divideRecur(Math.abs(dividend), Math.abs(divisor));
            quotient = result[0];
        }
        return sameSign? quotient : -quotient;
    }

    //return int[] with int[0] being quotient, and int[1] being remainder
    private int[] divideRecur(int dividend, int divisor) {
        if(dividend == divisor) {
             return new int[]{1, 0};
        } else if(dividend < divisor) {
            return new int[]{0, dividend};
        } else {
            int lsb = dividend & 1; //for odd number, we want to store the least significant bit
            int[] result = divideRecur(dividend >> 1, divisor);
            result[0] = result[0] + result[0];
            result[1] = result[1] + result[1] + lsb;
            if(result[1] >= divisor) {
                result[0]++;
                result[1] = result[1] - divisor;
            }
            return result;
        }
    }
}

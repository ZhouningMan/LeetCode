package other;

public class ReverseInteger {
    public int reverse(int x) {
        if(x == 0) return 0;
        String val = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        int end;
        String limit;
        if(x < 0) {
            end = 1;
            sb.append('-');
            limit = String.valueOf(Integer.MIN_VALUE);
        } else {
            end = 0;
            limit = String.valueOf(Integer.MAX_VALUE);
        }
        boolean leadingZero = true;
        for(int i = val.length() - 1; i >= end; --i) {
            if(val.charAt(i) == '0' && leadingZero) continue;
            leadingZero = false;
            sb.append(val.charAt(i));
        }
        String reversed = sb.toString();
        if(reversed.length() == limit.length() && reversed.compareTo(limit) > 0) return 0;
        else return Integer.parseInt(reversed);
    }

    public int reverse2(int x) {
        int result = 0;
        while(x != 0) {
            int tail = x % 10;
            if(overflow(result, tail)) return 0;
            result = result * 10 + tail;
            x = x /10;
        }
        return result;
    }

    private boolean overflow(int val, int tail) {
        //newVal = val * 10 + tail
        //modulo of negative number <= 0
        //the only difference between positive and negative limit is in the
        //direction of equality check, not the value of limit
        if(val < 0 || tail < 0) {
            return val < (Integer.MIN_VALUE - tail)/10;//within range
        } else {
            return val > (Integer.MAX_VALUE - tail)/10; //within range
        }
    }

    public static void test() {
         new ReverseInteger().reverse2(-123);
    }
}

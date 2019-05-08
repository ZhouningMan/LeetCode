package array_string;

public class AddBinary {
    private static final char ZERO = '0';
    public String addBinary(String a, String b) {
        //make sure first string has greater length, this is a smart move
        if(b.length() > a.length()) {
            return addBinary(b, a);
        }

        StringBuilder sb = new StringBuilder();
        final int aLen = a.length();
        final int bLen = b.length();
        int carryOver = 0;
        int i;
        //loop though shorter string
        for(i = 0;  bLen -1 - i >= 0; ++i) {
            int sum = carryOver + (a.charAt(aLen - 1 - i) - ZERO) + b.charAt(bLen - 1 - i) - ZERO;
            carryOver = appendString(sum, sb);
        }
        //loop through the longer string
        for(; aLen - 1 - i >=0; ++i) {
            int sum = carryOver + (a.charAt(aLen - 1 - i) - ZERO);
            carryOver = appendString(sum, sb);
        }

        if(carryOver == 1) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }

    //return carry over
    private int appendString(int sum, StringBuilder sb) {
        int carryOver;
        if(sum == 3) {
            carryOver = 1;
            sb.append('1');
        } else if(sum == 2) {
            carryOver = 1;
            sb.append('0');
        } else {
            carryOver = 0;
            sb.append(sum == 1 ? '1' : '0');
        }
        return  carryOver;
    }

    public static void test() {
        new AddBinary().addBinary("100", "110010");
    }
}

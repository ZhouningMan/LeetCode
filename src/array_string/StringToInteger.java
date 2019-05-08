package array_string;

public class StringToInteger {

    public int myAtoi(String str) {
        StringBuilder sb = new StringBuilder();
        String numbers = "0123456789";
        String INT_MAX = "2147483647";
        char sign = '+';
        boolean validStart = true;
        boolean leadZero = true;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == ' ') {
                if (!validStart) {
                    break;
                }
            } else if (c == '+' || c == '-') {
                if (validStart) {
                    validStart = false;
                    sign = c;
                } else {
                    break;
                }
            } else if (numbers.indexOf(c) != -1) {
                validStart = false;
                if(c == '0' && leadZero) {
                   continue;
                } else {
                    leadZero = false;
                    sb.append(c);
                }
            } else {
                break;
            }
        }
        String numStr = sb.toString();
        boolean inRange = true;
        if (numStr.length() > INT_MAX.length()) {
            inRange = false;
        } else if (numStr.length() == INT_MAX.length()) {
            for (int i = 0; i < INT_MAX.length() && inRange; ++i) {
                if (numStr.charAt(i) > INT_MAX.charAt(i)) {
                    inRange = false;
                } else if (numStr.charAt(i) < INT_MAX.charAt(i)){
                    break;
                }
            }
        }

        if(numStr.isEmpty()){
            return 0;
        } else if (inRange) {
            int num = Integer.parseInt(numStr);
            return sign == '+' ? num : -num;
        } else {
            return sign == '+' ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
    }

    public static void test() {
        new StringToInteger().myAtoi("1095502006p8");
    }
}

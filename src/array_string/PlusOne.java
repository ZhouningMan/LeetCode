package array_string;

import java.util.Arrays;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int[] result = digits;
        int i = digits.length - 1;
        while(i >= 0 && digits[i] == 9) {
            digits[i] = 0;
            i--;
        }

        if(i >= 0 ) {
            digits[i]++;
        } else {
            //default are zero initialized
            //so don't need to copy digits to result
            result = new int[digits.length + 1];
            result[0] = 1;
        }
        return result;
    }
}

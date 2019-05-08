package other;

public class UTF8Validator {
    private static final int ONE_BYTE_CHECK = 0x80;//1000 0000
    private static final int TWO_BYTES_CHECK = 0xE0;//1110 0000
    private static final int THREE_BYTES_CHECK = 0xF0;//1111 0000
    private static final int FOUR_BYTES_CHECK = 0xF8;//1111 1000

    public boolean validUtf8(int[] data) {
        int currentChar = 0;
        final int length = data.length;
        while(currentChar < length) {
            int encoded = data[currentChar];
            byte charLength;
            if((encoded & ONE_BYTE_CHECK) == 0) {
                charLength = 1;
            } else if((encoded & TWO_BYTES_CHECK) == 0xC0) {
                charLength = 2;
            } else if((encoded & THREE_BYTES_CHECK) == 0xE0) {
                charLength = 3;
            } else if((encoded & FOUR_BYTES_CHECK) == 0xF0) {
                charLength = 4;
            } else {
                //if there are more than 4 ones, return false
                return false;
            }
            //if we don't have enough characters, this is invalid encoding
            if(charLength + currentChar  -1 >= length) {
                return false;
            }

            //make sure all the following bytes follow the same rule.
            for(int i = 1; i < charLength; ++i) {
                if((data[currentChar + i] & 0x80) != 0x80) return false;
            }
            currentChar += charLength;
        }
        return true;
    }

    public static void test() {
        System.out.println(new UTF8Validator().validUtf8(new int[]{248,130,130,130}));
    }
}

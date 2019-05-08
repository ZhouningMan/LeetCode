package other;

public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int result = 0;
        final int L = s.length();
        for(int i = 0; i < L; ++i) {
            char c = s.charAt(i);
            int multiple = c - 'A';
            int exp = L - 1 - i;
            result += multiple * Math.pow(26, exp);
        }
        return result;
    }
}

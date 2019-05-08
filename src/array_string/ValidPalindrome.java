package array_string;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while(i < j) {
            char c = s.charAt(i);
            if(!isAphanumeric(c)) {
                i++;
                continue;
            }
            char d = s.charAt(j);
            if(!isAphanumeric(d)) {
                j--;
                continue;
            }
            if(caseIgnoreCompare(c, d)) {
                i++;
                j--;
            } else break;
        }

        return i >= j;
    }

    private boolean isAphanumeric(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean caseIgnoreCompare(char a, char b) {
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }
}

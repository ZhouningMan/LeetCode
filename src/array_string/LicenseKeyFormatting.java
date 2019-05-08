package array_string;

public class LicenseKeyFormatting {

    private static final int DIFF = 'a' - 'A';
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb  = new StringBuilder();
        for(int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if(c != '-') {
                sb.append(toUpper(c));
            }

        }
        int len = sb.length();
        int groupSize = len / K;
        int firstGroupLen = len % K;


        for(int i = groupSize - 1; i > 0 ; i--) {
            sb.insert(firstGroupLen + i*K, '-');
        }
        if(firstGroupLen != 0 && groupSize > 0){
            sb.insert(firstGroupLen, '-');
        }

        return sb.toString().toUpperCase();
    }

    private char toUpper(char c) {
        if(c >= 'a' && c <= 'z') {
            return (char)((int)c - DIFF);
        }
        return c;
    }
}

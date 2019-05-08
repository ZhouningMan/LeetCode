package dynamic_programming;

public class DecodeWays {

    public static void test() {
        new DecodeWays().numDecodings("230");
    }

    public int numDecodings(String s) {
        int[] ways = new int[s.length() + 1];
        String singleDigits = "3456789";
        for (int i = s.length(); i >= 0; i--) {
            if (i == s.length()) {
                ways[i] = 1;
            } else if (singleDigits.indexOf(s.charAt(i)) >= 0) {
                ways[i] = ways[i + 1];
            } else if (s.charAt(i) == '0') {
                ways[i] = 0;
            } else { // char is 1 or 2
                if (i == s.length() - 1) {
                    ways[i] = ways[i + 1];
                } else if (ways[i + 1] == 0) { //previous char is 0
                    ways[i] = ways[i + 2];
                } else { //
                    int val = Integer.parseInt(s.substring(i, i + 2));
                    ways[i] = val > 26 ? ways[i + 1] : ways[i + 1] + ways[i + 2];
                }
            }
        }
        return ways[0];
    }

    public int numDecodingsSimplified(String s) {
        int[] ways = new int[s.length() + 1];
        for (int i = s.length(); i >= 0; i--) {
            if (i == s.length()) {
                ways[i] = 1;
            } else if (s.charAt(i) == '0') {
                ways[i] = 0;
            } else {
                if (i == s.length() - 1) {
                    ways[i] = ways[i + 1];
                } else { //
                    int val = Integer.parseInt(s.substring(i, i + 2));
                    ways[i] = val > 26 ? ways[i + 1] : ways[i + 1] + ways[i + 2];
                }
            }
        }
        return ways[0];
    }
}

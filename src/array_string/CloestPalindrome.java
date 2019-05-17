package array_string;

public class CloestPalindrome {
    public String nearestPalindromic(String n) {
        if(n == null || n.length() == 0) return "";
        boolean changed = false;
        int len = n.length();
        char[] narry = n.toCharArray();
        for(int i = 0, j = len -1; i < j; i++, j--) {
            if(narry[i] != narry[j]) {
                narry[j] = narry[i];
                changed = true;
            }
        }

        if(changed) {
            return new String(narry);
        }

        if(len % 2 != 0) {
            int mid = len/2;
            narry[mid] = narry[mid] == '0' ? '1' : (char)(narry[mid] - 1);
        } else {
            int mid = len/2;
            if(narry[mid] == '0') {
                narry[mid] = narry[mid -1] = '1';
            } else {
                narry[mid] = narry[mid - 1] = (char)(narry[mid] - 1);
            }
        }
        return new String(narry);
    }
}

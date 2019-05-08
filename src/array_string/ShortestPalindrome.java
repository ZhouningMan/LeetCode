package array_string;

public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if(s == null) return null;
        int i = s.length() - 1;
        while(i >= 1) {
            if(isPalindrome(s, i)) {
               break;
            }
            i--;
        }

        StringBuilder sb = new StringBuilder();
        for(int j = s.length() - 1; j > i; j--) {
            sb.append(s.charAt(j));
        }
        sb.append(s);
        return sb.toString();

    }

    private boolean isPalindrome(String s, int end) {
        int i = 0;
        int j = end;
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    public String shortestPalindrome2(String s) {
        int n = s.length();
        int i=0;
        for(int j = n-1; j>=0; j--){
            if(s.charAt(i) == s.charAt(j))
                i++;
        }
        if(i==n) return s;
        StringBuilder sb= new StringBuilder(s.substring(i));
        sb.reverse();
        return sb + shortestPalindrome(s.substring(0,i)) + s.substring(i);
    }
}

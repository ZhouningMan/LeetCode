package array_string;

public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int i = 0; int j = s.length() - 1;
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) break;
            i++;
            j--;
        }

        if(i >= j) return true; //without deleting, it is a match
        return isValid(s.substring(i+1, j + 1)) || isValid(s.substring(i, j));
    }

    private boolean isValid(String s) {
        if(s.isEmpty()) return true;
        for(int i = 0, j = s.length()-1; i < j; i++, j--) {
            if(s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}

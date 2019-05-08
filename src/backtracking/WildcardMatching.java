package backtracking;

public class WildcardMatching {
//    public boolean isMatch(String s, String p) {
//        int[] charCount = new int[p.length() + 1];
//        int nonStar = 0;
//        for(int i = p.length() - 1; i >=0; i--){
//            if(p.charAt(i) != '*') {
//                nonStar++;
//            }
//            charCount[i] = nonStar;
//        }
//        return helper(s, p, charCount);
//    }

    public static void test() {
        System.out.println(new WildcardMatching().isMatchGreatSolution("xxxxxxxxxxxxxy", "*xxx"));
    }
    private boolean helper(String s, String p, int[] charCount) {
        if(s.length() == 0 && p.length() == 0) {
            return true;
        } else if(p.length() != 0) {
            //match non * char;
            int i = 0;
            while(i < p.length() && i < s.length()) {
                char sc = s.charAt(i);
                char pc = p.charAt(i);
                if(!(pc == '?' || sc == pc)) break;
                i++;
            }
            if(i == p.length() && i == s.length()) {
                return true;
            } else if(i < p.length() && p.charAt(i) == '*') {
                //condense consecutive *
                int k = i + 1;
                for(; k < p.length() && p.charAt(k) == '*'; k++);
                String pLessStars = p.substring(k);
                int nonStar = charCount[charCount.length - 1 - (p.length() - k)];
//                for(int j = s.length(); j <= s.length() && s.length() - j >= nonStar; j++) {
//                    if(helper(s.substring(j), pLessStars, charCount)) return true;
//                }

                for(int j = s.length() - nonStar; j >= i ; j--) {
                    if(helper(s.substring(j), pLessStars, charCount)) return true;
                }

            }
        }
        return false;
    }

    boolean isMatchGreatSolution(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()) {
            System.out.println("s = " + s + ", p = " + p + ", match =" + match + ", startIdx = " + starIdx);
            // advancing both pointers
            if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1) { //reset to old location
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }
        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;
        return p == pattern.length();
    }
}

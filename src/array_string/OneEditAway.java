package array_string;

public class OneEditAway {
    public boolean isOneEditDistance(String s, String t) {
        if (s.length() == t.length()) {
            return oneEditAwayByReplacement(s, t);
        } else if (s.length() - t.length() == 1) {
            return oneEditAwayByInsertion(s, t);
        } else if (s.length() - t.length() == -1) {
            return oneEditAwayByInsertion(t, s);
        }
        return false;
    }

    public boolean isOneEditDistance2(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) return s.substring(i + 1).equals(t.substring(i + 1));
                else if (s.length() < t.length()) return s.substring(i).equals(t.substring(i + 1));
                else return t.substring(i).equals(s.substring(i + 1));
            }
        }
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        return Math.abs(s.length() - t.length()) == 1;
    }

    private boolean oneEditAwayByReplacement(String s1, String s2) {
        int edit = 0;
        for (int i = 0; i < s1.length() && edit <= 1; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) edit++;
        }
        return edit == 1;
    }

    private boolean oneEditAwayByInsertion(String longer, String shorter) {
        int sIndex = 0;
        int lIndex = 0;
        while (sIndex < shorter.length() && lIndex - sIndex <= 1) {
            if (shorter.charAt(sIndex) != longer.charAt(lIndex)) {
                lIndex++;
            } else {
                lIndex++;
                sIndex++;
            }
        }
        return lIndex - sIndex > 1;
    }
}

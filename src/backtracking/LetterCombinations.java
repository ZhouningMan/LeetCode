package backtracking;

import java.util.*;

public class LetterCombinations {
    private static final Map<Integer, String> DIGIT_LETTERS = new HashMap<>();
    static {
        DIGIT_LETTERS.put(2, "abc");
        DIGIT_LETTERS.put(3, "def");
        DIGIT_LETTERS.put(4, "ghi");
        DIGIT_LETTERS.put(5, "jkl");
        DIGIT_LETTERS.put(6, "mno");
        DIGIT_LETTERS.put(7, "pqrs");
        DIGIT_LETTERS.put(8, "tuv");
        DIGIT_LETTERS.put(9, "wxyz");
    }


    public List<String> letterCombinations(String digits) {
        return helper(digits, 0);
    }

    private List<String> helper(String digits, int i) {
        if(i >= digits.length()) return Collections.emptyList();
        int digit = digits.charAt(i) - '0';
        String letters = DIGIT_LETTERS.get(digit);
        List<String> combs = new ArrayList<>();
        for(int j = 0; j < letters.length(); ++j) {
            List<String> prev = helper(digits, i + 1);
            if(prev.isEmpty()) {
                combs.add(letters.substring(j, j+1));
            } else {
                for(String s : prev){
                    combs.add(letters.charAt(j) + s);
                }
            }
        }
        return combs;
    }

    public static void test() {
        List<String> combs = new LetterCombinations().letterCombinations("23");

    }
}

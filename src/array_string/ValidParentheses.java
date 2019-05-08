package array_string;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        boolean valid = true;
        for(int i = 0; i < s.length() && valid; ++i) {
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{') {
                stack.offerLast(c);
            } else if(c == ')' || c == ']' || c == '}') {
                Character toMatch = stack.pollLast();
                if(toMatch == null || !isMatch(toMatch, c)) {
                    valid = false;
                }
            } else {
                valid = false;
            }
        }
        return stack.isEmpty() && valid;
    }

    private boolean isMatch(char begin, char end) {
        return begin == '(' && end == ')' || begin == '[' && end == ']' || begin == '{' && end == '}';
    }
}

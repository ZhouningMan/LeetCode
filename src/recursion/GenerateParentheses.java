package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        helper("", result, 0, 0, n);
        return result;
    }

    private void helper(String s, List<String> result, int open, int close, int max) {
        if(open == max && close == max) {
            result.add(s);
            return;
        }
        //we can place open bracket to a valid candidate as long as we have open bracket
        //left
        if(open < max) {
            helper(s + '(', result, open + 1, close, max);
        }
        //if we have more open brackets than close brackets in a valid candidate,
        //we can add a close bracket to close one of the open bracket
        if(open > close) {
            helper(s + ')', result, open, close+ 1, max);
        }
    }
}

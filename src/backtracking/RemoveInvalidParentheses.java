package backtracking;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        helper(s, "", 0, result);
        return result;
    }

    private int helper(String remaining, String chosen, int maxLength, List<String> result) {
        if (remaining.length() == 0 && isValid(chosen) == 0) {
            if (chosen.length() > maxLength) result.clear();
            result.add(chosen);
            maxLength = chosen.length();
        } else if (remaining.length() > 0) {
            int possibleLength = chosen.length() + remaining.length();
            char c = remaining.charAt(0); //pick the first char
            remaining = remaining.substring(1);//shorten the exploration space.
            if (possibleLength >= maxLength && isValid(chosen) >= 0) { //filter bad exploration space
                maxLength = Math.max(maxLength, helper(remaining, chosen + c, maxLength, result));
            }
            char lastChosen = chosen.length() >= 1 ? chosen.charAt(chosen.length() - 1) : '\0';
            //filter duplicate
            if (possibleLength - 1 >= maxLength && c != lastChosen && isValid(chosen) >= 0) {
                //exclude the parenthesis
                maxLength = Math.max(maxLength, helper(remaining, chosen, maxLength, result));
            }
            //no backtracking require, because we don't have mutable global state.
        }
        return maxLength;
    }

    //if return == 0, exact match, if return > 0, possible , if return < 0, impossible
    int isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')') count--;
        }
        return count;
    }
//    private int helper(StringBuilder s, StringBuilder chosen, int maxLength, List<String> result) {
//        if(s.length() == 0) {
//            String chosenStr =  isValid(chosen.toString();
//            if(isValid(chosenStr)) {
//                if(result.size() > 0 ) {
//                    if(result.get(result.size() -1).length() < chosenStr.length()) {
//                        result.clear();
//                    }
//                }
//                result.add(chosenStr);
//                maxLength = chosenStr.length();
//            }
//            return maxLength;
//        } else {
//            char c = s.charAt(0);
//            s.deleteCharAt(0);
//            if(isPossible(chosen.append(c).toString())) {
//                maxLength = Math.max(maxLength, helper(s, chosen, maxLength, result));
//            }
//            chosen.deleteCharAt(chosen.length() - 1);
//            if(chosen.length() + s.length() >= maxLength) {
//                maxLength = Math.max(maxLength, helper(s, chosen, maxLength, result));
//            }
//            return maxLength;
//        }
//    }
}

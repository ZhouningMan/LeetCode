package other;

import java.util.ArrayDeque;
import java.util.Deque;

public class SolveTheEquation {

    private static class OneSide {
        int xCount;
        int constant;
    }

    public String solveEquation(String equation) {
        String[]  twoSides = equation.split("=");
        OneSide left = evaluate(twoSides[0]);
        OneSide right = evaluate(twoSides[1]);
        int xCount = left.xCount - right.xCount;
        int constant = right.constant - left.constant;
        if(xCount == 0) {
            if(constant == 0 )return "Infinite solutions";
            else return "No solution";
        } else {
            int result = constant / xCount;
            return "x=" + result;
        }
    }

    private OneSide evaluate(String s) {
        Deque<Integer> operands = new ArrayDeque<>();
        OneSide result = new OneSide();
        char operator = '+';
        //coefficient;
        int i = 0;
        while(i < s.length()) {
            char c = s.charAt(i);
            if(c == '+' || c == '-') {
                if(operands.isEmpty())operands.push(0);
                operator = c;
                i++;
            } else if(c == 'x') {
                if(operator == '+') result.xCount++;
                else result.xCount--;
                operands.push(0);
                calcualte(operator, operands);
                i++;
            } else {
                int val = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    val = val * 10 + (s.charAt(i) - '0');
                    i++;
                }
                if(i < s.length() && s.charAt(i) == 'x') { //this is an coefficient
                    i++;//ignore x
                    if(operator == '+') result.xCount += val;
                    else result.xCount -= val;
                    operands.push(0);
                } else {
                    operands.push(val);
                }
                calcualte(operator, operands);
            }
        }
        result.constant = operands.pop();
        return result;
    }

    private void calcualte(char operator, Deque<Integer> operands) {
        if(operands.size() == 2) {
            int op2 = operands.pop();
            int op1 = operands.pop();
            int val = operator == '+' ? op1 + op2 : op1 - op2;
            operands.push(val);
        }
    }
}

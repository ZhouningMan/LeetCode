package heap_queu_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {

    public int calculate(String s) {
        Deque<Integer> operands = new ArrayDeque<>();
        Deque<Character> operator = new ArrayDeque<>();

        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if(c == '+' || c == '-' || c == '(') {
                operator.push(c);
                ++i;
            } else if(c == ')') {
                operator.pop(); //pop (
                calc(operands, operator);
                ++i;
            } else if(c == ' '){
                ++i;
            } else {
                StringBuilder sb = new StringBuilder();
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    sb.append(s.charAt(i));
                    ++i;
                }
                operands.push(Integer.parseInt(sb.toString()));
                calc(operands, operator);
            }
        }
        return operands.pop();
    }

    private void calc(Deque<Integer> operands, Deque<Character> operator) {
        Character prev = operator.peek();
        while(prev != null && prev != '(') {
            operator.pop();
            int op2 = operands.pop();
            int op1 = operands.pop();

            if(prev == '-') {
                operands.push(op1 - op2);
            } else {
                operands.push(op1 + op2);
            }
            prev = operator.peek();
        }
    }
}

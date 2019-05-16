package math;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculatorII {
    public int calculate(String s) {
        Deque<Integer> operands = new ArrayDeque<>();
        Deque<Character> operator = new ArrayDeque<>();
        int result = 0;
        char prevOp = '+';
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                result = result * 10 + (c - '0');
            } else if (c == '+' || c == '-') {
                operands.push(result);
                result = 0;
                reduce(operands, operator, false);
                prevOp = c;
                operator.push(c);
            } else if (c == '*' || c == '/') {
                operands.push(result);
                result = 0;
                if (prevOp == '*' || prevOp == '/') {
                    reduce(operands, operator, true);
                }
                prevOp = c;
                operator.push(c);
            }
            //space
        }
        operands.push(result);
        reduce(operands, operator, false);
        return operands.pop();
    }

    private void reduce(Deque<Integer> operands, Deque<Character> operators, boolean single){
        while(operands.size() >= 2) {
            int p2 = operands.pop();
            int p1 = operands.pop();
            char op = operators.pop();
            switch (op) {
                case '+' : operands.push(p1 + p2); break;
                case '-' : operands.push(p1 - p2); break;
                case '*' : operands.push(p1 * p2); break;
                case '/' : operands.push(p1 / p2); break;
            }
            if(single) break;
        }
    }

    public static void test() {
        new BasicCalculatorII().calculate("530+194/2/2*3/25*2/5*6/5*8-22/2*2*4+24*11+120/6/2/2*13*62");

    }
}

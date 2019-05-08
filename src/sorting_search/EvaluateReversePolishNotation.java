package sorting_search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiFunction;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(String token : tokens) {
            switch (token) {
                case "+":
                    compute(stack, (op1, op2) -> op2 + op1);
                    break;
                case "-":
                    compute(stack, (op1, op2) -> op2 - op1);
                    break;
                case "*":
                    compute(stack, (op1, op2) -> op2 * op1);
                    break;
                case "/":
                    compute(stack, (op1, op2) -> op2 / op1);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }

    private void compute(Deque<Integer> stack, BiFunction<Integer, Integer, Integer> operation) {
        stack.push(operation.apply(stack.pop(), stack.pop()));
    }
}

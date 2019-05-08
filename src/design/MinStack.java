package design;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Deque<Integer> minStack = new ArrayDeque<>();
    /** initialize your data structure here. */
    public MinStack() {
    }

    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        int x = stack.pop();
        if(x <= minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

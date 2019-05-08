package design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

@SuppressWarnings({"ConstantConditions", "WeakerAccess", "UnusedReturnValue"})
public class MaxStack {
    private final Deque<Integer> stack;
    private final Deque<Integer> maxStack;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new ArrayDeque<>();
        maxStack = new ArrayDeque<>();
    }
    public void push(int x) {
        stack.push(x);
        //only the max so far
        if(maxStack.isEmpty() || maxStack.peek() <= x) {
            maxStack.push(x);
        }
    }
    public int pop() {
        throwIfEmpty();
        int val = stack.pop();
        if(maxStack.peek() == val) {
            maxStack.pop();
        }
        return val;
    }
    public int top() {
        throwIfEmpty();
        return stack.peek();
    }
    public int peekMax() {
        throwIfEmpty();
        return maxStack.peek();
    }
    //This is the most time consuming operations.
    public int popMax() {
        throwIfEmpty();
        int max = maxStack.pop();
        Deque<Integer> cache = new ArrayDeque<>();
        while(stack.peek() < max) {
            //I don't want the max
            cache.push(stack.pop());
        }
        stack.pop(); //remove top elements
        for(Integer i : cache) {
            //reuse our implementation
            push(i);
        }
        return max;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    private void throwIfEmpty() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    public static void test() {
        MaxStack ms = new MaxStack();
        ms.push(5);
        ms.push(1);
        ms.push(5);
        ms.top();
        ms.popMax();
        ms.top();
        ms.peekMax();
        ms.pop();
        ms.top();
    }
}

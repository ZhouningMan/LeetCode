package design;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStackImple2 {
    private long min;
    private final Deque<Long> minStack = new ArrayDeque<>();

    public void push(int x) {
        if(minStack.isEmpty()) {
            minStack.push(0L);
            min = x;
        } else {
            minStack.push((x - min));
            if(x < min) {
                min = x;
            }
        }
    }
    public void pop() {
        if(minStack.isEmpty()) return;
        long val = minStack.pop();
        if(val < 0) {
            //restore previous min
            //current min is actually the real value
            //previous minimum value is current value - delta
            min = min - (int)val;
        }
    }
    public int top() {
        long val = minStack.peek();
        if(val < 0) return (int)min;
        else return (int)(val + min);
    }
    public int getMin() {
        return (int)min;
    }
}

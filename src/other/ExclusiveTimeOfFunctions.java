package other;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        //keep a stack of all the functions that are currently running
        //they might be executing or suspended
        Deque<Integer> stack = new ArrayDeque<>(); //store id only
        int prev = 0;
        for(String s : logs) {
            String[] parts = s.split(":");
            int id = Integer.parseInt(parts[0]);
            String event = parts[1];
            int ts = Integer.parseInt(parts[2]);
            if(event.equals("start")) {
                if(!stack.isEmpty()) {
                    //update the runtime for the current executing method
                    res[stack.peek()] += ts - prev;
                }
                //new method start executing
                stack.push(id);
                prev = ts;
            } else {
                //a function has ended, pay special attention to the +1
                res[stack.pop()] += ts - prev + 1;
                prev = ts + 1;
            }
        }
        return res;
    }
}

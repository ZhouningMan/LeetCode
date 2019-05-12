package heap_queu_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    public String simplifyPath(String path) {
        path = path.replaceAll("/+", "/");
        Deque<String> deque = new ArrayDeque<>();
        int begin = 0;
        int end = path.indexOf('/', begin + 1);
        while(begin >= 0) {
            if(end < 0 ) {
                String dir = path.substring(begin+1);
                pushDir(deque, dir);
            } else {
                String dir = path.substring(begin+1, end);
                pushDir(deque, dir);
            }
            begin = end;
            if(end > 0) end = path.indexOf('/', end + 1);
        }
        StringBuilder sb = new StringBuilder("/");
        while(!deque.isEmpty()) {
            sb.append(deque.pollLast());
            if(!deque.isEmpty()) {
                sb.append('/');
            }
        }
        return sb.toString();
    }

    private void pushDir(Deque<String> deque, String dir) {
        if(dir.isEmpty()) return;
        if(dir.equals("..")) {
            if(!deque.isEmpty()) deque.pop();
        } else if(!dir.equals(".")) {
            deque.push(dir);
        }
    }
}

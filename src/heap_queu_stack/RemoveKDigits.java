package heap_queu_stack;

import java.util.ArrayDeque;
import java.util.Deque;

@SuppressWarnings("Duplicates")
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        int n = num.length();
        int size = n - k;
        if(size == 0) return "0";
        Deque<Character> deque = new ArrayDeque<>();
        //when we need to remove, and the last character
        //because our most significant digits are the smallest, we
        //can be sure our algorithm produces the smallest.
        for(int i = 0; i < num.length(); ++i) {
            char c = num.charAt(i);
            while(!deque.isEmpty() && k >0 && c < deque.peek()) {
                deque.pop();
                k--;
            }
            deque.push(c);
        }
        StringBuilder sb = new StringBuilder();
        boolean begin = true;
        while(!deque.isEmpty() && sb.length() < size) {
            char c = deque.pollLast();
            if(begin && c == '0') continue;
            begin = false;
            sb.append(c);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public String removeKdigitsWrong(String num, int k) {
        int n = num.length();
        int size = n - k;
        if(size == 0) return "0";
        Deque<Character> deque = new ArrayDeque<>();
        //this doesn't work for this case:
        //"112" remove one element,because we have equal element, this doesn't work
        //since we start from least significant digit to most significant one
        for(int i = n - 1; i >=0; --i) {
            char c = num.charAt(i);
            if(deque.size() < size) {
                deque.push(c);
            } else {
                if(c < deque.peek()) {
                    deque.pop();
                    deque.push(c);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean begin = true;
        while(!deque.isEmpty()) {
            char c = deque.pop();
            if(begin && c == '0') continue;
            begin = false;
            sb.append(c);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

package array_string;

import java.util.*;

class RepeatedStringMatch {
    public int repeatedStringMatch(String A, String B) {
        if(A.length() > B.length()) {
            if(A.contains(B)) return 1;
            if((A + A).contains(B)) return 2;
            else return -1;
        }
        String bSeg = B.substring(0, A.length());
        int startIndex = (A + A).indexOf(bSeg);
        if(startIndex < 0) return -1;
        int repeat = 0;
        int aLen = A.length();
        int bIndex = 0;
        //keep the core of the loop simple, everything else is special condition
        while(bIndex + aLen -1 < B.length()) {
            if(bSeg.equals(B.substring(bIndex, bIndex + aLen))) {
                bIndex += aLen;
                repeat++;
            } else {
                return -1;
            }
        }

        String aReminder = A.substring(startIndex);
        String bRemainder = B.substring(bIndex);

        if(startIndex == 0 && bRemainder.length() >0 ) {
            if(A.startsWith(bRemainder)) repeat++;
            else return -1;
        }
        if(startIndex > 0) {
            repeat++;
            if(!aReminder.startsWith(bRemainder)) repeat++;
        }
        return repeat;
    }

    private boolean subPatternMatch(String A, String toMatch) {
        int resetCount = 0;
        int index = 0;
        Deque<Character> toMatchDeque = toDeque(toMatch);
        while(index < toMatch.length() && resetCount <= 1) {
            Character c = toMatchDeque.pollFirst();
            if(c == A.charAt(index++)) {
                index++;
            } else if(index > 0) {
                resetCount++;
                index = 0;
            }
            toMatchDeque.offerLast(c);
        }
        return index == A.length();
    }

    private Deque<Character> toDeque(String toMatch) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : toMatch.toCharArray()) {
            deque.offerLast(c);
        }
        return  deque;
    }






}
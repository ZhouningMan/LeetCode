package recursion;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {
//    public String decodeString2(String s) {
//        String res = "";
//        Stack<Integer> countStack = new Stack<>();
//        Stack<String> resStack = new Stack<>();
//        int idx = 0;
//        while (idx < s.length()) {
//            if (Character.isDigit(s.charAt(idx))) {
//                int count = 0;
//                while (Character.isDigit(s.charAt(idx))) {
//                    count = 10 * count + (s.charAt(idx) - '0');
//                    idx++;
//                }
//                countStack.push(count);
//            }
//            else if (s.charAt(idx) == '[') {
//                resStack.push(res);
//                res = "";
//                idx++;
//            }
//            else if (s.charAt(idx) == ']') {
//                StringBuilder temp = new StringBuilder (resStack.pop());
//                int repeatTimes = countStack.pop();
//                for (int i = 0; i < repeatTimes; i++) {
//                    temp.append(res);
//                }
//                res = temp.toString();
//                idx++;
//            }
//            else {
//                res += s.charAt(idx++);
//            }
//        }
//        return res;
//    }

    public String decodeString3(String s) {
        StringBuilder reader = new StringBuilder();
        StringBuilder countReader = new StringBuilder();
        //A well form string will have three components:
        //1. prefix
        //2. quantifier
        //3. the unit to be repeated
        Deque<String> prefixStack = new ArrayDeque<>();
        Deque<Integer> countStack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                countReader.append(c);
            } else if(c == '[') { //events to store prefix and quantifier
                prefixStack.push(reader.toString());
                reader.setLength(0);
                countStack.push(Integer.parseInt(countReader.toString()));
                countReader.setLength(0);
            } else if(c == ']') {//events to repeat unit
                int count =countStack.pop();
                String unit = reader.toString();
                reader.setLength(0);
                reader.append(prefixStack.pop());
                for(int j = 0; j < count; j++) {
                    reader.append(unit);
                }
            } else {//this character can be either prefix or unit
                reader.append(c);
            }
        }
        return reader.toString();
    }


    //worst time performance is O(n^2)
    public String decodeString(String s) {
        StringBuilder quantifier = new StringBuilder();
        StringBuilder encoded = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int squareCount = 0;
        for(int i = 0; i < s.length(); ++i) { //loop through the characters
            char c = s.charAt(i);
            if (c == '[') { //special case
                if(squareCount>0) encoded.append(c);
                squareCount++;
            } else if (c == ']') { //special case
                squareCount--;
                if (squareCount == 0) {
                    //recursively apply the same logic
                    String expanded = decodeString(encoded.toString());
                    for (int j = 0; j < Integer.parseInt(quantifier.toString()); ++j) {
                        result.append(expanded);
                    }
                    quantifier.setLength(0);
                    encoded.setLength(0);
                } else {
                    encoded.append(c);
                }
            }  else if(squareCount> 0) { //special case
                encoded.append(c);//accumulate string in brackets
            } else if (Character.isDigit(c)) { //special case
                quantifier.append(c);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void test() {
      //  System.out.println(new DecodeString().decodeString("a3[a2[c]]"));
        System.out.println(new DecodeString().decodeString3("a3[a2[c]]"));
    }
}

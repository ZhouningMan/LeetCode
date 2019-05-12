package array_string;

import java.util.*;

public class ReorderLogFiles {
    static class Record {
        String key;
        String content;

        Record(String key, String content) {
            this.key = key;
            this.content = content;
        }
    }
    public String[] reorderLogFiles(String[] logs) {
        List<Record> letters = new ArrayList<>();
        List<Record> digits = new ArrayList<>();
        for(String log : logs) {
            int firstSpace = log.indexOf(' ');
            int secondSpace = log.indexOf(' ', firstSpace + 1);
            String key = log.substring(0, firstSpace);
            String content = log.substring(firstSpace);
            String firstWord = secondSpace == -1 ? log.substring(firstSpace + 1) : log.substring(firstSpace + 1, secondSpace);
            if(isNum(firstWord)) {
                digits.add(new Record(key, content));
            } else {
                letters.add(new Record(key, content));
            }
        }
        letters.sort(Comparator.comparing((Record a) -> a.content).thenComparing(a -> a.key));
        letters.addAll(digits);
        return letters.stream().map(a -> a.key + a.content).toArray(String[]::new);
    }

    private boolean isNum(String s) {
        for(int i = 0 ; i < s.length(); ++i) {
            if(!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
}

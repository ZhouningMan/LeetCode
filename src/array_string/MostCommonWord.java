package array_string;

import java.util.*;

public class MostCommonWord {

    public String mostCommonWordBuiltIn(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        String[] words = paragraph.replaceAll("[^a-zA-Z]+", " ").toLowerCase().split("\\s+");
        Map<String, Integer> wordFreq = new HashMap<>();
        int maxFreq = 0;
        String result = "";
        for (String w : words) {
            if (bannedSet.contains(w)) continue;
            wordFreq.put(w, wordFreq.getOrDefault(w, 0) + 1);
            if (wordFreq.get(w) > maxFreq) {
                result = w;
                maxFreq = wordFreq.get(w);
            }
        }
        return result;
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> count = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int maxFreq = 0;
        String result = "";
        for (int i = 0; i < paragraph.length(); ++i) {
            char c = paragraph.charAt(i);
            boolean isLetter = isLetter(c);
            if (isLetter) {
                c = toLower(c);
                sb.append(c);
            }
            if (!isLetter || i == paragraph.length() - 1) { //two conditions
                if (sb.length() > 0) {
                    String word = sb.toString();
                    sb.setLength(0); //clear string builder
                    if (set.contains(word)) continue;
                    count.put(word, count.getOrDefault(word, 0) + 1);
                    if (count.get(word) > maxFreq) { //better match
                        result = word;
                        maxFreq = count.get(word);
                    }
                }
            }
        }
        return result;
    }

    private char toLower(char c) {
        int diff = 'a' - 'A';
        if (c >= 'A' && c <= 'Z') c += diff;
        return c;
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static void test() {
        new MostCommonWord().mostCommonWordBuiltIn("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"});
    }
}

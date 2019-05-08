package array_string;

import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs) {
            String key =  transorm(s);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    //counting sort
    private String transorm(String s) {
        int[] abz = new int[26];
        for(int i = 0; i < s.length(); ++i) {
            abz[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder(s.length());
        for(int i = 0; i < 26; ++i) {
            int j = abz[i];
            while(j > 0) {
                sb.append(i + 'a');
                j--;
            }
        }
        return sb.toString();
    }
}

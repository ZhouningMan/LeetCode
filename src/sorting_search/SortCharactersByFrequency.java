package sorting_search;

import java.util.*;

public class SortCharactersByFrequency {
    private static class Pair {
        int freq;
        char c;
    }
    @SuppressWarnings("unchecked")
    public String frequencySort(String s) {
        Map<Character, Integer> count = getCharacterIntegerMap(s);
        List<Character>[] buckets = (List<Character>[])(new List[s.length() + 1]);
        for(Map.Entry<Character, Integer> entry : count.entrySet()) {
            if(buckets[entry.getValue()] == null) buckets[entry.getValue()] = new ArrayList<>();
            buckets[entry.getValue()].add(entry.getKey());
        }

        StringBuilder sb = new StringBuilder(s.length());
        for(int i = buckets.length - 1; i >= 0; --i) {
            if(buckets[i] == null) continue;
            for(Character c : buckets[i]) {
                for(int j = 0; j < i; ++j) sb.append(c);
            }
        }
        return sb.toString();
    }

    private Map<Character, Integer> getCharacterIntegerMap(String s) {
        Map<Character, Integer> count = new HashMap<>();
        //find the frequency
        for (int i = 0; i < s.length(); ++i) {
            count.compute(s.charAt(i), (k, val) -> val == null ? 1 : val + 1);
        }
        return count;
    }

    public String frequencySort2(String s) {
        Map<Character, Integer> count = getCharacterIntegerMap(s);
        PriorityQueue<Map.Entry<Character, Integer>> queue =
                new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
        queue.addAll(count.entrySet());
        StringBuilder sb = new StringBuilder(s.length());
        while(!queue.isEmpty()) {
            Map.Entry<Character, Integer> entry = queue.poll();
            for(int i = 0 ; i < entry.getValue(); ++i) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }
}

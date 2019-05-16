package sorting_search;

import java.util.*;
import static java.util.Map.*;

public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int v : nums) {
            freqMap.put(v, freqMap.getOrDefault(v, 0) + 1);
        }

        Queue<Entry<Integer, Integer>> topK = new PriorityQueue<>(Comparator.comparingInt(Entry::getValue));
        for(Entry<Integer, Integer> entry : freqMap.entrySet()) {
            topK.offer(entry);
            if(topK.size() >k) topK.poll();
        }

        Integer[] result = new Integer[k];
        for(int i = k - 1; i >= 0 && !topK.isEmpty(); --i) {
            result[i] = topK.poll().getKey();
        }
        return Arrays.asList(result);
    }
}

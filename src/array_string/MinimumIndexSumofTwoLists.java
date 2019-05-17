package array_string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class MinimumIndexSumofTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> idxMap2 = new HashMap<>();
        //no duplicates
        for(int i = 0; i < list2.length; i++) idxMap2.put(list2[i], i);
        Deque<int[]> candidates = new ArrayDeque<>();
        for(int i = 0; i < list1.length; i++) {
            if(idxMap2.containsKey(list1[i])) {
                int[] newPair = new int[]{i, idxMap2.get(list1[i])};
                if(!candidates.isEmpty()) {
                    int[] pair = candidates.peek();
                    int diff = Integer.compare(pair[0] + pair[1], newPair[0] + newPair[1]);
                    if(diff > 0) {
                        candidates.clear();
                        candidates.push(newPair);
                    } else if(diff == 0) {
                        candidates.push(newPair);
                    }
                } else {
                    candidates.push(newPair);
                }
            }
        }
        if(candidates.isEmpty()) return null;
        else {
            String[] result = new String[candidates.size()];
            for(int i = 0; i < result.length; i++) {
                result[i] = list1[candidates.pop()[0]];
            }
            return result;
        }
    }
}

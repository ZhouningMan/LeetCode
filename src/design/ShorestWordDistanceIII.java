package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShorestWordDistanceIII {

    private final Map<String, List<Integer>> wordIndices;

    public ShorestWordDistanceIII(String[] words) {
        wordIndices = new HashMap<>();
        for(int i = 0; i < words.length; ++i) {
            wordIndices.computeIfAbsent(words[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> idx1 = wordIndices.get(word1);
        List<Integer> idx2 = wordIndices.get(word2);
        return minDist(idx1, idx2);
    }

    //merge like min distance, why does this work
    //Imaging we merge the two indices, then the min distance can only
    //happen between the adjacent indices. So intead of merging them,
    //we just iteratively find the min dist.
    private int minDist(List<Integer> idx1, List<Integer> idx2) {
        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        while(i < idx1.size() && j < idx2.size()) {
            int w1 = idx1.get(i);
            int w2 = idx2.get(j);
            if(w1 > w2) {
                min = Math.min(w1 - w2, min);
                j++;
            } else {
                min = Math.min(w2 - w1, min);
                i++;
            }
        }
        return min;
    }
}

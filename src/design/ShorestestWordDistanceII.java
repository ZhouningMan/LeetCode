package design;

import java.util.HashMap;
import java.util.Map;

public class ShorestestWordDistanceII {
    private final Map<String, Integer> distMap;
    //assuming words.length << # of shortest calls
    public ShorestestWordDistanceII(String[] words) {
        distMap = new HashMap<>();
        for(int i = 1; i < words.length; ++i) {
            for(int j = i -1; j >= 0; j--) {
                String key = key(words[i], words[j]);
                int diff = i - j;
                distMap.compute(key, (k, v) -> v == null ? diff : Math.min(v, diff));
            }
        }
    }

    public int shortest(String word1, String word2) {
        String key1 = key(word1, word2);
        String key2 = key(word2, word1);
        return Math.min(distMap.getOrDefault(key1, Integer.MAX_VALUE),
                distMap.getOrDefault(key2, Integer.MAX_VALUE));
    }
    //assuming word1, and word2 do not contain ',' otherwise i just need to create a Key class
    private String key(String word1, String word2) {
        return word1 + ',' + word2;
    }
}

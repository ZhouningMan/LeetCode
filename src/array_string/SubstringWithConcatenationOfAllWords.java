package array_string;

import java.util.*;

@SuppressWarnings("Duplicates")
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words == null || words.length == 0) return result;
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        int sLen = s.length();
        int wCount = words.length;
        int wLen = words[0].length();
        for (int i = 0; i < sLen - wCount * wLen + 1; ++i) { //O(n) where n is s.length
            Map<String, Integer> map = new HashMap<>(wordMap);
            for (int j = i; j < i + wCount * wLen; j += wLen) { //O(m) where m is words.length
                String word = s.substring(j, j + wLen); //O(wL) where wl is the length of the word
                int count = map.getOrDefault(word, 0);
                if (count == 0) break;
                else if (count == 1) {
                    map.remove(word);
                } else {
                    map.put(word, count - 1);
                }
            }
            if (map.size() == 0) result.add(i);
        }
        return result;
    }

    //Double pointers issue.
    public List<Integer> findSubstringOptimized(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words == null || words.length == 0) return result;
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        int sLen = s.length();
        int wCount = words.length;
        int wLen = words[0].length();
        //the outer loop + the inner loop visit s in O(N) times(at most 2N because the left pointer must visit
        //each character once more)
        //O(nL) where n = s.length and L = words[0].length;
        for (int i = 0; i < wLen; ++i) {
            Map<String, Integer> map = new HashMap<>(wordMap);
            int j = i, k = i;
            while (k < sLen - wLen + 1) {
                String right = s.substring(k, k + wLen);
                k += wLen; //right pointer is always incremented.
                if (!wordMap.containsKey(right)) {  //reset and start over
                    j = k; //update k
                    map = new HashMap<>(wordMap);
                } else {
                    int rightC = map.getOrDefault(right, 0);
                    if (rightC == 1) map.remove(right);
                    else map.put(right, rightC - 1);
                }
                //update left pointer if needed
                if (j < k - wCount * wLen) {
                    String left = s.substring(j, j + wLen);
                    int leftC = map.getOrDefault(left, 0);
                    if (leftC == -1) map.remove(left);
                    else map.put(left, leftC + 1);
                    j += wLen; //update k
                }
                if (map.size() == 0) result.add(j);
            }
        }
        return result;
    }

    private void updateMap(Map<String, Integer> map, String word) {
        int count = map.getOrDefault(word, 0);
        if (count == 1) {
            map.remove(word);
        } else {
            map.put(word, count - 1);
        }
    }

    public static void test() {
        System.out.println(new SubstringWithConcatenationOfAllWords().findSubstringOptimized("barfoofoobarthefoobarman", new String[]{"bar","foo","the"}));
    }
}

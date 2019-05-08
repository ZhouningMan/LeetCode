package sorting_search;

import java.util.*;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for(String w : words) {
            count.compute(w, (key, v) -> v == null ? 1 : v + 1);
        }
        Comparator<Map.Entry<String, Integer>> comparator = (e1, e2 ) ->{
            int diff = e1.getValue() - e2.getValue();
            return diff == 0 ? e2.getKey().compareTo(e1.getKey()) : diff;
        };
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(comparator);

        for(Map.Entry<String, Integer> entry : count.entrySet()) {
            queue.offer(entry);
            if(queue.size() > k) {
                queue.poll();
            }
        }

        //BAD, because queue iterator doesn't preserve any order
       // return queue.stream().map(Map.Entry::getKey).collect(Collectors.toList());
        List<String> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            result.add(0, queue.poll().getKey());
        }
        return result;
    }

    public static void test() {
        new TopKFrequentWords().topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2);
    }
}

class Impl2 {
    class TrieNode {
        TrieNode[] children;
        String word;
        public TrieNode() {
            children = new TrieNode[26];
            this.word = null;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        TrieNode[] buckets = new TrieNode[words.length + 1];
        for (String word : map.keySet()) {
            int freq = map.get(word);
            if (buckets[freq] == null) {
                buckets[freq] = new TrieNode();
            }
            addWord(word, buckets[freq]);
        }
        List<String> res = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && res.size() < k; i--) {
            if (buckets[i] != null) {
                getWords(buckets[i], res, k);
            }
        }
        return res;
    }

    public void getWords(TrieNode node, List<String> list, int k) {
        if (node == null) return;
        if (node.word != null) {
            list.add(node.word);
        }
        if (list.size() == k) return;
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                getWords(node.children[i], list, k);
            }
        }
    }

    public void addWord(String word, TrieNode root) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node.children[word.charAt(i) - 'a'] == null) {
                node.children[word.charAt(i) - 'a'] = new TrieNode();
            }
            node = node.children[word.charAt(i) - 'a'];
        }
        node.word = word;
    }
}

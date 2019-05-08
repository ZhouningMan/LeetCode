package design;

import java.util.*;
import java.util.stream.Collectors;

public class AutocompleteSystem {
    private static final Comparator<Record> COMPARATOR = (a, b) -> {
        int diff = a.freq - b.freq;
        return diff == 0 ? b.sentence.compareTo(a.sentence) : diff;
    };
    private final Node root = new Node();
    private final Map<String, Integer> stringCount = new HashMap<>();
    private final StringBuilder prefix = new StringBuilder();
    private Node curr = root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; ++i) {
            stringCount.put(sentences[i], times[i]);
            addToTrie(sentences[i], root, 0);
        }
    }

    public static void test() {
        var system = new AutocompleteSystem(new String[]{"i love you", "island", "iroman", "i love leetcode"}, new int[]{5, 3, 2, 2});
        //["i"],[" "],["a"],["#"]]
        System.out.println(system.input('i'));
        System.out.println(system.input(' '));
        System.out.println(system.input('a'));
        System.out.println(system.input('#'));
    }

    public List<String> input(char c) {
        if (c == '#') {
            String s = prefix.toString();
            stringCount.compute(s, (k, v) -> v == null ? 1 : v + 1);
            addToTrie(s, root, 0);
            prefix.setLength(0);
            curr = root;
            return new ArrayList<>();
        } else {
            prefix.append(c);
            int idx = c == ' ' ? 26 : c - 'a';
            Node child = curr.children[idx];
            if (child == null) {
                child = new Node();
                curr.children[idx] = child;
            }
            curr = child;
            return collect();
        }
    }

    private void addToTrie(String sentence, Node curr, int pos) {
        if (pos == sentence.length()) return;
        char c = sentence.charAt(pos);
        int idx = c == ' ' ? 26 : c - 'a';
        Node child = curr.children[idx];
        if (child == null) {
            child = new Node();
            curr.children[idx] = child;
        }
        Set<String> hottest = new HashSet<>();
        //This is the difficulty
        hottest.add(sentence);
        while (!child.prefixed.isEmpty()) {
            hottest.add(child.prefixed.poll().sentence);
        }
        for (String s : hottest) {
            child.prefixed.offer(new Record(s, stringCount.get(s)));
            if (child.prefixed.size() > 3) {
                child.prefixed.poll();
            }
        }
        addToTrie(sentence, child, pos + 1);
    }

    private List<String> collect() {
        LinkedList<Record> result = new LinkedList<>(curr.prefixed);
        result.sort(COMPARATOR);
        Collections.reverse(result);
        return result.stream().map(r -> r.sentence).collect(Collectors.toList());
    }

    private static class Record {
        String sentence;
        int freq;

        Record(String s, int f) {
            this.sentence = s;
            this.freq = f;
        }
    }

    private static class Node {
        //Bad choice of using priority queue to store data because it is hard to be
        //continuously process or updated.
        PriorityQueue<Record> prefixed = new PriorityQueue<>(COMPARATOR);
        Node[] children = new Node[27]; //26 characters + space
    }

    private static class AutocompleteSystem2 {
        Node root;
        Node currRoot;
        String prefix;
        public AutocompleteSystem2(String[] sentences, int[] times) {
            root = new Node();
            currRoot = root;
            prefix = "";
            for (int i = 0; i < sentences.length; i++) {
                add(sentences[i], times[i]);
            }
        }

        public List<String> input(char c) {
            if (c == '#') {
                //System.out.println("Adding " + prefix);
                add(prefix, 1);
                prefix = "";
                currRoot = root;
                return new ArrayList<>();
            }
            prefix += c;
            if (currRoot == null || !currRoot.map.containsKey(c)) {
                currRoot = null;
                return new ArrayList<>();
            }
            currRoot = currRoot.map.get(c);
            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> (a.getValue().equals(b.getValue()) ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue()));
            for (Map.Entry<String, Integer> entry : currRoot.counts.entrySet()) {
                pq.offer(entry);
            }
            List<String> res = new ArrayList<String>();
            for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
                res.add(pq.poll().getKey());
            }
            return res;
        }

        private void add(String s, int count) {
            Node current = root;
            for (char ch : s.toCharArray()) {
                Node next = current.map.get(ch);
                if (next == null) {
                    next = new Node();
                    current.map.put(ch, next);
                }
                int c = next.counts.getOrDefault(s, 0);
                next.counts.put(s, c + count);
                current = next;
            }
            current.isEnd = true;
        }

        class Node {
            Map<Character, Node> map;
            boolean isEnd;
            Map<String, Integer> counts;

            public Node() {
                this.map = new HashMap<>();
                this.counts = new HashMap<>();
            }
        }
    }
/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
}

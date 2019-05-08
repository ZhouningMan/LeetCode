package tree_graph;

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        if(!buildGraph(graph, words, 0, words.length -1, 0)) return "";
        StringBuilder sb = new StringBuilder();
        Set<Character> visited = new HashSet<>();
        for(Character c : graph.keySet()) {
            StringBuilder partial = new StringBuilder();
            dfs(c, graph, visited, partial);
            sb.append(partial.reverse().toString());
        }
        return sb.toString();
    }

    private void dfs(Character c, Map<Character, Set<Character>> graph, Set<Character> visited, StringBuilder sb) {
        if(visited.contains(c)) return;
        visited.add(c);
        Set<Character> neighbors = graph.get(c);
        if(neighbors.isEmpty()) sb.append(c);
        else {
            for(Character neighbor : neighbors) {
                dfs(neighbor, graph, visited,sb);
            }
            sb.append(c);
        }
    }

    boolean buildGraph(Map<Character, Set<Character>> graph, String[] words, int begin, int end, int idx) {
        while(words[begin].length() <= idx) {
            begin++;
        }
        if (begin == end) {
            for(int i = idx; i < words[begin].length(); ++i) {
                graph.putIfAbsent(words[begin].charAt(i), new HashSet<>());
            }
            return true;
        } else if(begin < end){
            int gBegin = begin;
            int gEnd = begin;
            for (int i = begin + 1; i <= end; ++i) {
                char prevC = words[gBegin].charAt(idx);
                char currC = words[i].charAt(idx);
                graph.putIfAbsent(prevC, new HashSet<>());
                graph.putIfAbsent(currC, new HashSet<>());
                //begin of new groups
                if (prevC != currC) {
                    graph.get(prevC).add(currC);
                    if(graph.get(currC).contains(prevC)) return false;
                    if(!buildGraph(graph, words, gBegin, gEnd, idx + 1)) return false;
                    gBegin = gEnd;
                } {
                    gEnd++;
                }
            }
            return buildGraph(graph, words, gBegin, gEnd, idx + 1);
        }
        return true;
    }

    public static void test() {
        new AlienDictionary().alienOrder(new String[]{
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        });
    }
}

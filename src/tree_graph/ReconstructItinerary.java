package tree_graph;

import java.util.*;

public class ReconstructItinerary {


    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, Queue<String>> ticketMap = new HashMap<>();
        for(List<String> pair : tickets) {
            String from = pair.get(0);
            String to = pair.get(1);
            ticketMap.computeIfAbsent(from, k ->new PriorityQueue<>()).add(to);
        }

        List<String> result = new LinkedList<>();
        eulerianPath(ticketMap,"JFK", result);
        return result;
    }

    private void eulerianPath(Map<String, Queue<String>> ticketMap, String from, List<String> list) {
        Queue<String> dests = ticketMap.get(from);
        while(dests != null && !dests.isEmpty()) {
            eulerianPath(ticketMap, dests.poll(), list);
        }
        list.add(0, from);
    }

    //BAD solution
    private boolean dfs(Map<String, Queue<String>> ticketMap, List<String> result, String from, String to, int count) {
        result.add(to);
        if(result.size() == count + 1) return true;
        Queue<String> dests = ticketMap.get(to);
        if(dests == null) {
            if(!from.isEmpty()) ticketMap.get(from).add(to);
            result.remove(result.size() - 1);//remove last;
            return false;
        }

        while(!dests.isEmpty()) {
            String next= dests.poll();
            if(dfs(ticketMap, result, to, next, count)) return true;
        }
        if(!from.isEmpty()) ticketMap.get(from).add(to);
        result.remove(result.size() - 1);//remove last;
        return false;
    }

    //DO NOT work because the itinerary could have loop
    public List<String> findItinerary2(List<List<String>> tickets) {
        Map<String, String> ticketMap = new HashMap<>();
        for(List<String> pair : tickets) {
            String from = pair.get(0);
            String to = pair.get(1);
            ticketMap.compute(from, (k, v) -> v == null ? to : v.compareTo(to) > 0 ? to : v);
        }

        List<String> result = new ArrayList<>();
        String start = "JFK";
        while(ticketMap.containsKey(start)) {
            result.add(start);
            start = ticketMap.get(start);
        }
        result.add(start);//last destination
        return result;
    }

    public static void test() {
//        //[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//        List<List<String>> itinerary = List.of(
//                List.of("JFK","SFO"),
//                List.of("JFK","ATL"),
//                List.of("SFO","ATL"),
//                List.of("ATL","JFK"),
//                List.of("ATL","SFO")
//        );

        //[["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
        List<List<String>> itinerary = List.of(
                List.of("JFK","KUL"),
                List.of("JFK","NRT"),
                List.of("NRT","JFK")
        );
        System.out.println(new ReconstructItinerary().findItinerary(itinerary));
    }
}

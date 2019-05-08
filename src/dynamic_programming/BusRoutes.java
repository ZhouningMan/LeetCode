package dynamic_programming;

import java.util.*;

public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(routes == null || routes.length == 0) return -1;
        if(S == T) return 0;
        Map<Integer, List<Integer>> busesForStop = new HashMap<>();
        for(int i = 0; i < routes.length; ++i) {
            for(int stop : routes[i]) busesForStop.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
        }
        //queue contains only stop that is not the destination
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> busTaken = new HashSet<>();
        //BAD IDEA, I don't need to treat initial case so differently!!!!
        //        List<Integer> startBuses = busesForStop.get(S);
        //        for(Integer b : startBuses) {
        //            busTaken.add(b);
        //            for(int stop : routes[b]) {
        //                if(stop == T) return 1;
        //                queue.offer(stop);
        //            }
        //        }
        int res = 0;
        queue.offer(S);
        while(!queue.isEmpty()) {
            int size = queue.size();
            res++; //increment per level
            for(int i = 0; i < size; ++i) {
                int stop = queue.poll();
                List<Integer> passingBuses = busesForStop.get(stop);
                for(Integer b : passingBuses) {
                    if(busTaken.contains(b)) continue;
                    busTaken.add(b);
                    for(int next :routes[b]) {
                        if(next == stop) continue;
                        if(next == T) return res;
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }
}

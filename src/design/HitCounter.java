package design;

import java.util.*;

public class HitCounter{

    private final Deque<Integer> hits;

    /** Initialize your data structure here. */
    public HitCounter() {
        hits = new ArrayDeque<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        hits.offerFirst(timestamp);
        removeOutdated(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        removeOutdated(timestamp);
        return hits.size();
    }

    private void removeOutdated(int timestamp) {
        while(!hits.isEmpty() && hits.peekLast() + 300 <= timestamp) {
            hits.pollLast();
        }
    }

    //Bad implementation
    private static class Impl2 {
        /** Initialize your data structure here. */
        public Impl2() {
        }

        private final TreeMap<Integer, Integer> map = new TreeMap<>();
        private int cumulative = 0;

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public synchronized void hit(int timestamp) {
            cumulative++;
            map.put(timestamp, cumulative);
            //clean up old entries
            int boundary = timestamp - 300;
            Map.Entry<Integer, Integer> entry = map.floorEntry(boundary);
            Map<Integer, Integer> expired = map.subMap(Integer.MIN_VALUE,
                    true,
                    timestamp-300,
                    true);
            map.keySet().removeAll(new HashSet<>(expired.keySet()));
            if(entry != null) {
                map.put(boundary, entry.getValue());
            }
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public synchronized int getHits(int timestamp) {
            int boundary = timestamp - 300;
            Map.Entry<Integer, Integer> entry = map.floorEntry(boundary);
            if(entry != null) {
                return cumulative - entry.getValue();
            } else {
                return cumulative;
            }
        }
    }


}



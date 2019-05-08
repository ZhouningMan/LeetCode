package sorting_search;

import java.util.*;

public class InsertInterval {
    public static void test() {
        List<Interval> intervals = new ArrayList<>();
        intervals = new InsertInterval().insert(intervals, new Interval(2, 5));
        System.out.println(intervals);
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null || newInterval.end < newInterval.start) return intervals;
        TreeSet<Interval> orderedInterval = new TreeSet<>(Comparator.comparingInt(interval -> interval.start));
        orderedInterval.addAll(intervals);
        add(orderedInterval, newInterval);
        return new ArrayList<>(orderedInterval);
    }

    private void add(TreeSet<Interval> intervals, Interval newInterval) {
        Interval endKey = new Interval(newInterval.end, newInterval.end);
        Interval floorStart = intervals.floor(newInterval);
        Interval floorEnd = intervals.floor(endKey);
        if (floorStart == null) {
            if (floorEnd != null) {
                newInterval.end = Math.max(floorEnd.end, newInterval.end);
            }
            intervals.add(newInterval);
        } else if (floorStart.end >= newInterval.start) {
            newInterval.start = floorStart.start;
            newInterval.end = Math.max(floorEnd.end, newInterval.end);
            intervals.remove(newInterval);
            intervals.add(newInterval);
        } else {
            newInterval.end = Math.max(floorEnd.end, newInterval.end);
            intervals.remove(newInterval);
            intervals.add(newInterval);
        }
        //remove duplicates
        Set<Interval> duplicates = new HashSet<>(intervals.subSet(newInterval, false, endKey, true));
        intervals.removeAll(duplicates);
    }

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }

    class Solution {

        public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
            //working with lists and need to change list size, create a new list
            //rather than manipulating list in place.
            List<Interval> linkedList = new LinkedList<>();
            int i = 0;
            //append all smaller interval
            while(i < intervals.size() && intervals.get(i).end < newInterval.start) {
                linkedList.add(intervals.get(i));
                i++;
            }

            //merge, this works because we are given a SORTED list!
            while(i < intervals.size() && intervals.get(i).start <= newInterval.end) {
                Interval existing = intervals.get(i);
                newInterval.start = Math.min(newInterval.start, existing.start);
                newInterval.end = Math.max(newInterval.end, existing.end);
                i++;
            }

            linkedList.add(newInterval);
            while(i < intervals.size()) {
                linkedList.add(intervals.get(i));
                i++;
            }
            return  linkedList;
        }
    }
}

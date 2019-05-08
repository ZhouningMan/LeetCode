package sorting_search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MergeInterval {
    public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }

    public List<Interval> merge(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(interval -> interval.start));
        if(intervals.isEmpty()) return intervals;
        Iterator<Interval> iterator = intervals.iterator();
        List<Interval> merged = new ArrayList<>();
        Interval current = iterator.next();
        while(iterator.hasNext()) {
            Interval next = iterator.next();
            if(next.start > current.end) {
                merged.add(current);
                current = next;
            } else {
                current.start = Math.min(current.start, next.start);
                current.end = Math.max(current.end, next.end);
            }
        }
        merged.add(current);
        return merged;
    }

    public List<Interval> mergeInplace(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(interval -> interval.start));
        if(intervals.isEmpty()) return intervals;
        Iterator<Interval> iterator = intervals.iterator();
        Interval current = iterator.next();
        while(iterator.hasNext()) {
            Interval next = iterator.next();
            if(next.start > current.end) {
                current = next;
            } else {
                current.end = Math.max(current.end, next.end);
                iterator.remove();
            }
        }
        return intervals;
    }
}

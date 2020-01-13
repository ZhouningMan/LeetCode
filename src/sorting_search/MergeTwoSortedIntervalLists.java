package sorting_search;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition of Interval:
 * public classs Interval {
 * int start, end;
 * Interval(int start, int end) {
 * this.start = start;
 * this.end = end;
 * }
 * }
 */
class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MergeTwoSortedIntervalLists {
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        if(list1 == null ){
            return list2;
        } else if(list2 == null) {
            return list1;
        }

        int size1 = list1.size();
        int size2 = list2.size();
        int i = 0, j = 0;
        List<Interval> ans = new ArrayList<>();
        while(i < size1 || j < size2) {
            if(i >= size1) {
                addOrMerge(ans, list2.get(j++));
            } else if (j >= size1) {
                addOrMerge(ans, list1.get(i++));
            } else if (list1.get(i).start < list2.get(j).start) {
                addOrMerge(ans, list1.get(i++));
            } else {
                addOrMerge(ans, list2.get(j++));
            }
        }
        return ans;
    }

    private void addOrMerge(List<Interval> intervals, Interval interval) {
        if(intervals.size() == 0) {
            intervals.add(interval);
            return;
        }
        Interval last = intervals.get(intervals.size() - 1);
        if(last.end >= interval.start) {
            // end is the maximum of the two
            last.end = Math.max(last.end, interval.end);
        } else {
            intervals.add(interval);
        }
    }
}

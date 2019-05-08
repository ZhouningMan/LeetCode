package sorting_search;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {
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
    }

    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(itv ->itv.start));
        PriorityQueue<Interval> ongoingMeetings = new PriorityQueue<>(Comparator.comparingInt(itv ->itv.end));
        ongoingMeetings.offer(intervals[0]);
        int max = ongoingMeetings.size();
        for(int i = 1; i < intervals.length; ++i) {
            Interval interval = intervals[i];
            //Meeting in the PQ have already started, but not end
            while(!ongoingMeetings.isEmpty() && ongoingMeetings.peek().end <= interval.start) {
                ongoingMeetings.poll();//the inner loop can be ran at most intervals.length times.
            }
            ongoingMeetings.offer(interval);
            max = Math.max(max, ongoingMeetings.size());
        }
        return max;
    }
}

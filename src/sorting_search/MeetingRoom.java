package sorting_search;

import java.util.Arrays;

public class MeetingRoom {
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
//        int[] prev = intervals[0];
//        for(int i = 1; i < intervals.length; i++) {
//            int[] curr = intervals[i];
//            if(curr[0] < prev[1]) return false;
//            prev = curr;
//        }

        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < intervals[i-1][1]) return false;
        }
        return true;
    }
}

package heap_queu_stack;

import java.util.*;

@SuppressWarnings("Duplicates")
public class EmployeeFreeTime {
    public int[][] employeeFreeTime2(int[][][] schedule) {
        List<int[]> allIntervals = new ArrayList<>();
        for(int[][] individualSchedule : schedule) {
            allIntervals.addAll(Arrays.asList(individualSchedule));
        }
        if(allIntervals.size() == 0) return null;
        allIntervals.sort(Comparator.comparingInt(a -> a[0]));
        List<int[]> result = new ArrayList<>();
        Iterator<int[]> iterator = allIntervals.iterator();
        int[] prev = iterator.next();
        while(iterator.hasNext()) {
            int[] curr = iterator.next();
            //keep updating prev to interval with higher end.
            if(curr[0] > prev[1]) {
                result.add(new int[]{prev[1], curr[0]});
                prev = curr;
            } else {
                prev = curr[1] > prev[1] ? curr : prev;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public int[][] employeeFreeTime1(int[][][] schedule) {
        List<int[]> allIntervals = mergeSort(schedule, 0, schedule.length -1);
        if(allIntervals.size() == 0) return null;
        List<int[]> result = new ArrayList<>();
        Iterator<int[]> iterator = allIntervals.iterator();
        int[] prev = iterator.next();
        while(iterator.hasNext()) {
            int[] curr = iterator.next();
            //keep updating prev to interval with higher end.
            if(curr[0] > prev[1]) {
                result.add(new int[]{prev[1], curr[0]});
                prev = curr;
            } else {
                prev = curr[1] > prev[1] ? curr : prev;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    private List<int[]> mergeSort(int[][][] schedule, int lo, int hi) {
        if(lo == hi) return Arrays.asList(schedule[lo]);
        int mid = lo + (hi - lo)/2;
        List<int[]> left = mergeSort(schedule, lo, mid);
        List<int[]> right = mergeSort(schedule, mid + 1, hi);
        return merge(left, right);
    }

    private List<int[]> merge(List<int[]> left, List<int[]> right) {
        int i = 0;
        int j = 0;
        int k = left.size() + right.size();
        List<int[]> result = new ArrayList<>(k);
        while(--k >=0) {
            if(i >= left.size()) {
                result.add(right.get(j++));
            } else if(j >= right.size()) {
                result.add(left.get(i++));
            } else {
                int[] leftItem = left.get(i);
                int[] rightItem = right.get(j);
                if(leftItem[0] <= rightItem[0]) {
                    result.add(leftItem);
                    i++;
                } else {
                    result.add(rightItem);
                    j++;
                }
            }
        }
        return result;
    }
}

package sorting_search;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SmallestRange {
    public static void test() {
        List<List<Integer>> nums = Stream.of(
                Stream.of(-5,-4,-3,-2,-1,1).collect(Collectors.toList()),
                Stream.of(1,2,3,4,5).collect(Collectors.toList())
                ).collect(Collectors.toList());
        new SmallestRange().smallestRange(nums);
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Tuple> queue = new PriorityQueue<>(Comparator.comparingInt(tuple -> tuple.val));
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        //A min priority queue for all the items seen.
        for (List<Integer> l : nums) {
            Iterator<Integer> itr = l.iterator();
            int val = itr.next();
            max = Math.max(max, val);
            min = Math.min(min, val);
            queue.offer(new Tuple(val, itr));
        }
        int d = max - min;
        int[] result = new int[2];
        result[0] = min;
        result[1] = max;
        while (queue.size() == nums.size()) {
            Tuple tuple = queue.poll();
            min = tuple.val;
            if (d > max - min) {
                result[0] = min;
                result[1] = max;
                d = max - min;
            }
            if (tuple.iterator.hasNext()) {
                tuple.val = tuple.iterator.next();
                max = Math.max(tuple.val, max);
                queue.offer(tuple);
            }
        }
        return result;
    }

    static class Tuple {
        int val;
        Iterator<Integer> iterator;

        Tuple(int val, Iterator<Integer> iterator) {
            this.val = val;
            this.iterator = iterator;
        }
    }
}

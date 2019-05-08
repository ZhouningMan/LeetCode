package design;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {

    /**
     * initialize your data structure here.
     */
    private PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> hi = new PriorityQueue<>();

    public MedianFinder() {
    }

    public void addNum(int num) {
        lo.offer(num);
        hi.offer(lo.poll());
        if (lo.size() < hi.size()) {
            lo.offer(hi.poll());
        }
    }

    public double findMedian() {
        if (lo.size() == hi.size()) {
            return (lo.peek() + hi.peek()) / 2.0;
        } else {
            return lo.peek();
        }
    }

    public static void test() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.findMedian();
        medianFinder.addNum(3);
        medianFinder.findMedian();
    }
}

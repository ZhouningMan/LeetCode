package sorting_search;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KCloestPoints {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> kClosest = new PriorityQueue<>((a, b) -> {
           long dist1 = a[0]*a[0] + a[1]*a[1];
           long dist2 = b[0]*b[0] + b[1]*b[1];
           return Long.compare(dist2, dist1);
        });

        for(int[] p : points) {
            kClosest.offer(p);
            if(kClosest.size() > K) kClosest.poll();
        }

        int[][] result = new int[K][2];
        for(int i = 0 ; i < K; ++i) {
            result[i] = kClosest.poll();
        }

        return result;
    }

    public int[][] KClosestQuickSelect(int[][] points, int k) {
        int lo = 0, hi = points.length - 1;
        while(lo < hi) {
            int idx = partition(points, lo, hi);
            //[0, k) elements are top k elements that we want.
            //we don't need to deal with zero based or one based here
            if(idx == k) break;
            else if(idx > k) hi = idx - 1;
            else lo = idx + 1;
        }
        return Arrays.copyOf(points, k); //k means length
    }

    private int partition(int[][] points, int lo, int hi) {
        int[] pivot = points[lo];
        long distP = distSq(pivot);
        int i = lo + 1, j = hi;

        //lo [lo+1, ...... ,  hi]
        //we know the loop will terminate, because i potential range [lo +1, hi +1]
        //j potential range [lo, hi]
        while(i <= j) { //because i is incremented, we want to check for equals condition.
            //it is easier to understand with hi and lo check
            //i range from [lo +1, hi]
            //we don't want to do i < j etc for avoid extreme cases like the array has been sorted
            while (i <= hi && distSq(points[i]) < distP) i++;
            //j range from [lo + 1, hi]  as well, so we cannot j >= lo
            while (j > lo && distSq(points[j]) >= distP) j--;
            if(i < j) swap(points, i, j);
        }
        swap(points, lo, j);
        return j;
    }

    private void swap(int[][] points, int i, int j) {
        int[] t = points[i];
        points[i] = points[j];
        points[j] = t;
    }

    private long distSq(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    public static void test() {
        new KCloestPoints().KClosestQuickSelect(new int[][]{{-5,4},{-3,2},{0,1},{-3,7},{-2,0},{-4,-6},{0,-5}}, 6);
    }
}

package sorting_search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k < 0) {
            return result;
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> (a[0] + a[1])));
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], i, 0});
        }

        while(result.size() < k && !minHeap.isEmpty()) {
            int[] item = minHeap.poll();
            result.add(new int[]{item[0], item[1]});
            int nextNum2 = item[3] + 1;
            if( nextNum2 == nums2.length) continue;
            minHeap.offer(new int[]{nums1[item[2]], nums2[nextNum2], item[2], nextNum2});
        }
        return result;
    }


    public List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        if(k == 0 ||  len1 == 0 || len2 == 0) return result;
        int[] oddPair = new int[] {0, 1};
        int[] evenPair = new int[] {1, 0};
        result.add(new int[]{nums1[0], nums2[0]});
        while(result.size() < k) {
            if(!isValid(len1, len2, oddPair) && !isValid(len1, len2, evenPair)) {
                return result;
            } else if(!isValid(len1, len2, oddPair)) {
                result.add(new int[]{nums1[evenPair[0]], nums2[evenPair[1]]});
                move(len1, len2, evenPair);
            } else if(!isValid(len1, len2, evenPair)) {
                result.add(new int[]{nums1[oddPair[0]], nums2[oddPair[1]]});
                move(len1, len2, oddPair);
            } else if(less(oddPair, evenPair, nums1, nums2)) {
                result.add(new int[]{nums1[oddPair[0]], nums2[oddPair[1]]});
                move(len1, len2, oddPair);
            } else {
                result.add(new int[]{nums1[evenPair[0]], nums2[evenPair[1]]});
                move(len1, len2, evenPair);
            }
        }
        return result;
    }

    private void move(int len1, int len2, int[] pair) {
        int next = pair[1] + 1;
        pair[1] = next % len2;
        if (next / len2 == 1) {
            pair[0] += 2;
        }
    }

    private boolean less(int[] oddPair, int[] evenPair, int[] nums1, int[] nums2) {
        int oddSum = nums1[oddPair[0]] + nums2[oddPair[1]];
        int evenSum = nums1[evenPair[0]] + nums2[evenPair[1]];
        return oddSum <= evenSum;
    }

    private boolean isValid(int len1, int len2, int[] pos) {
        return pos[0] < len1 && pos[1] < len2;
    }
//    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
//        List<int[]> result = new ArrayList<>();
//        int len1 = nums1.length;
//        int len2 = nums2.length;
//        if(k == 0 ||  len1 == 0 || len2 == 0) return result;
//        int[] moveFirst = new int[] {1, 0};
//        int[] moveSecond = new int[] {0, 1};
//        result.add(new int[]{nums1[0], nums2[0]});
//        while(result.size() < k) {
//            int firstSum = Integer.MAX_VALUE;
//            if(isValid(len1, len2, moveFirst) ) {
//                firstSum = nums1[moveFirst[0]] + nums2[moveFirst[1]];
//            }
//            while(result.size() < k) {
//                int secondSum = Integer.MAX_VALUE;
//                if(isValid(len1, len2, moveSecond) ) {
//                    secondSum = nums1[moveSecond[0]] + nums2[moveSecond[1]];
//                }
//                if(firstSum < secondSum) {
//                    result.add(new int[]{nums1[moveFirst[0]], nums2[moveFirst[1]]});
//                    moveFirst(len1, moveFirst);
//                    break;
//                } else if(firstSum > secondSum) {
//                    result.add(new int[]{nums1[moveSecond[0]], nums2[moveSecond[1]]});
//                    moveSecond(len2, moveSecond);
//                } else if(moveFirst[0] == moveSecond[0] && moveFirst[1] == moveSecond[1]) {
//                    result.add(new int[]{nums1[moveSecond[0]], nums2[moveSecond[1]]});
//                    moveFirst(len1, moveFirst);
//                    moveSecond(len2, moveSecond);
//                } else if(isValid(len1, len2, moveFirst) || isValid(len1, len2, moveSecond)) {
//                    result.add(new int[]{nums1[moveSecond[0]], nums2[moveSecond[1]]});
//                    moveSecond(len2, moveSecond);
//                } else {
//                    return result;
//                }
//            }
//        }
//        return result;
//    }

    private void moveFirst(int len1, int[] moveFirst) {
        int next = moveFirst[0] + 1;
        moveFirst[0] = next % len1;
        if (next / len1 == 1) {
            moveFirst[1] += 2;
        }
    }

    private void moveSecond(int len2, int[] moveSecond) {
        int next = moveSecond[1] + 1;
        moveSecond[1] = next % len2;
        if (next / len2 == 1) {
            moveSecond[0] += 2;
        }
    }
}

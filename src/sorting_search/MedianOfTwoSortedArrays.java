package sorting_search;

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int X = nums1.length;
        final int Y = nums2.length;
        if(X > Y) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int low = 0;
        int high = X;
        final int M = (X + Y + 1)/2; // + 1 to make it easy to deal with odd case
        //since we are dealing with partition point, we want to use length rather than index.
        //we use <= rather than < is because low = partitionX + 1; and high = partitionX - 1;
        while(low <= high) {
            int partitionX = low + (high - low)/2;
            int partitionY = M - partitionX;

            int xRight = partitionX == X? Integer.MAX_VALUE : nums1[partitionX];
            int xLeft = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX -1];
            int yRight = partitionY == Y ? Integer.MAX_VALUE : nums2[partitionY];
            int yLeft = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY -1];

            if(xRight >= yLeft && xLeft <= yRight) {
                if((X + Y) % 2 == 0) { //even number
                    return (Math.max(xLeft, yLeft) + Math.min(xRight, yRight)) / 2.0;
                } else { //odd number
                    return Math.max(xLeft, yLeft);
                }
            } else if(xRight < yLeft) {
                low = partitionX + 1;
            } else {
                high = partitionX - 1;
            }
        }
        throw new IllegalArgumentException("Bad input...");
    }
}

package sorting_search;

public class FindFirstAndLastPositionOfElementsInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int lower = findTarget(nums, target, true);
        if(lower == -1) return new int[]{-1, -1};
        int higher = findTarget(nums, target, false);
        return new int[]{lower, higher};
    }

    private int findTarget(int[] nums, int target, boolean lower) {
        int low = 0, high = nums.length -1;
        int found = -1;
        while (low <= high) {
            int mid = low + (high - low) /2;
            if(nums[mid] == target) {
                found = mid;
                if(lower) high = mid - 1;
                else low = mid + 1;
            } else if(nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return found;
    }

    public static void test() {
        new FindFirstAndLastPositionOfElementsInSortedArray().searchRange(new int[]{5,7,7,8,8,10}, 8);
    }
}

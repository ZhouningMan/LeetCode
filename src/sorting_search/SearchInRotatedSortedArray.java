package sorting_search;

public class SearchInRotatedSortedArray {

    //works when there is no duplicate
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                if (nums[low] <= nums[mid]) {
                    low = mid + 1;
                } else {
                    if (target == nums[high]) {
                        return high;
                    } else if (target < nums[high]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            } else {
                if (nums[low] <= nums[mid]) {
                    if (target == nums[low]) {
                        return low;
                    } else if (target < nums[low]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end -start )/2;
            if (target == nums[mid]) return mid;
            if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        if (nums[start] == target) return start;
        return -1;
    }
}

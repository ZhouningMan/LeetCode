package sorting_search;

public class SearchRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int low = 0;
        int high  = nums.length - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(target == nums[mid]) {
                return true;
            } else if(target > nums[mid]) {
                if(nums[low] == nums[mid]) {
                    low++;
                } else if(nums[low] < nums[mid]) {
                    low = mid + 1;
                } else {
                    if(target == nums[high]) {
                        return true;
                    } else if(target < nums[high]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            } else {
                if(nums[low] == nums[mid]) {
                    low++;
                } else if(nums[low] < nums[mid]) {
                    if(target == nums[low]) {
                        return true;
                    } else if(target < nums[low]){
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end -start )/2;
            if (target == nums[mid]) return true;
            if (nums[mid] == nums[start]) {
                start++;
            } else if (nums[mid] > nums[start]) {
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
        if (nums[start] == target) return true;
        return false;
    }

}

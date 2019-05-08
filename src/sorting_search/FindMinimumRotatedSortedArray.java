package sorting_search;

public class FindMinimumRotatedSortedArray {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while(low < high) {
            int mid = low + (high - low)/2;
            if(nums[mid] == nums[high]) {
                //no idea whether the min is on the left or right
                //so just reduce high by one.
                high--;
            } else if(nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                //under this condition the min could be the mid value
                high = mid;
            }
        }
        return nums[low];
    }
}

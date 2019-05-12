package sorting_search;

public class SearchInsertPosition {
    @SuppressWarnings("Duplicates")
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        //when we reach here, we know nums[high] < target
        //and we can only insert it after high which is the low
        return low;
    }
}

package sorting_search;

public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length -1 , k - 1);
    }

    private int findKthLargest(int[] nums, int begin, int end, int k) {
        if(begin > end) throw new RuntimeException("Failed to find " + k + " elements");
        int j = partition(nums, begin, end);
        if(j == k ) return  nums[j];
        else if ( j > k){
            return findKthLargest(nums, begin, j - 1, k);
        } else {
            return findKthLargest(nums, j + 1, end, k);
        }
    }

    //descending order
    private int partition(int[] nums, int begin, int end) {
        int pivot = nums[begin];
        int i = begin + 1;
        int j = end;
        while(i < j) {
            while(nums[i] >= pivot) {
                i++;
                if(i > end) break;
            }
            while(nums[j] < pivot) {
                j--;
                if(j < begin) break;
            }
            if(i > j) break;
            swap(nums, i , j);
        }

        if(pivot <= nums[j]) {
            swap(nums, begin, j);
        }
        return j;
    }

    private void swap(int[] number, int i, int j) {
        int temp = number[i];
        number[i] = number[j];
        number[j] = temp;
    }

}

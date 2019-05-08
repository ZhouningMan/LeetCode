package sorting_search;

public class SortColors {
    public void sortColors(int[] nums) {
        int count1 = 0, count2 = 0;
        for (int i =0; i < nums.length; ++i) {
            if(nums[i] == 0) {
                //swap 0 and 1, after this one is out of order
                swap(nums, i, i - (count1 + count2));
                //put i into right location
                if(nums[i] == 1)swap(nums, i, i - count2);
            } else if(nums[i] == 1) {
                count1++;
                swap(nums, i, i - count2);
            } else {
                count2++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

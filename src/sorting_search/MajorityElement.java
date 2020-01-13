package sorting_search;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int target = nums.length / 2;
        while(low < high) {
            int idx = index(nums, low, high);
            if(idx == target) {
                return nums[idx];
            } else if (idx > target) {
                high = idx - 1;
            } else {
                low = idx + 1;
            }
        }
        return nums[low];
    }

    private int index(int[] nums, int low, int high) {
        int pivot = nums[low];
        int left = low + 1;
        int right = high;
        while(left <= right) {
            while(left <= high && nums[left] < pivot) left++;
            while(right > low && nums[right] >= pivot) right--;
            if(left < right) {
                swap(nums, left, right);
            }
        }
        swap(nums, right, low);
        return right;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void test() {
        new MajorityElement().majorityElement(new int[]{3, 3, 4});
    }
}

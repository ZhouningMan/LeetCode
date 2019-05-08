package array_string;

public class MoveZeros {
    public void moveZeroes(int[] nums) {
        int zeros = 0;
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] == 0) {
                zeros++;
            } else if(zeros > 1) {
                nums[i - zeros] = nums[i];
                nums[i] = 0;
            }
        }
    }
}

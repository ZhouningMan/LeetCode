package array_string;

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        if(nums == null || nums.length < 3) return false;
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        //because we are looking for the first triplet, not the longest
        //increasing sequences, we could do this.
        for(int i = 0 ; i < nums.length; i++) {
            //the equal sign is important.
            if(nums[i] <= first) first = nums[i];
            else if(nums[i] <= second) second = nums[i];
            else return true;
        }
        return false;
    }
}

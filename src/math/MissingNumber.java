package math;

public class MissingNumber {
    public int missingNumber(int[] nums) {
        long sum = 0;
        long n = nums.length;
        long expected = (n*n + n)/2;
        for(int i : nums) {
            sum += i;
        }
        return (int)(expected - sum);
    }
}

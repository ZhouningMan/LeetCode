package array_string;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] forward = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i == 0) forward[i] = nums[i];
            else forward[i] = forward[i - 1] * nums[i];
        }
        int[] backward = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1) backward[i] = nums[i];
            else backward[i] = backward[i + 1] * nums[i];
        }
        int[] result = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                result[i] = backward[i + 1];
            } else if (i == n - 1) {
                result[i] = forward[i - 1];
            } else {
                result[i] = forward[i - 1] * backward[i + 1];
            }
        }
        return result;
    }

    public int[] productExceptSelfImproved(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}

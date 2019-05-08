package dynamic_programming;

public class MaximumProductSubarray {


    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        final int LEN = nums.length;
        int[] minus = new int[LEN];
        int[] product = new int[LEN];
        int max = nums[0];
        int j = 0;
        product[0] = nums[0];
        minus[0] = nums[0] < 0 ? 1: 0;
        //how to deal with zeros.
        for(int i = 1; i<LEN; ++i) {
            if(nums[i] == 0) { //reset
                product[i] = 0;
                minus[i] = 0;
                j = i + 1;
                max = Math.max(max, product[i]);
            } else {
                product[i] = product[i -1] == 0? nums[i] : nums[i] * product[i -1];
                minus[i] = nums[i] < 0? minus[i -1] + 1 : minus[i -1];
                max = Math.max(max, product[i]);
                if(minus[i] % 2 != 0) { //negative product
                    while(j < i) {
                        if((minus[i] - minus[j]) % 2 == 0) {
                            max = Math.max(product[i]/product[j], max);
                            break;
                        }
                        j++;
                    }
                }
            }
        }
        return max;
    }

    public static void test() {
        new MaximumProductSubarray().maxProduct(new int[]{3, -1, 4});
    }
}

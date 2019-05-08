package sorting_search;

import java.util.Arrays;

public class SortTransformArray {
//    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
//        if(a != 0) {
//            return quadratic(nums, a, b, c);
//        } else {
//            return linear(nums, b, c);
//        }
//    }

    public static void test() {
        String s = Arrays.toString(new SortTransformArray().sortTransformedArray(new int[]{-4, -2, 2, 4}, -1, 3, 5));
        System.out.println(s);
    }

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int p1 = 0;
        int p2 = n-1;
        int[] arr = new int[n];
        if (a<0) {
            int i = 0;
            while(p1<=p2){
                if(quad(nums[p1],a,b,c)<quad(nums[p2],a,b,c)) {
                    arr[i]=quad(nums[p1],a,b,c);
                    p1++;
                }else{
                    arr[i]=quad(nums[p2],a,b,c);
                    p2--;
                }
                i++;
            }
        }else{
            int i = n-1;
            while(p1<=p2){
                if(quad(nums[p1],a,b,c)>quad(nums[p2],a,b,c)) {
                    arr[i]=quad(nums[p1],a,b,c);
                    p1++;
                }else{
                    arr[i]=quad(nums[p2],a,b,c);
                    p2--;
                }
                i--;
            }
        }
        return arr;
    }


    public int quad(int x, int a, int b, int c) {
        return  a*x*x+b*x+c;
    }

    private int[] linear(int[] nums, int b, int c) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            int val = b * nums[i] + c;
            if (b > 0) {
                result[i] = val;
            } else {
                result[nums.length - 1 - i] = val;
            }
        }
        return result;
    }

    private int[] quadratic(int[] nums, int a, int b, int c) {
        final int len = nums.length;
        final double turn = ((double) b) / (-2 * a);
        int left, right;
        int insertionPOint = Arrays.binarySearch(nums, (int) turn);
        if (insertionPOint < 0) {
            insertionPOint = (insertionPOint + 1) * -1;
        }
        right = insertionPOint;
        left = insertionPOint - 1;
        int i = 0;
        int[] result = new int[len];
        while (i < len) {
            int val;
            if (left < 0) { //exhaust left
                int x = nums[right];
                val = a * x * x + b * x + c;
                right++;
            } else if (right >= len) { //exhaust right
                int x = nums[left];
                val = a * x * x + b * x + c;
                left--;
            } else {
                if ((Math.abs(nums[left] - turn)) < Math.abs(nums[right] - turn)) {
                    int x = nums[left];
                    val = a * x * x + b * x + c;
                    left--;
                } else {
                    int x = nums[right];
                    val = a * x * x + b * x + c;
                    right++;
                }
            }
            if (a > 0) {
                result[i] = val;
            } else {
                result[len - 1 - i] = val;
            }
            i++;
        }
        return result;
    }
}

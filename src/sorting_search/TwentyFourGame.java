package sorting_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("Duplicates")
public class TwentyFourGame {
    public boolean judgePoint24(int[] nums) {
        if(nums == null) return false;
        return findTarget(nums, 24);
    }

    private boolean findTarget(int[] nums, double target) {
        if(nums.length == 0) return false;
        if(nums.length == 1) return Math.abs(nums[0] - target) < 0.000001;
        System.out.println(Arrays.toString(nums) + " ==> " + target);
        for(int i = 0; i < nums.length; ++i) {
            int[] oneLess = copyExcept(nums, i);
            double val = nums[i];
            //because we could have parentheses, so we might just pick this number
            //and perform operations with the result of the rest.
            if(findTarget(oneLess, target - val) ||
                findTarget(oneLess, val - target) ||
                findTarget(oneLess, target / val) ||
                findTarget(oneLess, val/target)) return true;

            //we can perform a binary operation, ourselves, try to search new target from
            //the rest of the operands.
            for(int j = 0; j < oneLess.length; ++j) {
                int[] twoLess = copyExcept(oneLess, j);
                double val2 = oneLess[j];
                double[] nexts = new double[] {val - val2, val + val2, val * val2, val / val2};
                for(double next : nexts) {
                    if(findTarget(twoLess, target - next) ||
                        findTarget(twoLess, next - target)||
                        (next != 0 && findTarget(twoLess, target / next)) ||
                        (next != 0 && findTarget(twoLess, next/target))) return true;
                }
            }
        }
        return false;
    }

    public static int[] copyExcept(int[] nums, int i) {
        int[] result = new int[nums.length - 1];
        int k = 0;
        for(int j = 0; j < nums.length; ++j) {
            if(j == i) continue;
            result[k++] = nums[j];
        }
        return result;
    }

    public static void test() {
        System.out.println(new Imple2().judgePoint24(new int[] {1,2,1,2}));
        //System.out.println(new Imple2().judgePoint24(new int[] {4,1,8,7}));
    }

    public static class Imple2 {
        private static final double EPS = 0.00001;

        public boolean judgePoint24(int[] nums) {
            if(nums == null) return false;
            List<Double> numList = new ArrayList<>();
            for (int n : nums) numList.add((double)n);
            return findTarget(numList);
        }

        private boolean findTarget(List<Double> nums) {
            int size = nums.size();
            if(size == 0) return false;
            if(size == 1) return Math.abs(nums.get(0) - 24) < EPS;

            for(int i = 0; i < size; ++i) {
                for(int j = 0; j < size; ++j) {
                    if(i == j) continue;
                    List<Double> reduced = copyExcept(nums, i, j);
                    double first = nums.get(i);
                    double second = nums.get(j);
                    List<Double> toExplore =
                            new ArrayList<>(Arrays.asList(first + second, first - second, first * second));
                    if(Math.abs(second) > EPS) toExplore.add(first / second);
                    for(double next : toExplore) {
                        reduced.add(next);
                        if(findTarget(reduced))return true;
                        reduced.remove(reduced.size() - 1);
                    }
                }
            }
            return false;
        }

        private List<Double> copyExcept(List<Double> nums, int i , int j) {
            int size = nums.size();
            List<Double> newList = new ArrayList<>(size - 2);
            for(int k = 0; k < size; ++k) {
                if(k == i || k == j) continue;
                newList.add(nums.get(k));
            }
            return newList;
        }
    }
}



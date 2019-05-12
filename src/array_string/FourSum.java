package array_string;

import java.util.*;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        int bi = 0; //b stands for beginning
        for(int i = 0; i < len - 3; ++i) {
            //ignore duplicate
            if(i != bi && nums[bi] == nums[i]) continue;
            bi = i;
            int bj = i + 1;
            for(int j = i+1; j < len -2; ++j) {
                //ignore duplicate
                if(bj != j && nums[bj] == nums[j]) continue;
                bj = j;
                int bk = j + 1;
                int k = j + 1;
                int m = len - 1;
                int sum2 = target - nums[i] - nums[j];
                //two pointers
                while(k < m) {
                    //ignore duplicate
                    if(k != bk && nums[k] == nums[bk]) {
                        k++;
                        continue;
                    }
                    bk = k;
                    if(nums[k] + nums[m] == sum2) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[m]));
                        k++;
                    } else if(nums[k] + nums[m] < sum2) k++;
                     else m--;
                }
            }
        }
        return result;
    }

    private Map<Integer, List<List<Integer>>> sumPair(int[] nums) {
        int bi = 0;
        int len = nums.length;
        Map<Integer, List<List<Integer>>> sumof2 = new HashMap<>();
        for(int i = 0; i < len - 1; ++i) {
            if(bi != i && nums[bi] == nums[i]) continue;
            int bj = i+1;
            for(int j = i+1; j < len; ++j) {
                if(j != bj && nums[bj]== nums[j]) continue;
                bj = j;
                int sum = nums[i] + nums[j];
                sumof2.computeIfAbsent(sum, k -> new ArrayList<>()).add(Arrays.asList(nums[i], nums[j]));
            }
        }
        return sumof2;
    }

    public static void test() {
        System.out.println(new FourSum().fourSum(new int[]{-3,-1,0,2,4,5}, 2));
    }
}

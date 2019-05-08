package dynamic_programming;

import java.util.Arrays;
import java.util.Comparator;

public class RussianDoll {

    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0) return 0;
        Arrays.sort(envelopes, (a, b)-> {
            int res = Integer.compare(a[0], b[0]);
            return res == 0? Integer.compare(b[1], a[1]) : res;
        });
        //dp[i] contains the last element of i + 1 fitted dolls. (tail value)
        int[] dp = new int[envelopes.length];
        int len = 0; //maximum number of fitted dolls
        for(int[] envelop : envelopes) {//each new value could change the tail value and len
            // binarySearch(int[] a, int fromIndex, int toIndex, int key)
            int index = Arrays.binarySearch(dp, 0, len, envelop[1]);
            if(index < 0) {
                index = -index - 1;
            }
            //index is the insertion point, if there exists value from [0, len) that is
            //>= envelop[i], then dp[index] is the ceiling value.
            //otherwise, index location for a new value, i.e index == len
            dp[index] = envelop[1];//replace dp[index] with envelop[
            if(index == len) len++;//the maximum fitted dolls is increased by one.
        }
        return len;
    }

    public int maxEnvelopesQuadratic(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0) return 0;
        //in this quadratic version, sort both the width and heigh in increasing order.
        Arrays.sort(envelopes, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
        //dp[i] contains the maximum dolls including the ith sorted doll.
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for(int i = 1; i < envelopes.length; ++i) {
            for(int j = i -1; j >=0; --j) {
                //this doll can contain previous jth doll.
                if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    //This doesn't work because there could be a very fat doll prevents a lot
    //more doll to be fitted.
    public int maxEnvelopesBADDP(int[][] envelopes) {
        Arrays.sort(envelopes, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
        int res = 0;
        int prevI = -1;
        for(int i = 0; i < envelopes.length; ++i) {
            if(i == 0) {
                res = 1;
                prevI = i;
            } else if(envelopes[i][0] > envelopes[prevI][0] && envelopes[i][1] > envelopes[prevI][1]) {
                res++;
                prevI = i;
            }
        }
        return res;
    }

    public static void test() {
        new RussianDoll().maxEnvelopesQuadratic(new int[][]{{5,4},{6,4},{6,7},{2,3}});
    }
}

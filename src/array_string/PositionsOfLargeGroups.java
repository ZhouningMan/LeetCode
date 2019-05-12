package array_string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositionsOfLargeGroups {

    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> result = new ArrayList<>();
        if(S == null || S.length() < 3) return result;
        int i = 0;
        //For loop is better
//        int j = 1;
//        while(j < S.length()) {
//            if(S.charAt(j)  == S.charAt(i)) {
//                j++;
//            } else {
//                if(j-i >= 3)result.add(Arrays.asList(i, j-1));
//                i = j;
//                j++;
//            }
//        }

        for(int k = 1; k < S.length(); ++k) {
            if(S.charAt(k) != S.charAt(i)) {
                if(k - i >=3) result.add(Arrays.asList(i, k -1));
                i = k;
            }
        }
        if(S.length() - i>=3)result.add(Arrays.asList(i, S.length()-1));
        return result;
    }
}

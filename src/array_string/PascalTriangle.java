package array_string;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for(int r = 0; r < numRows; ++r) {
            List<Integer> rowVals = new ArrayList<>(r + 1);
            for(int c = 0 ; c < r + 1; ++c) {
                if(c == 0 || c == r) {
                    rowVals.add(c, 1);
                } else {
                    List<Integer> prev = result.get(r -1);
                    rowVals.add(c, prev.get(c -1) + prev.get(c));
                }
            }
            result.add(rowVals);
        }
        return result;
    }
}

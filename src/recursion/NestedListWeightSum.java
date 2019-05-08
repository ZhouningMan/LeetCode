package recursion;

import java.util.List;

public class NestedListWeightSum {

    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList == null) return 0;
        int sum = 0;
        for(NestedInteger nested: nestedList) {
            sum += sumNestedInt(nested, 1);
        }
        return sum;
    }

    public int sumNestedInt(NestedInteger nestedInteger, int depth) {
        if(nestedInteger.isInteger()) {
            return depth * nestedInteger.getInteger();
        }
        int sum = 0;
        for(NestedInteger nested : nestedInteger.getList()) {
            sum += sumNestedInt(nested, depth+1);
        }
        return sum;
    }
}

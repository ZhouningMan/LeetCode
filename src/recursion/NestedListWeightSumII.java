package recursion;

import java.util.*;

public class NestedListWeightSumII {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        Queue<NestedInteger> queue = new ArrayDeque<>();
        int unweighted = 0;
        int total = 0;
        for (NestedInteger next: nestedList) {
            queue.offer(next);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.poll();
                if (current.isInteger()) levelSum += current.getInteger();
                List<NestedInteger> nextList = current.getList();
                queue.addAll(nextList);
            }
            unweighted += levelSum;
            total += unweighted; //total will be weighted because we keep adding weighted
        }
        return total;
    }

    public int depthSumInverseBruteForce(List<NestedInteger> nestedList) {
        if(nestedList == null) return 0;
        Map<Integer, List<NestedInteger>> nestedMap = new HashMap<>();
        int maxLevel = collect(nestedList, nestedMap, 1);
        int sum = 0;
        for(Map.Entry<Integer, List<NestedInteger>> entry : nestedMap.entrySet()) {
            sum += sumNested(entry.getValue(), maxLevel - entry.getKey() + 1);
        }
        return sum;
    }

    private int collect(List<NestedInteger> nestedList, Map<Integer, List<NestedInteger>> nestedMap, int level) {
        int maxLevel = level;
        for(NestedInteger nested: nestedList) {
            if(nested.isInteger()) nestedMap.computeIfAbsent(level, k-> new ArrayList<>()).add(nested);
            else {
                maxLevel = Math.max(maxLevel, collect(nested.getList(), nestedMap, level +1));
            }
        }
        return maxLevel;
    }

    private int sumNested(List<NestedInteger> nestedList, int level) {
        int sum = 0;
        for(NestedInteger n : nestedList) {
            sum += n.getInteger() * level;
        }
        return sum;
    }
}

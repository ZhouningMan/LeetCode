package dynamic_programming;

import java.util.*;
import java.util.function.BiFunction;

public class OddEvenJump {
    public int oddEvenJumps(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        int[] dpo = new int[len]; // if the next jump is odd jump
        int[] dpe = new int[len]; // if the next jump is even jump
        // we need to sort by value, but we need to remember the index,
        // also, there could be duplicate values, so we have to build map dynamically
        TreeMap<Integer, Integer> valToIdx = new TreeMap<>();
        dpo[len - 1] = 1;
        dpe[len - 1] = 1;
        valToIdx.put(A[len-1], len-1);
        for(int i = len - 2; i >= 0; i--) {
            Map.Entry<Integer, Integer> ceiling = valToIdx.ceilingEntry(A[i]);
            if(ceiling != null) {
                dpo[i] = dpe[ceiling.getValue()];
            }
            Map.Entry<Integer, Integer> floor = valToIdx.floorEntry(A[i]);
            if(floor != null) {
                dpe[i] = dpo[floor.getValue()];
            }
            valToIdx.put(A[i], i);
        }
        return Arrays.stream(dpo).sum();
    }

    public int oddEvenJumps2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        int[] dpo = new int[len]; // if the next jump is odd jump
        int[] dpe = new int[len]; // if the next jump is even jump
        Integer[] indices = new Integer[len];
        for(int i = 0; i < len; ++i) {
            indices[i] = i;
        }
        Arrays.sort(indices, (i, j) -> Integer.compare(A[i], A[j]));
        Map<Integer, Integer> ceilingMap = buildCloest(indices, (a, b) -> a <= b);
        Arrays.sort(indices, (i, j) -> Integer.compare(A[j], A[i]));
        Map<Integer, Integer> floorMap = buildCloest(indices, (a, b) -> a <= b);
        dpo[len - 1] = 1;
        dpe[len - 1] = 1;
        for(int i = len - 2; i >= 0; i--) {
            Integer ceiling = ceilingMap.get(i);
            if(ceiling != null) {
                dpo[i] = dpe[ceiling];
            }
            Integer floor = floorMap.get(i);
            if(floor != null) {
                dpe[i] = dpo[floor];
            }
        }
        return Arrays.stream(dpo).sum();
    }

    private Map<Integer, Integer> buildCloest(Integer[] A, BiFunction<Integer, Integer, Boolean> comp) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int v : A) {
            while(stack.size() > 0 && comp.apply(stack.peek(), v)) {
                map.put(stack.pop(), v);
            }
            stack.push(v);
        }
        return map;
    }

    public static void test() {
        OddEvenJump solution = new OddEvenJump();
        int[] input = new int[]{1,2,3,2,1,4,4,5};
        System.out.println(solution.oddEvenJumps(input));
    }
}

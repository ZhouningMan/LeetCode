package other;

import java.util.*;

public class Candy {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length]; //initialize to 0;
        Arrays.fill(candies, 1);
        List<Integer> startingPoint = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < ratings.length; ++i) {
            boolean leftSmaller = false;
            if(i > 0) leftSmaller = ratings[i] > ratings[i-1];
            boolean rightSmaller = false;
            if(i + 1 < ratings.length ) rightSmaller = ratings[i] > ratings[i + 1];
            if(leftSmaller && rightSmaller) {
                //no need to do bound check because when any of this variable is true,
                //the value is within bound.
                graph.computeIfAbsent(i-1, k -> new ArrayList<>()).add(i);
                graph.computeIfAbsent(i+1, k->new ArrayList<>()).add(i);
            } else if(leftSmaller) {
                graph.computeIfAbsent(i-1, k -> new ArrayList<>()).add(i);
            } else if(rightSmaller) {
                graph.computeIfAbsent(i+1, k->new ArrayList<>()).add(i);
            } else {
                startingPoint.add(i);
            }
        }
        for(int s : startingPoint) traverse(candies, graph, s);
        return Arrays.stream(candies).sum();
    }

    private void traverse(int[] candies, Map<Integer, List<Integer>> graph, int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(s);
        while(!queue.isEmpty()) {
            Integer i = queue.poll();
            if(!graph.containsKey(i)) continue;
            List<Integer> neighbors = graph.get(i);
            for(Integer n : neighbors) {
                candies[n] = Math.max(candies[n], candies[i] + 1);
                queue.offer(n);
            }
        }
    }

    public static void test() {
        new Candy().candy(new int[] {1, 2, 2});
    }
}

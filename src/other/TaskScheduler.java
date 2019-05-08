package other;

import java.util.*;

public class TaskScheduler {
    static class Tuple {
        int cycle;
        Iterator<Character> iterator;
        Tuple(int cycle, Iterator<Character> itr) {
            this.cycle = cycle;
            this.iterator = itr;
        }
    }
     public int leastInterval(char[] tasks, int n) {
        Map<Character, List<Character>> pendingTasks = new HashMap<>();
        for(char c : tasks) pendingTasks.computeIfAbsent(c, k->new ArrayList<>()).add(c);
        PriorityQueue<Tuple> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.cycle));
        for(List<Character> pending : pendingTasks.values()) {
            Iterator<Character> itr = pending.iterator();
            itr.next();
            queue.offer(new Tuple(1, itr));
        }
        int cycles = 0;
        while(!queue.isEmpty()) {
            cycles++;
            Tuple tuple = queue.poll();
            if(tuple.cycle <= cycles) {//good to execute
                if(tuple.iterator.hasNext()) {
                    tuple.cycle += n + 1; //next tasks
                    tuple.iterator.next();//remove task
                    queue.offer(tuple);
                }
            } else {
                queue.offer(tuple); //idle do nothing
            }
        }
        return cycles;
    }
}

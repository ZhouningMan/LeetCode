package other;

import java.util.*;

public class TheSkylineProblem {
    public static final int LI = 0;
    public static final int RI = 1;
    public static final int HI = 2;

    public List<int[]> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> endQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[RI]));
        //TreeSet allows us to remove a element in log(N) time
        TreeSet<int[]> heightQueue = new TreeSet<>(Comparator.comparingInt(a -> a[HI]));
        final int LEN = buildings.length;
        List<int[]> result = new ArrayList<>();
        int prevHeight = 0;
        for(int i = 0;  i <= LEN; ++i) {
            int[] newBuilding = i == LEN ? new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, 0} : buildings[i];
            //remove buildings whose Ri < Li of new buildings
            while(!endQueue.isEmpty()) {
                int[] insertedBuilding = endQueue.peek();
                 if(insertedBuilding[RI] == newBuilding[LI] && insertedBuilding[HI] == newBuilding[HI]) {
                    endQueue.poll();
                    heightQueue.remove(insertedBuilding);
                     prevHeight = newBuilding[HI];
                } else if(insertedBuilding[RI] <= newBuilding[LI]) {
                    endQueue.poll();
                    heightQueue.remove(insertedBuilding);
                    prevHeight = heightQueue.isEmpty() ? 0: heightQueue.last()[HI];
                    if(insertedBuilding[HI] > prevHeight) {
                        result.add(new int[]{insertedBuilding[RI], prevHeight});
                    }
                } else {
                    break;
                }
            }
            if(newBuilding[HI] > prevHeight) { //we can see a new building
                result.add(new int[]{newBuilding[LI], newBuilding[HI]});
            }
            endQueue.offer(newBuilding);
            heightQueue.add(newBuilding);
        }
        return result;
    }

    public static void test() {
        new TheSkylineProblem().getSkyline(new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}});
    }
}

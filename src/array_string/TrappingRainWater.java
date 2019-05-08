package array_string;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class TrappingRainWater {

    public int trap(int[] A){
        int a=0;
        int b=A.length-1;
        int max=0;
        int leftmax=0;
        int rightmax=0;
        while(a<=b){
            leftmax=Math.max(leftmax,A[a]);
            rightmax=Math.max(rightmax,A[b]);
            if(leftmax<rightmax){
                max+=(leftmax-A[a]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
                a++;
            }
            else{
                max+=(rightmax-A[b]);
                b--;
            }
        }
        return max;
    }



//    public int trap(int[] height) {
//        ArrayList<Integer> localTrap = new ArrayList<>();
//        localTrap.add(0);
//        int trapIndex = 0;
//        boolean findingLeft = true; //left barrier
//        boolean findingMin = false;
//        boolean findingRight = false;
//
//        int totalWater = 0;
//        for(int i = 0; i < height.length; ++i) {
//            if(findingLeft) {
//                if(localTrap.get(trapIndex) <= height[i]) {
//                    localTrap.set(trapIndex, height[i]);
//                } else {
//                    trapIndex++;
//                    localTrap.add(height[i]);
//                    findingLeft = false;
//                    findingMin = true;
//                    continue;
//                }
//            }
//
//            if(findingMin) {
//                if(localTrap.get(trapIndex) >= height[i]) {
//                    trapIndex++;
//                    localTrap.add(height[i]);
//                } else {
//                    trapIndex++;
//                    localTrap.add(height[i]);
//                    findingMin = false;
//                    findingRight = true;
//                    continue;
//                }
//            }
//
//            if(findingRight) {
//                if(localTrap.get(trapIndex) <= height[i]) {
//                    trapIndex++;
//                    localTrap.add(height[i]);
//                } else {
//                    totalWater += water(localTrap);
//                    Integer newLeft = localTrap.get(trapIndex);
//                    localTrap.clear();
//                    localTrap.add(newLeft);
//                    localTrap.add(height[i]);
//                    trapIndex = 1;
//                    findingMin = true;
//                    findingRight = false;
//                }
//            }
//        }
//
//        if(findingRight) {
//            totalWater += water(localTrap);
//        }
//
//        return totalWater;
//    }
//
//    private int water(ArrayList<Integer> trap) {
//        System.out.println(trap);
//        int len = trap.size();
//        int min = Math.min(trap.get(len/2), trap.get(len/2) - 1);
//        int barrier = Math.min(trap.get(0), trap.get(len - 1));
//        int localWater = (barrier - min) * (len - 2);
//        for(int i = 1 ; i < len - 1; ++i) {
//            localWater = localWater - (Math.min(trap.get(i), barrier) - min);
//        }
//        return localWater;
//    }


}

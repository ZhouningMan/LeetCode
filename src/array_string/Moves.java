package array_string;

import java.util.Arrays;
import java.util.List;

public class Moves {
    public static void  test() {
        List<int[]> testInputs = Arrays.asList(
                new int[0],
                new int[]{2},
                new int[]{1,2,3,4,5,6},
                new int[]{4,3,2,1},
                new int[]{6,4,2,1,5,7},
                new int[]{7,8,7,8,7,8});
        for(int[] input : testInputs) {
            System.out.println("Before: " + Arrays.toString(input));
            System.out.println("array_string.Moves: " + moves(input));
            System.out.println("After: " + Arrays.toString(input));
        }
    }

    private static int moves(int[] array ) {
        int start = 0;
        int end = array.length - 1;
        int totalMoves = 0;
        while(start < end) {
            while(array[start]%2 == 0) start++;
            while(array[end]%2 != 0) end--;
            if(start < end) {
                move(array, start, end);
                totalMoves++;
            }
        }
        return totalMoves;
    }

    private static void move(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

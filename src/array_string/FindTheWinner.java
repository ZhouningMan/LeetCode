package array_string;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FindTheWinner {

    public static String winners(int[] andrea, int[] maria, String command) {
        int[] points = new int[] {0, 0};
        int index = command.equals("Even") ? 0 : 1;
        while(index < andrea.length || index < maria.length) {
            points[0] += andrea[index] - maria[index];
            points[1] += maria[index] - andrea[index];
            index += 2;
        }
        if(points[0] == points[1]) return "Tie";
        else if(points[0] > points[1]) return "Andrea";
        else return "Maria";
    }

    public static void test() {
        List<int[]> andrea = Arrays.asList(
            new int[]{1,2,3,4},
            new int[]{4,0,2,1},
            new int[]{5},
            new int[0],
            new int[]{1,2,3}
        );

        List<int[]> maria = Arrays.asList(
            new int[]{5,6,7,8},
            new int[]{3,1,2,1},
            new int[]{3},
            new int[0],
            new int[]{1,2,3}
        );

        List<String> commands = Arrays.asList("Even", "Odd");

        for(int i = 0; i < andrea.size(); ++i) {
            for(String command: commands) {
                System.out.println(command);
                System.out.println(Arrays.toString(andrea.get(i)));
                System.out.println(Arrays.toString(maria.get(i)));
                System.out.println("Winner: " + winners(andrea.get(i), maria.get(i), command));
            }
        }
    }
}

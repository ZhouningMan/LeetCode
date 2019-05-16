package other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PrisonCellsAfterNDay {
    public static void test() {
        new PrisonCellsAfterNDay().prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7);
    }

    public int[] prisonAfterNDays(int[] cells, int N) {
        //find a pattern dynamically;
        Map<String, Integer> seen  = new HashMap<>();
        seen.put(Arrays.toString(cells), 0);
        int i = 1;
        int cycle = -1;
        for(; i <= N; ++i) {
            cells = transform(cells);
            String key = Arrays.toString(cells);
            if(seen.containsKey(key))  {
                cycle = i - seen.get(key);
                break;
            }
            seen.put(Arrays.toString(cells), i);
        }
        if(i < N) {
            for(int j = 0; j < (N - i) % cycle; ++j) cells = transform(cells);
        }
        return cells;
    }

    private int[] transform(int[] cells) {
        int[] copy = new int[cells.length];
        for (int i = 1; i < cells.length - 1; ++i) {
            copy[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return copy;
    }
}

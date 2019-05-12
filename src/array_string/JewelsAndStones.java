package array_string;

import java.util.Arrays;

public class JewelsAndStones {
    public int numJewelsInStones(String J, String S) {
        int[] jewels = new int[128];
        Arrays.fill(jewels, -1);
        for(int i = 0; i < J.length(); ++i) {
            jewels[J.charAt(i)] = 0;
        }

        for(int i = 0; i < S.length(); ++i) {
            char c = S.charAt(i);
            if(jewels[c] == -1) continue;
            jewels[c] += 1;
        }

        int sum = 0;
        for(int i = 0; i < jewels.length; ++i) {
            if(jewels[i] > 0) sum += jewels[i];
        }
        return sum;
    }
}

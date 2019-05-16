package other;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        int[] lastIdx = new int[26];
        for(int i = 0; i < S.length(); ++i) {
            lastIdx[S.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int begin = 0;
        while (begin < S.length()) {
            int end = lastIdx[S.charAt(begin) - 'a'];
            for(int i = begin; i <= end; ++i) {
                end = Math.max(end, lastIdx[S.charAt(i) - 'a']);
            }
            result.add(end - begin + 1);
            begin = end + 1;
        }
        return result;
    }
}

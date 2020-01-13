import java.util.*;

class Solution {
    public String[] solution(int N, int K) {
        if (N == 0) {
            return new String[] {""};
        }
        ArrayList<String> result = new ArrayList<>();
        String[] ans = solution(N - 1, K);
        for (String p : ans) {
            for (char l : new char[]{'a', 'b', 'c'}) {
                int pLen = p.length();
                if (pLen == 0 || p.charAt(pLen - 1) != l) {
                    result.add(p + l);
                }
            }
        }
        Math.ceil()
        int prefSize = Math.min(result.size(), K);
        return result.subList(0, prefSize).toArray(new String[prefSize]);
    }

    public static void test() {
        Solution s = new Solution();
        String[] ans = s.solution(5, 0);
        System.out.println(Arrays.toString(ans));
    }
}

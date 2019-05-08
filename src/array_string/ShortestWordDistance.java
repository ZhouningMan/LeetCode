package array_string;

public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int idx1 = Integer.MAX_VALUE;
        int idx2 = Integer.MAX_VALUE;
        int dist = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; ++i) {
            if(words[i].equals(word1)) {
                dist = Math.min(dist, Math.abs(idx2 - i));
                idx1 = i;
            } else if(words[i].equals(word2)) {
                dist = Math.min(dist, Math.abs(idx1 - i));
                idx2 = i;
            }
        }
        return dist;
    }
}

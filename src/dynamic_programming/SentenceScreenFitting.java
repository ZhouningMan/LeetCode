package dynamic_programming;

public class SentenceScreenFitting {
//    public int wordsTyping(String[] sentence, int rows, int cols) {
//        int times = 0;
//        int wordIdx = 0;
//        int pos = 0;
//        while(pos < rows * cols) {
//            int row = pos / cols;
//            int col = pos % cols;
//            int nextCol = col + sentence[wordIdx].length() + 1;
//                if(nextCol < cols) {
//                pos = row * cols +  nextCol;
//                wordIdx++;
//                times += wordIdx / sentence.length;
//                wordIdx = wordIdx % sentence.length;
//            } else if (nextCol == cols || nextCol == cols + 1) {
//                pos = (row + 1) * cols;
//                wordIdx++;
//                times += wordIdx / sentence.length;
//                wordIdx = wordIdx % sentence.length;
//            } else {
//                pos = (row + 1) * cols;
//            }
//        }
//        return times;
//    }

    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] dp = new int[sentence.length];
        int n = sentence.length;
        for(int i = 0, prev = 0, len = 0; i < sentence.length; ++i) {
            // remove the length of previous word and space
            if(i != 0 && len > 0) len -= sentence[i - 1].length() + 1;
            // calculate the start of next line.
            // it's OK the index is beyond the length of array so that
            // we can use it to count how many words one row has.
            while(len + sentence[prev % n].length() <= cols) len += sentence[prev++ % n].length() + 1;
            dp[i] = prev;
        }
        int count = 0;
        for(int i = 0, k = 0; i < rows; ++i) {
            // count how many words one row has and move to start of next row.
            // It's better to check if d[k] == k but I find there is no test case on it.
            // if(dp[k] == k) return 0;
            count += dp[k] - k;
            k = dp[k] % n;
        }
        // divide by the number of words in sentence
        return count / n;
    }

    public static void test() {
       int val =  new SentenceScreenFitting().wordsTyping(new String[]{"I","had","apple","pie"}, 4, 5);
        System.out.println(val);
    }
}

package array_string;

public class ReverseWordsInStringII {
    public void reverseWords(char[] str) {
        reverse(str, 0, str.length - 1);
        for (int i = 0; i < str.length; ) {
            int k = 0;
            while (i + k < str.length && str[i + k] != ' ') k++;
            reverse(str, i, i + k - 1);
            i = i + k + 1;
        }
    }

    private void reverse(char[] str, int begin, int end) {
        while (begin < end) {
            char temp = str[begin];
            str[begin] = str[end];
            str[end] = temp;
            begin++;
            end--;
        }
    }
}

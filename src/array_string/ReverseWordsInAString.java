package array_string;

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        //clean space;
        StringBuilder sb = new StringBuilder();
        boolean space = true;
        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if(s.charAt(i) != ' ') {
                space = false;
                sb.append(c);
            } else if(!space) {
                sb.append(c);
                space = true;
            }
        }
        if(sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') sb.deleteCharAt(sb.length() -1);
        sb.reverse();//reverse the entire string
        int begin = 0, end = 0;
        while(end <= sb.length()) {
            if(end == sb.length() || sb.charAt(end) == ' ') {
                reverse(sb, begin, end - 1);
                begin = end + 1;
            }
            end++;
        }
        return sb.toString();
    }

    private void reverse(StringBuilder sb, int begin, int end) {
        while(begin < end) {
            char c = sb.charAt(begin);
            sb.setCharAt(begin, sb.charAt(end));
            sb.setCharAt(end, c);
            begin++;
            end--;
        }
    }
}

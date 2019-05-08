package array_string;

import java.util.Arrays;
import java.util.Comparator;

public class BoldTagInString {

    public String addBoldTag(String s, String[] dict) {
        Arrays.sort(dict, (s1, s2) -> s2.length() - s1.length());
        boolean[] boldChar = new boolean[s.length()];
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < dict.length; j++) {
                if(s.startsWith(dict[j], i)) {
                    setBoldChar(boldChar, i, i + dict[j].length() - 1);
                    break;
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        boolean bold = false;
        for(int i = 0; i < s.length(); i++) {
            if(!bold && boldChar[i]) {
                sb.append("<b>");
                bold = true;
            } else if(bold && !boldChar[i]) {
                sb.append("</b>");
                bold = false;
            }
            sb.append(s.charAt(i));
        }
        if(bold) {
            sb.append("</b>");
        }
        return sb.toString();
    }

    public void setBoldChar(boolean[] mark, int start, int end) {
        for(int i = start; i <= end; ++i) {
            mark[i] = true;
        }
    }
}

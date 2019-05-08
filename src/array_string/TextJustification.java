package array_string;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public static void test() {
        new TextJustification().fullJustify(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"
        }, 16);
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        final int LEN = words.length;
        while (i < LEN) {
            List<String> line = new ArrayList<>();
            int count = 0;
            //read a line
            while (count + line.size() < maxWidth && i < LEN) {
                String next = words[i];
                if (count + line.size() + next.length() <= maxWidth) {
                    count += next.length();
                    line.add(next);
                    i++;
                } else {
                    break;
                }
            }
            //read words enough for a line.
            //format the line
            boolean leftJustfy = i == LEN;
            String s = formatLine(line, count, maxWidth, leftJustfy);
            result.add(s);
        }
        return result;
    }

    //format a line based on the rules
    private String formatLine(List<String> line, int count, int maxWidth, boolean leftJustify) {
        StringBuilder sb = new StringBuilder();
        if (line.size() == 1) {
            sb.append(line.get(0));
            appendSpace(sb, maxWidth - count);
        } else if(leftJustify) {
            int endSpace = maxWidth - count - line.size() + 1;
            for(int i = 0; i < line.size(); ++i) {
                sb.append(line.get(i));
                if(i < line.size() - 1) sb.append(' ');
            }
            appendSpace(sb, endSpace);
        } else {
            int insertion = line.size() - 1;
            int minSpace = (maxWidth - count) / insertion;
            int extraSpace = (maxWidth - count) % insertion;
            for (int i = 0; i < line.size(); ++i) {
                sb.append(line.get(i));
                if (i < line.size() - 1) {
                    int spaceCount = minSpace;
                    if (extraSpace > 0) {
                        spaceCount++;
                        extraSpace--;
                    }
                    appendSpace(sb, spaceCount);
                }
            }
        }
        return sb.toString();
    }

    //padding
    private void appendSpace(StringBuilder sb, int num) {
        for (int i = 0; i < num; i++) {
            sb.append(' ');
        }
    }
}

package array_string;

public class StringCompression {
    public int compress(char[] chars) {
        if(chars == null || chars.length == 0) return 0;
        int pos = 0;
        int count = 1;
        char group = chars[0];
        int begin = 0;
        for(int i = 1; i < chars.length; ++i) {
            if(chars[i] == group) {
                count++;
            } else {
                pos = update(chars, begin, count, pos);
                //reset
                count = 1;
                group = chars[i];
                begin = i;
            }
        }
        pos = update(chars, begin, count, pos);
        return pos;
    }

    private int update(char[] chars ,int begin, int count, int pos) {
        chars[pos++] = chars[begin];
        if(count <= 1) {
            return pos;
        }
        for(char c : String.valueOf(count).toCharArray()) {
            chars[pos++] = c;
        }
        return pos;
    }
}

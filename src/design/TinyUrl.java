package design;

import java.util.HashMap;
import java.util.Map;

public class TinyUrl {
    private final String RADIX = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final String PREFIX = "http://tinyurl.com/";
    private final Map<String, String> cache = new HashMap<>();
    private long total = 0;
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String code = getCode(total);
        total++;
        cache.put(code, longUrl);
        return PREFIX + code;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String code = shortUrl.substring(PREFIX.length());
        return cache.get(code);
    }

    private String getCode(long count) {
        StringBuilder sb = new StringBuilder();
        while(count > RADIX.length()) {
            int index = (int)(count % RADIX.length());
            sb.append(RADIX.charAt(index));
            count = count / RADIX.length();
        }
        sb.append(RADIX.charAt((int)count));
        return sb.toString();
    }

    public static void test() {
        var tests = new String[]{
                "https://leetcode.com/problems/encode-and-decode-tinyurl/submissions/",
                "https://leetcode.com/problems/encode-and-decode-tinyurl/discuss/100268/Two-solutions-and-thoughts"
        };
        TinyUrl codec = new TinyUrl();
        for(var s : tests) {
            var code = codec.encode(s);
            System.out.println(code);
            System.out.println("->");
            System.out.println(codec.decode(code));
        }
    }
}

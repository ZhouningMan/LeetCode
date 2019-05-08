package array_string;

public class ValidateIPAddress {
    private static final String IP4 = "IPv4";
    private static final String IP6 = "IPv6";
    private static final String NEITHER = "Neither";

    public String validIPAddress(String IP) {
        if(IP.length() > 39) return NEITHER;
        if(IP.indexOf('.') > 0 && isValidIP4(IP)) return IP4;
        if(IP.indexOf(':') > 0 && isValidIP6(IP)) return IP6;
        return NEITHER;
    }

    private boolean isValidIP4(String ip) {
        int begin = 0;
        int end;
        for(int i = 0; i < 4; ++i) { // four segments
            if(i < 3) end = ip.indexOf('.', begin); //special condition within a loop.
            else end = ip.length();
            //segment length > 3 or no character in-between
            if(end <=begin || end - begin > 3) return false;
            boolean leadingzero = true;
            for(int j = begin; j < end; ++j) {
                char c = ip.charAt(j);
                if(!Character.isDigit(c)) return false; //bad char
                if(leadingzero && c == '0' && end -  begin> 1) return false;//leading zero
                leadingzero = false;
            }
            //limit exceed
            if(Integer.parseInt(ip.substring(begin, end)) > 255) return false;
            begin = end + 1;
        }
        return true;
    }

    private boolean isValidIP6(String ip) {
        int begin = 0;
        int end;
        String hex = "0123456789abcedf";
        for(int i = 0; i < 8; ++i) {//8 segments
            if(i < 7) end = ip.indexOf(':', begin);
            else end = ip.length();
            //end - begin; the number of characters being index begin and end excluding end
            if(end <= begin || end - begin > 4) return false;
            for(int j = begin; j < end; ++j) {
                char c = Character.toLowerCase(ip.charAt(j));
                //not a hex number
                if(hex.indexOf(c) < 0) return false;
            }
            begin = end + 1;
        }
        return true;
    }

    public static void test() {
        new ValidateIPAddress().validIPAddress("172.16.254.1");
    }
}

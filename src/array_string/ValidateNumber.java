package array_string;

import java.util.regex.Pattern;

public class ValidateNumber {
    Pattern DECIMAL = Pattern.compile("^[+-]?[0-9]*[\\.]?[0-9]+$|^[+-]?[0-9]*[\\.]?[0-9]+e[0-9]+$");
    public boolean isNumber(String s) {
        return DECIMAL.matcher(s.trim()).matches();
    }
}

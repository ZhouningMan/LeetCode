package other;

public class IntegerToEnglishWords {
    private static final int THOUSAND = 1000;
    private static final int MILLION = 1000000;
    private static final int BILLION = 1000000000;
    private static final String[] NUM_STR = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] DOUBLE_DIGITS = new String[]{"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] SPECIAL = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    public String numberToWords(int num) {
        if(num == 0) return NUM_STR[0];
        int remainder = num;
        StringBuilder sb = new StringBuilder();
        while(remainder > 0 ) {
            if(remainder < THOUSAND) {
                sb.append(englishOf(remainder));
                remainder = 0;
            } else if(remainder < MILLION) {
                int multiple = remainder / THOUSAND;
                remainder = remainder % THOUSAND;
                sb.append(englishOf(multiple)).append(" Thousand ");
            } else if(remainder < BILLION) {
                int multiple = remainder / MILLION;
                remainder = remainder % MILLION;
                sb.append(englishOf(multiple)).append(" Million ");
            } else {
                int multiple = remainder / BILLION;
                remainder = remainder % BILLION;
                sb.append(englishOf(multiple)).append(" Billion ");
            }
        }
        return sb.toString().trim();
    }
    private String englishOf(int multiples) {
        StringBuilder sb = new StringBuilder();
        while(multiples > 0) {
            if(multiples < 10) {
                sb.append(NUM_STR[multiples]);
                multiples = 0;
            } else if(multiples < 20) {
                sb.append(SPECIAL[multiples - 10]).append(" ");
                multiples = 0;
            } else if(multiples < 100) {
                sb.append(DOUBLE_DIGITS[multiples/10 - 2]).append(" ");
                multiples = multiples % 10;
            } else {
                sb.append(NUM_STR[multiples/100]).append(" Hundred ");
                multiples = multiples % 100;
            }
        }
        return sb.toString().trim();
    }
}

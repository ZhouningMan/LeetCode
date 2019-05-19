package math;

public class TrailingZeros {
    public int trailingZeroes(int n) {
        return n == 0 ? n / 5 : trailingZeroes(n / 5);
    }
}

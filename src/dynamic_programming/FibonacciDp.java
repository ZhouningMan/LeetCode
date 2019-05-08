package dynamic_programming;

public class FibonacciDp {

    public long fibonacci(int n) {
        long[] memory = new long[n + 1];
        for(int i = 0; i <= n; ++i) {
            if(i == 0 || i == 1) memory[i] = i;
            else memory[i] = memory[i - 1] + memory[i - 2];
        }
        return memory[n];
    }

    public static void test() {
        for(int i = 0; i < 50; i++) {
            System.out.print(new FibonacciDp().fibonacci(i) + " ");
        }
    }
}

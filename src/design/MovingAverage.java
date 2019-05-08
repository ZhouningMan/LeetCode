package design;

public class MovingAverage {

    private final int size;

    private double total = 0;
    private int window = 0;
    private final int[] cache;
    private int toRemove = 0;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        if(size == 0) throw new IllegalArgumentException("Size of a moving windows must be greater than 0");
        this.size = size;
        cache = new int[size];
    }

    public double next(int val) {
        if(window < size) {
            window++;
        } else {
            toRemove = toRemove % size;
            total = total - cache[toRemove];
        }
        total = total + val;
        cache[toRemove++] = val;
        return total / window;
    }
}

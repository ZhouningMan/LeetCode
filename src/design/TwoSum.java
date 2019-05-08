package design;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    private final Map<Integer, Integer> nums;
    /** Initialize your data structure here. */
    public TwoSum() {
        nums = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        nums.compute(number, (k, v) -> v == null ? 1 : v+1);
    }

    /**
     * This is a slow find but quick add implementation
     * @param value
     * @return
     */
    public boolean find(int value) {
        for(Map.Entry<Integer, Integer> entry : nums.entrySet()) {
            int diff = value - entry.getKey();
            if(diff == entry.getKey()) { //This deals with duplicate
                if(entry.getValue() == 2) return true;
            } else {
                if(nums.containsKey(diff)) return true;
            }
        }
        return false;
    }
}

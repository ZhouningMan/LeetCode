package heap_queu_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        //the
        Deque<Integer> leftEdge = new ArrayDeque<>();
        int max = -1;
        for(int i = 0; i < heights.length; ++i) {
            int h = heights[i];
            if(i == 0) {
                leftEdge.push(i);
            } else if(h > heights[leftEdge.peek()]) {
                leftEdge.push(i);
            } else {
                while(!leftEdge.isEmpty() && heights[leftEdge.peek()] >= h) {
                    int j = leftEdge.pop();
                    int left = leftEdge.isEmpty() ? -1 : leftEdge.peek();
                    max = Math.max(max, heights[j] * (i - left - 1));
                }
                leftEdge.push(i); //i becomes the left edge for righer elements
            }
        }

        while(!leftEdge.isEmpty()) {
            int j = leftEdge.pop();
            int left = leftEdge.isEmpty() ? -1 : leftEdge.peek();
            //right edge becomes the length of the array
            max = Math.max(max, heights[j] * (heights.length - left -1));
        }
        return max;
    }
}

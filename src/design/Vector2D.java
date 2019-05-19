package design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Vector2D {
    private final Iterator<int[]> parentItr;
    private Iterator<Integer> childItr;

    public Vector2D(int[][] v) {
        parentItr = Arrays.asList(v).iterator();
        createChildItr();
    }

    private void createChildItr() {
        while(parentItr.hasNext()) {
            int[] next = parentItr.next();
            List<Integer> childList = new ArrayList<>();
            for(int n : next) {
                childList.add(n);
            }

            childItr = childList.iterator();
            if(childItr.hasNext()) break;
        }
    }

    public int next() {
        int v = childItr.next();
        if(!childItr.hasNext()) createChildItr();
        return v;
    }

    public boolean hasNext() {
        return childItr != null && childItr.hasNext();
    }
}

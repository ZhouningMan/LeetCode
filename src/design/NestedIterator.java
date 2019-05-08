package design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//this is a recursive data structure
public class NestedIterator implements Iterator<Integer> {
    public interface NestedInteger {
        boolean isInteger();
        Integer getInteger();
        List<NestedInteger> getList();
    }
    private final Iterator<NestedInteger> iterator;
    private Iterator<Integer> childIterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        iterator = nestedList.iterator();
        //recursive case
        if(nestedList.size() > 0 ) {
            childIterator =  createChildIterator(iterator);
        } else { //base case.
            childIterator = Collections.emptyIterator();
        }
    }
    private Iterator<Integer> createChildIterator(Iterator<NestedInteger> iterator) {
        while(iterator.hasNext()) {
            Iterator<Integer> nestedItr;
            NestedInteger nestedInteger = iterator.next();
            if(nestedInteger.isInteger()) {
                //treat integer as a single item list so i can treat all the data uniformly.
                List<Integer> wrap = new ArrayList<>(1);
                wrap.add(nestedInteger.getInteger());
                nestedItr = wrap.iterator();
            } else {
                nestedItr = new NestedIterator(nestedInteger.getList());
            }
            if(nestedItr.hasNext()) return nestedItr;
        }
        return  Collections.emptyIterator();
    }

    @Override
    public boolean hasNext() {
        return childIterator.hasNext();
    }

    @Override
    public Integer next() {
        Integer integer = childIterator.next();
        if(!childIterator.hasNext()) {
            childIterator = createChildIterator(iterator);
        }
        return integer;
    }
}

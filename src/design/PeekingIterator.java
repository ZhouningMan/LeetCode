package design;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PeekingIterator<E> implements Iterator<E>{
    private final Iterator<E> iterator;
    private boolean isPeeked;
    private E peekedItem;

    public PeekingIterator(Iterator<E> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        isPeeked = false;
        peekedItem = null;
    }
    public E peek() {
        if(!isPeeked) {
            E next = iterator.next();
            peekedItem = next;
            isPeeked = true;
        }
        return peekedItem;
    }
    @Override
    public E next() {
        if(isPeeked) {
            isPeeked = false;
            return peekedItem;
        } else {
            return iterator.next();
        }
    }
    @Override
    public boolean hasNext() {
        return isPeeked || iterator.hasNext();
    }

    public static void test() {
        PeekingIterator<Integer> iterator = new PeekingIterator<>(Stream.of(1, 2, 3).collect(Collectors.toList()).iterator());
        System.out.println(iterator.next());
        System.out.println(iterator.peek());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }
}

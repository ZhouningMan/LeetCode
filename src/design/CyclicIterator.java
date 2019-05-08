package design;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CyclicIterator {
    private final List<List<Integer>> numLists = new ArrayList<>();
    private int index;
    private int listIndex;

    public CyclicIterator(List<Integer>... lists) {
        for(List<Integer> list : lists) {
            if(!list.isEmpty()) numLists.add(list);
        }
        index = 0;
        listIndex = 0;
    }

    public int next() {
        listIndex= listIndex % numLists.size();
        List<Integer> numList = numLists.get(listIndex);
        int val = numList.get(index);
        boolean shouldRemove = numList.size() - 1 == index;
        if(listIndex == numLists.size() - 1) {
            index++;
        }

        if(shouldRemove) {
            numLists.remove(numList);
        } else {
            listIndex++;
        }
        return val;
    }

    public boolean hasNext() {
        return !numLists.isEmpty();
    }

    public static void test() {
        List<Integer> list1 = Stream.of(1).collect(Collectors.toList());
      //  List<Integer> list2 = Stream.of(3, 4, 5, 6).collect(Collectors.toList());
        CyclicIterator cyclicIterator = new CyclicIterator(list1);
        while(cyclicIterator.hasNext()) {
            System.out.println(cyclicIterator.next());
        }
    }
}

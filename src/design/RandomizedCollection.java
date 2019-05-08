package design;

import java.util.*;

@SuppressWarnings("Duplicates")
public class RandomizedCollection {
    //key is the inserted number, value is the position in valueList
    private final Map<Integer, Set<Integer>> valPos;
    //valLIst contains all the values
    private final List<Integer> valList;
    private final Random random;

    public RandomizedCollection() {
        valPos = new HashMap<>();
        valList = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean isNew = !valPos.containsKey(val);
        valPos.computeIfAbsent(val, k -> new HashSet<>()).add(valList.size());
        valList.add(val); //add to the end which is constant time
        return isNew;
    }

    public boolean remove(int val) {
        if(!valPos.containsKey(val)) return false;
        Set<Integer> positions = valPos.get(val);
        Iterator<Integer> it = positions.iterator();
        int pos = it.next();//remove one element
        it.remove();
        if(pos != valList.size() -1) { //if the element is not the last element in the list
            //swap the last element to the element to be deleted
            int lastVal = valList.get(valList.size() - 1);
            valPos.get(lastVal).remove(valList.size() - 1);
            valPos.get(lastVal).add(pos);
            //don't forget to update the value in the list.
            valList.set(pos, lastVal);
        }
        if(positions.isEmpty()) valPos.remove(val);
        valList.remove(valList.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int pos = random.nextInt(valList.size());
        return valList.get(pos);
    }

    public static void test() {
        RandomizedCollection collection = new RandomizedCollection();
        collection.insert(4);
        collection.insert(3);
        collection.insert(4);
        collection.insert(2);
        collection.insert(4);
        collection.remove(4);
        collection.remove(3);
        collection.remove(4);
        collection.remove(4);
    }
}

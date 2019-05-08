package design;

import java.util.*;

@SuppressWarnings("Duplicates")
public class RandomizedSet {
    //key is the inserted number, value is the position in valueList
    private final Map<Integer, Integer> valPos;
    //valLIst contains all the values
    private final List<Integer> valList;
    private final Random random;
    public RandomizedSet() {
        valPos = new HashMap<>();
        valList = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if(valPos.containsKey(val)) return false;
        valPos.put(val, valList.size());
        valList.add(val); //add to the end which is constant time
        return true;
    }

    public boolean remove(int val) {
        if(!valPos.containsKey(val)) return false;
        int pos = valPos.remove(val);
        int lasVal = valList.get(valList.size() - 1);
        //if it is not the last element, we swap it with the last element
        //and always remove the last element
        if(val != lasVal) {
            valList.set(pos, valList.get(valList.size() - 1));
            //IMPORTANT we need to make sure the map and the list are consistent
            valPos.put(lasVal, pos);
        }
        valList.remove(valList.size() - 1);//remove last element;
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int pos = random.nextInt(valList.size());
        return valList.get(pos);
    }

    public static void test() {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(3);
        randomizedSet.insert(-2);
        randomizedSet.remove(2);
        randomizedSet.insert(1);
        randomizedSet.insert(-3);
        randomizedSet.insert(-2);
        randomizedSet.remove(-2);
        randomizedSet.remove(-3);
        randomizedSet.insert(-1);
        randomizedSet.remove(-3);
        randomizedSet.insert(1);
        randomizedSet.insert(-2);
        randomizedSet.insert(-2);
        randomizedSet.insert(-2);
        randomizedSet.insert(1);
        randomizedSet.getRandom();
        randomizedSet.insert(-2);
        randomizedSet.remove(0);
        randomizedSet.insert(-3);
        randomizedSet.insert(1);
    }
}

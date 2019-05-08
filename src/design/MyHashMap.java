package design;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class MyHashMap {

    private static class Pair {
        int key;
        int val;
    }

    private List<Pair>[] buckets;
    private double size = 0;
    /** Initialize your data structure here. */
    public MyHashMap() {
        //default the buckts to 4
        buckets = (List<Pair>[])new ArrayList[4];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = getHash(key);
        if(buckets[hash] == null) buckets[hash] = new ArrayList<>();

        Pair pair = null;
        for(Pair p : buckets[hash]) {
            if(p.key == key) {
                pair = p;
                break;
            }
        }
        if(pair == null) {
            pair = new Pair();
            buckets[hash].add(pair);
            size++;
        }

        pair.key = key;
        pair.val = value;
        if(size % buckets.length >= 0.75 && buckets.length > 4) {
            resize(buckets.length * 2);
        }
    }

    private int getHash(int key) {
        return key % buckets.length;
    }

    private void resize(int size) {
        List<Pair>[] temp = buckets;
        this.size = 0;
        buckets = (List<Pair>[])new ArrayList[size];
        for(List<Pair> list : temp) {
            for(Pair p : list) {
                put(p.key, p.val);
            }
        }
    }

    public int get(int key) {
        int hash = getHash(key);
        if(buckets[hash] == null) return -1;
        for(Pair p : buckets[hash]) {
            if(p.key == key) return p.val;
        }
        return -1;
    }

    public void remove(int key) {
        int hash = getHash(key);
        if(buckets[hash] == null) return;
        boolean removed = buckets[hash].removeIf(p -> p.key == key);
        if(removed) size--;
        if(buckets.length > 4 && size / buckets.length <= 0.25) {
            resize(buckets.length/2);
        }
    }

    public static void test() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1);
        myHashMap.put(2, 2);
        System.out.println(myHashMap.get(1));
        System.out.println(myHashMap.get(3));
        System.out.println(myHashMap.get(1));
        myHashMap.put(2, 1);
        System.out.println(myHashMap.get(2));
        myHashMap.remove(2);
        System.out.println(myHashMap.get(2));

    }
}

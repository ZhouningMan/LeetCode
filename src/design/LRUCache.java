package design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    static class Node {
        int value;
        int key;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache = new HashMap<>();
    private Node head;
    private Node tail;


    public LRUCache(int capacity) {
        if(capacity == 0) throw new IllegalArgumentException("Capacity must be greater than 0 for LRUCache");
        this.capacity = capacity;
    }


    public int get(int key) {
        if(!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        updateMostRecentNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            updateMostRecentNode(node);
        } else {
            if(cache.size()  == capacity){
                cache.remove(tail.key);
                Node next = tail.next;
                tail.next = null;
                if(next != null) {
                    next.prev = null;
                }
                tail = next;
            }
            Node node = new Node(key, value);
            cache.put(key, node);
            updateMostRecentNode(node);
        }

        if(cache.size() == 1) {
            tail = head;
        }
    }
    private void updateMostRecentNode(Node node) {
        //special cases for head and tail.
        if(node == head ) return;
        if(node == tail) {
            if(tail.next != null) {
                tail = tail.next;
                tail.prev = null;
            }
        }

        if(node.prev != null) {
            node.prev.next = node.next;
        }
        if(node.next != null) {
            node.next.prev = node.prev;
        }

        node.prev = head;
        node.next = null;
        if(head != null) {
            head.next = node;
        }
        head = node;
    }

    public static void test() {
        LRUCache cache = new LRUCache( 1/* capacity */ );
        cache.put(2,1);
        cache.get(2);
        cache.put(3,2);
        cache.get(2);
        cache.get(3);
    }
}

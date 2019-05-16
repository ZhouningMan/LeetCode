package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreqStack {
    private static class Node {
        int key;
        int freq;
        Node prev;
        Node next;

        Node(int key) {
            this.key = key;
        }
    }

    private final Map<Integer, List<Node>> freqMap;
    private Node head;
    private Node tail;

    public FreqStack() {
        freqMap = new HashMap<>();
        head = null;
        tail = null;
    }

    public void push(int x) {
        List<Node> prev = freqMap.computeIfAbsent(x, k -> new ArrayList<>());
        Node node = new Node(x);
        if(prev.isEmpty()) {
            node.freq = 1;
            if(tail == null) {
                head = tail = node;
            } else {
                swim(node, tail);
            }
        } else {//this node is already in the list
            Node start = prev.get(prev.size() - 1); //get last node with same key
            node.freq = start.freq + 1;
            swim(node, start);
        }
        prev.add(node);
    }

    private Node swim(Node node, Node prev) {
        while(prev.next != null && prev.next.freq <= node.freq) {
            prev = prev.next;
        }

        Node next = prev.next;
        prev.next = node;
        node.prev = prev;
        node.next = next;
        if(next != null) {
            next.prev = node;
        } else {
            head = node;//update head.
        }
        return node;
    }

    public int pop() {
        Node node = head;
        List<Node> nodes = freqMap.get(node.key);
        nodes.remove(nodes.size() -1);
        if(nodes.isEmpty()) freqMap.remove(node.key);
        head = head.prev;
        if(head != null) head.next = null;
        //if this is the only element;
        if(tail == node) tail = null;
        return node.key;
    }

    public static void test() {
        FreqStack obj = new FreqStack();
        obj.push(5);
        obj.push(7);
        obj.push(5);
        obj.push(7);
        obj.push(4);
        obj.push(5);
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
    }
}

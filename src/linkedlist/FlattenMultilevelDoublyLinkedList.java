package linkedlist;

public class FlattenMultilevelDoublyLinkedList {
    public Node flatten(Node head) {
        if(head == null) return null;
        return helper(head, null);
    }

    private Node helper(Node begin, Node tail) {
        Node curr = begin;
        //here we are examine each node.
        while (curr != null) { //move node until the end
            Node child = curr.child;
            if (child != null) { //we find child
                child.prev = curr;
                Node next = curr.next;
                curr.child = null;
                curr.next = child;
                helper(child, next); //flatten children
                if(next == null) { //done
                    break;
                } else { //move one node ahead
                    curr = next;
                }
            } else if (curr.next != null) {
                curr = curr.next; //move to next node
            } else {
                break; //done
            }
        }
        //only update next when next is null, this is to deal with the special case
        //where we only have children
        if(curr.next == null) curr.next = tail;
        if(tail != null) tail.prev = curr;
        return begin;
    }
//    private Node helper(Node begin, Node tail) {
//        if(begin == null) return tail;
//        Node curr = begin;
//        Node child = curr.child;
//        while(child == null && curr.next != null) {
//            curr = curr.next;
//            child = curr.child;
//        }
//
//        if(child != null) {
//            child.prev = curr;
//            Node next = curr.next;
//            curr.child = null;
//            curr.next = helper(child, next);
//        }
//
//        while(curr.next != null) curr = curr.next;
//        curr.next = tail;
//        if(tail != null) tail.prev = curr;
//        return begin;
//    }

    public static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }
}

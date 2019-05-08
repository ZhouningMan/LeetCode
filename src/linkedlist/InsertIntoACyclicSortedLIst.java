package linkedlist;

public class InsertIntoACyclicSortedLIst {
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val,Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        if(head == null) {
            Node result = new Node(insertVal, null);
            result.next = result;
            return result;
        }

        Node ptr = head;
        Node maxNode = head;
        while(ptr.next != head) {
            if(maxNode.val <= ptr.next.val) {
                maxNode = ptr.next;
            }
            ptr = ptr.next;
        }

        if(insertVal >= maxNode.val) {
            maxNode.next= new Node(insertVal, maxNode.next);
        } else {
            ptr = maxNode;
            while(insertVal > ptr.next.val) {
                ptr = ptr.next;
            }
            ptr.next = new Node(insertVal, ptr.next);

        }
        return head;
    }
}

package linkedlist;

public class ReverseNodesInKGroup {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int len = findLen(head);
        boolean first = true;
        ListNode  begin = head;
        ListNode tail = begin;
        while(len >= k) {
            ListNode newBegin = reverse(begin, k);
            //process the result first
            if(first) { //first time, we record the new head and we don't need to update the tail
                head = newBegin;
                first = false;
            } else {
                tail.next = newBegin; //we need to update the previous tail to the next beginning
                tail = begin; //tail now becomes the previous begin, this begin will be updated for next iteration/
            }
            len -=k;
            //update the begin for next iteration.
            begin = begin.next;
        }
        return head;
    }

    //reverse begin and begin will points to the next un reversed part
    private ListNode reverse(ListNode begin, int k) {
        ListNode newBegin = null;
        ListNode tail = begin;
        ListNode next = null;
        //basic pop from one list and push to another list implementation
        while(k > 0) {
            next = begin.next;
            begin.next = newBegin;
            newBegin = begin;

            begin = next;
            k--;
        }
        tail.next = next; //this is important.
        return newBegin;
    }

    private int findLen(ListNode head) {
        int len = 0;
        while(head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}

package linkedlist;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode curr = head;
        //keeping track the linked list position through an independent variable
        int k = 1;
        ListNode transition = null;
        //locate the node right before the portion to reverse
        while (k < m) {
            transition = curr;
            curr = curr.next;
            k++;
        }
        //remember the tail
        ListNode tail = curr;

        //reverse the linked list
        ListNode before = null;
        while(k <= n) {
            ListNode next = curr.next;
            curr.next = before;
            before = curr;
            curr = next;
            k++;
        }
        //update tail
        tail.next = curr;
        //found head
        if(transition != null) transition.next = before;
        else head = before;
        return head;
    }
}

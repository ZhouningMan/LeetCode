package linkedlist;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first = l1;
        ListNode second = l2;
        int carryOver = 0;
        ListNode tail = first == null? second : first;
        ListNode head = tail;
        while (first != null || second != null) {
            int sum = carryOver;
            if(first != null) {
                sum += first.val;
                first = first.next;
            }
            if(second != null) {
                sum += second.val;
                second = second.next;
            }
            tail.val = sum %10;
            carryOver = sum / 10;
            tail.next = first == null? second: first;
            if(tail.next != null) tail = tail.next;
        }
        if (carryOver == 1) tail.next = new ListNode(carryOver);
        return head;
    }


    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode first = l1;
        ListNode second = l2;
        int carryOver = 0;
        ListNode tail = first;
        while(first != null && second != null) {
            int sum = (first.val + second.val + carryOver);
            first.val = sum%10;
            carryOver = sum/10;
            tail = first;
            first = first.next;
            second = second.next;
        }

        ListNode remaining = first == null? second: first;
        tail.next = remaining;
        while(remaining != null) { //the remaining list
            int sum = remaining.val + carryOver;
            carryOver = sum/10;
            remaining.val = sum%10;
            tail = remaining;
            remaining = remaining.next;
        }
        if(carryOver == 1) tail.next = new ListNode(carryOver);
        return l1;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}

package linkedlist;

public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n = length(l1);
        int m = length(l2);
        ListNode longer = n >= m? l1 : l2;
        ListNode shorter = n >= m? l2 : l1;
        int diff = n > m ? n - m : m - n;
        ListNode head = longer;
        while(diff > 0) {
            longer = longer.next;
            diff--;
        }

        int semiCarry = sum(longer, shorter);
        int carry = sum(head, longer, semiCarry);
        if(carry == 0) return head;
        else {
            ListNode newHead = new ListNode(carry);
            newHead.next = head;
            return newHead;
        }
    }

    private int length(ListNode list) {
        ListNode node = list;
        int n =0;
        while(node != null) {
            n++;
            node = node.next;
        }
        return n;
    }

    private int sum(ListNode first, ListNode second) {
        if(first == null && second == null) return 0;
        int carry = sum(first.next, second.next);
        int sum = first.val + second.val + carry;
        first.val = sum % 10;
        return sum / 10;
    }

    private int sum(ListNode begin, ListNode end, int val) {
        if(begin == end) return val;
        int carry = sum(begin.next, end, val);
        int sum = begin.val + carry;
        begin.val = sum % 10;
        return sum / 10;
    }
}

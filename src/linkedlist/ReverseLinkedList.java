package linkedlist;

public class ReverseLinkedList {
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

    public ListNode reverseList(ListNode head) {
       ListNode newHead = null;
       while(head != null) {
           ListNode next = head.next;
           head.next = newHead;
           newHead = head;
           head = next;
       }
       return newHead;
    }

    private ListNode reverseListRecur(ListNode head, ListNode newHead) {
        if (head == null)
            return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return reverseListRecur(next, head);
    }

    public void printNode(ListNode newHead, ListNode head) {
        System.out.print("New head: ");
        while(newHead != null) {
            System.out.print(newHead.val + "-->");
            newHead = newHead.next;
        }

        System.out.print("null.   Head: ");
        while(head != null) {
            System.out.print(head.val + "-->");
            head = head.next;
        }
        System.out.println("null.");
    }

    public static void test() {
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        root = new ReverseLinkedList().reverseList(root);
    }
}

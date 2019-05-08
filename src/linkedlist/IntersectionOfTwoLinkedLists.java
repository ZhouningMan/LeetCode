package linkedlist;

public class IntersectionOfTwoLinkedLists {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        boolean resetA = false;
        boolean resetB = false;
        ListNode pA = headA;
        ListNode pB = headB;
        while(pA != null && pB != null) {
            if(pA == pB) return pA;
            pA = pA.next;
            pB = pB.next;
            if(pA == null && !resetA) {
                resetA = true;
                pA = headB;
            }
            if(pB == null && !resetB) {
                resetB = true;
                pB = headA;
            }
        }
        return null;
    }
}

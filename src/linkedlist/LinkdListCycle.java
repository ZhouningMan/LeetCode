package linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkdListCycle {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null) {
            for(int i = 0; i < 2 && fast != null; i++) {
                if (fast == slow) return true;
                fast = fast.next;
            }
            slow = slow.next;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode node = head;
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            visited.add(node);
            node = node.next;
        }

        return null;
    }
}

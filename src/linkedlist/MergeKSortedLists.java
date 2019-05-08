package linkedlist;

import java.util.*;
import java.util.regex.Pattern;

public class MergeKSortedLists {

      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }



    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return sort(lists, 0, lists.length - 1);
    }

    private ListNode sort(ListNode[] lists, int right, int left) {
        if (right >= left) {
            return lists[right];
        }

        int mid = right + (left - right) / 2;
        ListNode node1 = sort(lists, right, mid);
        ListNode node2 = sort(lists, mid + 1, left);
        return merge(node1, node2);
    }

    private ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }

        if (node1.val < node2.val) {
            node1.next = merge(node1.next, node2);
            return node1;
        }

        node2.next = merge(node1, node2.next);
        return node2;
    }

    public ListNode mergeKListsPQ(ListNode[] lists) {
        if(lists == null || lists.length == 0 ) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        //
        for(int i = 0; i < lists.length; ++i) {
            if(lists[i] != null) {
                pq.add(lists[i]);
            }
        }

        ListNode head = null;
        ListNode ptr = null;
        while(!pq.isEmpty()) {
            if(head == null) {
                head = pq.poll();
                ptr = head;
            } else {
                ptr.next = pq.poll();
                ptr = ptr.next;
            }
            if(ptr.next != null){
                pq.add(ptr.next);
            }
        }
        return head;
    }



    public ListNode mergeKListsSB(ListNode[] lists) {
        if(lists == null || lists.length == 0 ) return null;
        int len = lists.length;
        ListNode newHead = null;
        ListNode ptr = null;

        while (true) {
            //find the min node
            int minIndex = -1;
            int minVal = Integer.MAX_VALUE;
            for (int i = 0; i < len; i++) {
                if(lists[i] == null) continue;
                if (lists[i].val < minVal) {
                    minIndex = i;
                    minVal = lists[i].val;
                }
            }
            //no more min index
            if(minIndex == -1) {
                break;
            }
            if (newHead == null) {
                newHead =lists[minIndex];
                ptr = newHead;
            } else {
                ptr.next = lists[minIndex];
                ptr = ptr.next;
            }
            lists[minIndex] = lists[minIndex].next;
        }
        return newHead;
    }

}

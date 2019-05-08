package sorting_search;

public class MergeTwoSortedList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode ptr = null;
        //identical idea to merge to sorted array
        while(list1 != null || list2 != null) {
            ListNode node;
            if(list1 == null) {
                node = list2;
                list2 = list2.next;
            } else if(list2 == null) {
                node = list1;
                list1 = list1.next;
            } else if(list1.val <= list2.val) {
                node = list1;
                list1 = list1.next;
            } else {
                node = list2;
                list2 = list2.next;
            }
            //remembering head is simple
            if(head == null) {
                head = node;
                ptr = head;
            } else {
                ptr.next = node;
                ptr = ptr.next;
            }
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

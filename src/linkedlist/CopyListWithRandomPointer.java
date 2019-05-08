package linkedlist;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    class RandomListNode {
      int label;
      RandomListNode next, random;
      RandomListNode(int x) { this.label = x; }
    }

    private final Map<RandomListNode, RandomListNode> visistedNode = new HashMap<>();

    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode oldHead = head;
        RandomListNode node = new RandomListNode(head.label);
        visistedNode.put(oldHead,node);
        while(head != null) {
            node.next = clonedNodeFor(head.next);
            node.random = clonedNodeFor(head.random);
            head = head.next;
            node = node.next;
        }
        return visistedNode.get(oldHead);
    }

    private RandomListNode clonedNodeFor(RandomListNode old) {
        if(old == null) return null;
        return visistedNode.computeIfAbsent(old, key -> new RandomListNode(old.label));
    }

    public RandomListNode copyRandomListRecur(RandomListNode head) {
        if(head == null) return null;
        if(visistedNode.containsKey(head)) return visistedNode.get(head);
        RandomListNode node = new RandomListNode(head.label);
        visistedNode.put(head, node);
        node.next = copyRandomListRecur(head.next);
        node.random = copyRandomListRecur(head.random);
        return node;
    }





    public RandomListNode copyRandomListWeaving(RandomListNode head) {
        //NULL check omitted. Creating a new weaved list of original and copied nodes.
        RandomListNode ptr = head;
        while (ptr != null) {
            // Cloned node
            RandomListNode newNode = new RandomListNode(ptr.label);
            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }
        ptr = head;
        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }
        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        RandomListNode ptr_old_list = head; // A->B->C
        RandomListNode ptr_new_list = head.next; // A'->B'->C'
        RandomListNode head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }
}
